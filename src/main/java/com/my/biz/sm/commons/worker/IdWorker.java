package com.my.biz.sm.commons.worker;

import org.apache.log4j.Logger;


/**
 * @author zhujuan
 * From: https://github.com/twitter/snowflake
 * An object that generates IDs.
 * This is broken into a separate class in case
 * we ever want to support multiple worker threads
 * per process
 * 
 * 以下详细说明
 * Snowflake ID有64bits长，由以下三部分组成：
 * 1. time—42bits,精确到ms，那就意味着其可以表示长达(2^42-1)/(1000360024*365)=139.5年，另外使用者可以自己定义一个开始纪元（epoch)，
 * 然后用(当前时间-开始纪元）算出time，这表示在time这个部分在140年的时间里是不会重复的，官方文档在这里写成了41bits，应该是写错了。
 * 另外，这里用time还有一个很重要的原因，就是可以直接更具time进行排序，对于twitter这种更新频繁的应用，时间排序就显得尤为重要了。
 * 
 * 2. machine id—10bits,该部分其实由datacenterId和workerId两部分组成，这两部分是在配置文件中指明的。
 * 
 * datacenterId的作用(个人看法)
 * 1).方便搭建多个生成uid的service，并保证uid不重复，比如在datacenter0将机器0，1，2组成了一个生成uid的service，
 * 而datacenter1此时也需要一个生成uid的service，从本中心获取uid显然是最快最方便的，那么它可以在自己中心搭建，
 * 只要保证datacenterId唯一。如果没有datacenterId，即用10bits，那么在搭建一个新的service前必须知道目前已经在用的id，
 * 否则不能保证生成的id唯一，比如搭建的两个uid service中都有machine id为100的机器，如果其server时间相同，那么产生相同id的情况不可避免。
 * 
 * 2).加快server启动速度。启动一台uid server时，会去检查zk同workerId目录中其他机器的情况，
 * 如其在zk上注册的id和向它请求返回的work_id是否相同，是否处同一个datacenter下，
 * 另外还会检查该server的时间与目前已有机器的平均时间误差是否在10s范围内等，这些检查是会耗费一定时间的。
 * 将一个datacenter下的机器数限制在32台(5bits)以内，在一定程度上也保证了server的启动速度。
 * 
 * workerId是实际server机器的代号，最大到32，同一个datacenter下的workerId是不能重复的。
 * 它会被注册到zookeeper上，确保workerId未被其他机器占用，并将host:port值存入，注册成功后就可以对外提供服务了。
 * 
 * sequence id —12bits,该id可以表示4096个数字，它是在time相同的情况下，递增该值直到为0，即一个循环结束，此时便只能等到下一个ms到来，
 * 一般情况下4096/ms的请求是不太可能出现的，所以足够使用了。
 * 
 * Snowflake ID便是通过这三部分实现了UID的产生，策略也并不复杂。
 * 
 * 核心代码就是毫秒级时间41位+机器ID 10位+毫秒内序列12位
 * 
 */
public class IdWorker {
    
    protected static final Logger LOG = Logger.getLogger(IdWorker.class);
    
    private long workerId; //机器标识位数
    private long datacenterId; 
    private long sequence = 0L;

    private long twepoch = 1288834974657L;

    private long workerIdBits = 5L;
    private long datacenterIdBits = 5L;
    private long maxWorkerId = -1L ^ (-1L << workerIdBits); //机器ID最大值
    private long maxDatacenterId = -1L ^ (-1L << datacenterIdBits); //数据中心ID最大值
    private long sequenceBits = 12L; //毫秒内自增位

    private long workerIdShift = sequenceBits; //机器ID偏左移12位
    private long datacenterIdShift = sequenceBits + workerIdBits; //数据中心ID左移17位
    private long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits; //时间毫秒左移22位
    private long sequenceMask = -1L ^ (-1L << sequenceBits);

    private long lastTimestamp = -1L;

    public IdWorker(long workerId, long datacenterId) {
        // sanity check for workerId
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(
            		String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
        }
        if (datacenterId > maxDatacenterId || datacenterId < 0) {
            throw new IllegalArgumentException(
            		String.format("datacenter Id can't be greater than %d or less than 0", maxDatacenterId));
        }
        this.workerId = workerId;
        this.datacenterId = datacenterId;
        LOG.info(
        		String.format("worker starting. timestamp left shift %d, datacenter id bits %d, " +
        				"worker id bits %d, sequence bits %d, workerid %d", timestampLeftShift, 
        						datacenterIdBits, workerIdBits, sequenceBits, workerId));
    }

    public synchronized long nextId() {
        long timestamp = timeGen();
        //时间错误
        if (timestamp < lastTimestamp) {
            LOG.error(String.format("clock is moving backwards.  Rejecting requests until %d.", lastTimestamp));
            throw new RuntimeException(
            		String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", 
            				lastTimestamp - timestamp));
        }

        if (lastTimestamp == timestamp) {
        	//当前毫秒内，则+1
            sequence = (sequence + 1) & sequenceMask;
            if (sequence == 0) {
            	//当前毫秒内计数满了，则等待下一秒
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0L;
        }
        
        lastTimestamp = timestamp;
        //ID偏移组合生成最终的ID，并返回ID
        return ((timestamp - twepoch) << timestampLeftShift) 
        			| (datacenterId << datacenterIdShift) 
        				| (workerId << workerIdShift) | sequence;
    }

    //等待下一个毫秒的到来
    protected long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    protected long timeGen() {
        return System.currentTimeMillis();
    }
    
    public static void main(String[] args) {
		IdWorker worker = new IdWorker(1,1);
		for (int i = 0; i < 1000; i++) {
			System.out.println(worker.nextId());
		}
	}
}
