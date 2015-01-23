package com.my.biz.sm.commons.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.util.StringUtils;
import com.my.biz.sm.commons.json.JsonConverter;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;
import redis.clients.jedis.Tuple;


public class JedisProxy
{
    private JedisPool jedisPool;

    public JedisProxy(JedisPool jedisPool)
    {
        this.jedisPool = jedisPool;
    }

    public static JedisProxy getLRUCache()
    {
        return new JedisProxy(JedisPoolFactory.getJedisPool("lruCache"));
    }

    public static JedisProxy getPersistCache()
    {
        return new JedisProxy(JedisPoolFactory.getJedisPool("persistCache"));
    }

    public String ping()
    {
        Jedis jedis = jedisPool.getResource();
        try
        {
            return jedis.ping();
        }
        finally
        {
            jedisPool.returnResource(jedis);
        }
    }

    public Set<Tuple> zrevrangeByScoreWithScores(String key, String max, String min)
    {
        Jedis jedis = jedisPool.getResource();
        try
        {
            return jedis.zrevrangeByScoreWithScores(key, max, min);
        }
        finally
        {
            jedisPool.returnResource(jedis);
        }
    }

    public Long rpushx(byte[] key, byte[] string)
    {
        Jedis jedis = jedisPool.getResource();
        try
        {
            return jedis.rpushx(key, string);
        }
        finally
        {
            jedisPool.returnResource(jedis);
        }

    }

    public ScanResult<String> sscan(final String key, final String cursor, final ScanParams params)
    {
        Jedis jedis = jedisPool.getResource();
        try
        {
            return jedis.sscan(key, cursor, params);
        }
        finally
        {
            jedisPool.returnResource(jedis);
        }
    }

    public ScanResult<Tuple> zscan(final String key, final String cursor, final ScanParams params)
    {
        Jedis jedis = jedisPool.getResource();
        try
        {
            return jedis.zscan(key, cursor, params);
        }
        finally
        {
            jedisPool.returnResource(jedis);
        }
    }

    //
    // public Object evalsha(String sha1, int keyCount, String... params)
    // {
    // return jedis.evalsha(sha1, keyCount, params);
    // }
    //
    // public String getSet(String key, String value)
    // {
    // return jedis.getSet(key, value);
    // }
    //
    // public Long sort(String key, SortingParams sortingParameters, String dstkey)
    // {
    // return jedis.sort(key, sortingParameters, dstkey);
    // }
    //
    // public Long setrange(byte[] key, long offset, byte[] value)
    // {
    // return jedis.setrange(key, offset, value);
    // }
    //
    // public Long hsetnx(byte[] key, byte[] field, byte[] value)
    // {
    // return jedis.hsetnx(key, field, value);
    // }
    //
    // public Long zinterstore(String dstkey, String... sets)
    // {
    // return jedis.zinterstore(dstkey, sets);
    // }
    //
    // public Long linsert(String key, BinaryClient.LIST_POSITION where, String pivot, String value)
    // {
    // return jedis.linsert(key, where, pivot, value);
    // }
    //
    // public Object eval(String script, int keyCount, String... params)
    // {
    // return jedis.eval(script, keyCount, params);
    // }
    //
    public Double zincrby(String key, double score, String member)
    {
        Jedis jedis = jedisPool.getResource();
        try
        {
            return jedis.zincrby(key, score, member);
        }
        finally
        {
            jedisPool.returnResource(jedis);
        }
    }

    //
    // public Long msetnx(String... keysvalues)
    // {
    // return jedis.msetnx(keysvalues);
    // }
    //
    // public String lindex(String key, long index)
    // {
    // return jedis.lindex(key, index);
    // }
    //
    // public Long decr(String key)
    // {
    // return jedis.decr(key);
    // }
    //
    // public Boolean setbit(byte[] key, long offset, byte[] value)
    // {
    // return jedis.setbit(key, offset, value);
    // }
    //
    public Long rpush(String key, String... strings)
    {
        Jedis jedis = jedisPool.getResource();
        try
        {
            return jedis.rpush(key, strings);
        }
        finally
        {
            jedisPool.returnResource(jedis);
        }
    }

    //
    // public Boolean getbit(String key, long offset)
    // {
    // return jedis.getbit(key, offset);
    // }
    //
    // public Set<byte[]> zrevrangeByScore(byte[] key, double max, double min)
    // {
    // return jedis.zrevrangeByScore(key, max, min);
    // }
    //
    public String setex(String key, int seconds, String value)
    {
        Jedis jedis = jedisPool.getResource();
        try
        {
            return jedis.setex(key, seconds, value);
        }
        finally
        {
            jedisPool.returnResource(jedis);
        }
    }

    //
    // public Long expireAt(String key, long unixTime)
    // {
    // return jedis.expireAt(key, unixTime);
    // }
    //
    public Long hdel(String key, String... fields)
    {
        Jedis jedis = jedisPool.getResource();
        try
        {
            return jedis.hdel(key, fields);
        }
        finally
        {
            jedisPool.returnResource(jedis);
        }
    }

    //
    // public byte[] substr(byte[] key, int start, int end)
    // {
    // return jedis.substr(key, start, end);
    // }
    //
    // public Long scard(byte[] key)
    // {
    // return jedis.scard(key);
    // }
    //
    // public Object eval(byte[] script, List<byte[]> keys, List<byte[]> args)
    // {
    // return jedis.eval(script, keys, args);
    // }
    //
    // public Long msetnx(byte[]... keysvalues)
    // {
    // return jedis.msetnx(keysvalues);
    // }
    //
    // public Long zremrangeByScore(byte[] key, double start, double end)
    // {
    // return jedis.zremrangeByScore(key, start, end);
    // }
    //
    // public Long llen(String key)
    // {
    // return jedis.llen(key);
    // }
    //
    // public Long zremrangeByRank(byte[] key, int start, int end)
    // {
    // return jedis.zremrangeByRank(key, start, end);
    // }
    //
    // public Long zunionstore(String dstkey, String... sets)
    // {
    // return jedis.zunionstore(dstkey, sets);
    // }
    //
    // public List<String> sort(String key)
    // {
    // return jedis.sort(key);
    // }
    //
    // public Long incr(byte[] key)
    // {
    // return jedis.incr(key);
    // }
    //
    public Set<String> zrangeByScore(String key, double min, double max, int offset, int count)
    {
        Jedis jedis = jedisPool.getResource();
        try
        {
            return jedis.zrangeByScore(key, min, max, offset, count);
        }
        finally
        {
            jedisPool.returnResource(jedis);
        }
    }

    public Set<String> zrangeByScore(String key, String min, String max)
    {
        Jedis jedis = jedisPool.getResource();
        try
        {
            return jedis.zrangeByScore(key, min, max);
        }
        finally
        {
            jedisPool.returnResource(jedis);
        }
    }

    //
    // public List<byte[]> sort(byte[] key, SortingParams sortingParameters)
    // {
    // return jedis.sort(key, sortingParameters);
    // }
    //
    // public Long persist(byte[] key)
    // {
    // return jedis.persist(key);
    // }
    //
    // public Long zunionstore(String dstkey, ZParams params, String... sets)
    // {
    // return jedis.zunionstore(dstkey, params, sets);
    // }
    //
    // public String configSet(String parameter, String value)
    // {
    // return jedis.configSet(parameter, value);
    // }
    //
    // public void psubscribe(BinaryJedisPubSub jedisPubSub, byte[]... patterns)
    // {
    // jedis.psubscribe(jedisPubSub, patterns);
    // }
    //
    // public Long hsetnx(String key, String field, String value)
    // {
    // return jedis.hsetnx(key, field, value);
    // }
    //
    // public List<Boolean> scriptExists(String... sha1)
    // {
    // return jedis.scriptExists(sha1);
    // }
    //
    // public Long sdiffstore(String dstkey, String... keys)
    // {
    // return jedis.sdiffstore(dstkey, keys);
    // }
    //
    // public Long decrBy(String key, long integer)
    // {
    // return jedis.decrBy(key, integer);
    // }
    //
    // public String scriptLoad(String script)
    // {
    // return jedis.scriptLoad(script);
    // }
    //
    // public Long zcount(String key, double min, double max)
    // {
    // return jedis.zcount(key, min, max);
    // }
    //
    // public List<String> hvals(String key)
    // {
    // return jedis.hvals(key);
    // }
    //
    public String set(String key, String value)
    {
        Jedis jedis = jedisPool.getResource();
        try
        {
            return jedis.set(key, value);
        }
        finally
        {
            jedisPool.returnResource(jedis);
        }
    }

    public String set(String key, Object value)
    {
        Jedis jedis = jedisPool.getResource();
        try
        {
            return set(key, JsonConverter.format(value));
        }
        finally
        {
            jedisPool.returnResource(jedis);
        }
    }

    //
    // public Pipeline pipelined()
    // {
    // return jedis.pipelined();
    // }
    //
    // public Set<byte[]> sinter(byte[]... keys)
    // {
    // return jedis.sinter(keys);
    // }
    //
    // public byte[] lpop(byte[] key)
    // {
    // return jedis.lpop(key);
    // }
    //
    // public Set<byte[]> zrevrangeByScore(byte[] key, double max, double min, int offset, int count)
    // {
    // return jedis.zrevrangeByScore(key, max, min, offset, count);
    // }
    //
    // public void sync()
    // {
    // jedis.sync();
    // }
    //
    // public Long smove(String srckey, String dstkey, String member)
    // {
    // return jedis.smove(srckey, dstkey, member);
    // }
    //
    // public Long zremrangeByScore(byte[] key, byte[] start, byte[] end)
    // {
    // return jedis.zremrangeByScore(key, start, end);
    // }
    //
    // public Set<byte[]> sdiff(byte[]... keys)
    // {
    // return jedis.sdiff(keys);
    // }
    //
    public Long del(String... keys)
    {
        Jedis jedis = jedisPool.getResource();
        try
        {
            return jedis.del(keys);
        }
        finally
        {
            jedisPool.returnResource(jedis);
        }
    }

    //
    // public void connect()
    // {
    // jedis.connect();
    // }
    //
    public Long zadd(String key, double score, String member)
    {
        Jedis jedis = jedisPool.getResource();
        try
        {
            return jedis.zadd(key, score, member);
        }
        finally
        {
            jedisPool.returnResource(jedis);
        }
    }

    //
    // public Long zcount(byte[] key, double min, double max)
    // {
    // return jedis.zcount(key, min, max);
    // }
    //
    // public Long publish(String channel, String message)
    // {
    // return jedis.publish(channel, message);
    // }
    //
    // public Long lpushx(String key, String string)
    // {
    // return jedis.lpushx(key, string);
    // }
    //
    // public String hmset(byte[] key, Map<byte[], byte[]> hash)
    // {
    // return jedis.hmset(key, hash);
    // }
    //
    // public String brpoplpush(String source, String destination, int timeout)
    // {
    // return jedis.brpoplpush(source, destination, timeout);
    // }
    //
    // public String lset(String key, long index, String value)
    // {
    // return jedis.lset(key, index, value);
    // }
    //
    // public Long zcard(byte[] key)
    // {
    // return jedis.zcard(key);
    // }
    //
    // public String auth(String password)
    // {
    // return jedis.auth(password);
    // }
    //
    // public String getrange(byte[] key, long startOffset, long endOffset)
    // {
    // return jedis.getrange(key, startOffset, endOffset);
    // }
    //
    // public String objectEncoding(String string)
    // {
    // return jedis.objectEncoding(string);
    // }
    //
    // public Long dbSize()
    // {
    // return jedis.dbSize();
    // }
    //
    // public String hmset(String key, Map<String, String> hash)
    // {
    // return jedis.hmset(key, hash);
    // }
    //
    // public List<String> configGet(String pattern)
    // {
    // return jedis.configGet(pattern);
    // }
    //
    // public String save()
    // {
    // return jedis.save();
    // }
    //
    // public Set<String> sunion(String... keys)
    // {
    // return jedis.sunion(keys);
    // }
    //
    // public Long append(byte[] key, byte[] value)
    // {
    // return jedis.append(key, value);
    // }
    //
    public Long zrank(String key, String member)
    {
        Jedis jedis = jedisPool.getResource();
        try
        {
            return jedis.zrank(key, member);
        }
        finally
        {
            jedisPool.returnResource(jedis);
        }
    }

    //
    // public String getrange(String key, long startOffset, long endOffset)
    // {
    // return jedis.getrange(key, startOffset, endOffset);
    // }
    //
    // public String info()
    // {
    // return jedis.info();
    // }
    //
    // public Long zadd(String key, Map<Double, String> scoreMembers)
    // {
    // return jedis.zadd(key, scoreMembers);
    // }
    //
    public Boolean sismember(String key, String member)
    {
        Jedis jedis = jedisPool.getResource();
        try
        {
            return jedis.sismember(key, member);
        }
        finally
        {
            jedisPool.returnResource(jedis);
        }
    }

    //
    // public String lpop(String key)
    // {
    // return jedis.lpop(key);
    // }
    //
    // public byte[] brpoplpush(byte[] source, byte[] destination, int timeout)
    // {
    // return jedis.brpoplpush(source, destination, timeout);
    // }
    //
    // public String select(int index)
    // {
    // return jedis.select(index);
    // }
    //
    // public Long move(String key, int dbIndex)
    // {
    // return jedis.move(key, dbIndex);
    // }
    //
    // public Long sadd(byte[] key, byte[]... members)
    // {
    // return jedis.sadd(key, members);
    // }
    //
    // public Long lrem(String key, long count, String value)
    // {
    // return jedis.lrem(key, count, value);
    // }
    //
    // public List<byte[]> mget(byte[]... keys)
    // {
    // return jedis.mget(keys);
    // }
    //
    // public byte[] lindex(byte[] key, int index)
    // {
    // return jedis.lindex(key, index);
    // }
    //
    // public Long hset(byte[] key, byte[] field, byte[] value)
    // {
    // return jedis.hset(key, field, value);
    // }
    //
    // public Long zinterstore(byte[] dstkey, ZParams params, byte[]... sets)
    // {
    // return jedis.zinterstore(dstkey, params, sets);
    // }
    //
    // public Set<byte[]> zrangeByScore(byte[] key, byte[] min, byte[] max)
    // {
    // return jedis.zrangeByScore(key, min, max);
    // }
    //
    public Long hlen(String key)
    {
        Jedis jedis = jedisPool.getResource();
        try
        {
            return jedis.hlen(key);
        }
        finally
        {
            jedisPool.returnResource(jedis);
        }
    }

    //
    // public Long zcount(byte[] key, byte[] min, byte[] max)
    // {
    // return jedis.zcount(key, min, max);
    // }
    //
    // public String quit()
    // {
    // return jedis.quit();
    // }
    //
    // public Set<byte[]> zrevrangeByScore(byte[] key, byte[] max, byte[] min, int offset, int count)
    // {
    // return jedis.zrevrangeByScore(key, max, min, offset, count);
    // }
    //
    public Long hincrBy(String key, String field, long value)
    {
        Jedis jedis = jedisPool.getResource();
        try
        {
            return jedis.hincrBy(key, field, value);
        }
        finally
        {
            jedisPool.returnResource(jedis);
        }
    }

    //
    // public byte[] get(byte[] key)
    // {
    // return jedis.get(key);
    // }
    //
    // public Boolean getbit(byte[] key, long offset)
    // {
    // return jedis.getbit(key, offset);
    // }
    //
    // public String spop(String key)
    // {
    // return jedis.spop(key);
    // }

    public Set<String> zrevrangeByScore(String key, double max, double min, int offset, int count)
    {
        Jedis jedis = jedisPool.getResource();
        try
        {
            return jedis.zrevrangeByScore(key, max, min, offset, count);
        }
        finally
        {
            jedisPool.returnResource(jedis);
        }
    }

    //
    // public Long setrange(String key, long offset, String value)
    // {
    // return jedis.setrange(key, offset, value);
    // }
    //
    // public String rename(String oldkey, String newkey)
    // {
    // return jedis.rename(oldkey, newkey);
    // }
    //
    public Long zrevrank(String key, String member)
    {
        Jedis jedis = jedisPool.getResource();
        try
        {
            return jedis.zrevrank(key, member);
        }
        finally
        {
            jedisPool.returnResource(jedis);
        }
    }

    //
    // public Long getDB()
    // {
    // return jedis.getDB();
    // }
    //
    // public List<byte[]> slowlogGetBinary()
    // {
    // return jedis.slowlogGetBinary();
    // }
    //
    // public Long zunionstore(byte[] dstkey, byte[]... sets)
    // {
    // return jedis.zunionstore(dstkey, sets);
    // }
    //
    // public Long strlen(byte[] key)
    // {
    // return jedis.strlen(key);
    // }
    //
    // public String bgrewriteaof()
    // {
    // return jedis.bgrewriteaof();
    // }
    //
    // public Set<byte[]> zrangeByScore(byte[] key, double min, double max)
    // {
    // return jedis.zrangeByScore(key, min, max);
    // }
    //
    // public Map<byte[], byte[]> hgetAll(byte[] key)
    // {
    // return jedis.hgetAll(key);
    // }
    //
    // public Long sort(String key, String dstkey)
    // {
    // return jedis.sort(key, dstkey);
    // }
    //
    // public List<String> blpop(int timeout, String... keys)
    // {
    // return jedis.blpop(timeout, keys);
    // }
    //
    // public String set(byte[] key, byte[] value)
    // {
    // return jedis.set(key, value);
    // }
    //
    public Long srem(String key, String... members)
    {
        Jedis jedis = jedisPool.getResource();
        try
        {
            return jedis.srem(key, members);
        }
        finally
        {
            jedisPool.returnResource(jedis);
        }
    }

    //
    public Set<String> smembers(String key)
    {
        Jedis jedis = jedisPool.getResource();
        try
        {
            return jedis.smembers(key);
        }
        finally
        {
            jedisPool.returnResource(jedis);
        }
    }

    //
    // public Long zinterstore(String dstkey, ZParams params, String... sets)
    // {
    // return jedis.zinterstore(dstkey, params, sets);
    // }
    //
    // public Set<Tuple> zrangeByScoreWithScores(byte[] key, byte[] min, byte[] max, int offset, int count)
    // {
    // return jedis.zrangeByScoreWithScores(key, min, max, offset, count);
    // }
    //
    // public List<Slowlog> slowlogGet()
    // {
    // return jedis.slowlogGet();
    // }
    //
    // public Long hdel(byte[] key, byte[]... fields)
    // {
    // return jedis.hdel(key, fields);
    // }
    //
    // public String configResetStat()
    // {
    // return jedis.configResetStat();
    // }
    //
    // public Set<Tuple> zrevrangeByScoreWithScores(String key, double max, double min)
    // {
    // return jedis.zrevrangeByScoreWithScores(key, max, min);
    // }
    //
    // public Long sunionstore(String dstkey, String... keys)
    // {
    // return jedis.sunionstore(dstkey, keys);
    // }
    //
    // public Long sort(byte[] key, byte[] dstkey)
    // {
    // return jedis.sort(key, dstkey);
    // }
    //
    // public Boolean scriptExists(String sha1)
    // {
    // return jedis.scriptExists(sha1);
    // }
    //
    // public String flushDB()
    // {
    // return jedis.flushDB();
    // }
    //
    public Set<Tuple> zrevrangeByScoreWithScores(String key, double max, double min, int offset, int count)
    {
        Jedis jedis = jedisPool.getResource();
        try
        {
            return jedis.zrevrangeByScoreWithScores(key, max, min, offset, count);
        }
        finally
        {
            jedisPool.returnResource(jedis);
        }
    }

    //
    // public Long zremrangeByRank(String key, long start, long end)
    // {
    // return jedis.zremrangeByRank(key, start, end);
    // }
    //
    // public String rpop(String key)
    // {
    // return jedis.rpop(key);
    // }
    //
    // public Set<String> zrevrangeByScore(String key, double max, double min)
    // {
    // return jedis.zrevrangeByScore(key, max, min);
    // }
    //
    // public Long ttl(byte[] key)
    // {
    // return jedis.ttl(key);
    // }
    //
    // public List<byte[]> sort(byte[] key)
    // {
    // return jedis.sort(key);
    // }
    //
    // public Long persist(String key)
    // {
    // return jedis.persist(key);
    // }
    //
    // public Object eval(String script, List<String> keys, List<String> args)
    // {
    // return jedis.eval(script, keys, args);
    // }
    //
    public Set<String> zrangeByScore(String key, String min, String max, int offset, int count)
    {
        Jedis jedis = jedisPool.getResource();
        try
        {
            return jedis.zrangeByScore(key, min, max, offset, count);
        }
        finally
        {
            jedisPool.returnResource(jedis);
        }
    }

    //
    // public String srandmember(String key)
    // {
    // return jedis.srandmember(key);
    // }
    //
    // public Long lpush(String key, String... strings)
    // {
    // return jedis.lpush(key, strings);
    // }
    //
    // public Set<byte[]> sunion(byte[]... keys)
    // {
    // return jedis.sunion(keys);
    // }
    //
    public Set<String> zrevrange(String key, long start, long end)
    {
        Jedis jedis = jedisPool.getResource();
        try
        {
            return jedis.zrevrange(key, start, end);
        }
        finally
        {
            jedisPool.returnResource(jedis);
        }

    }

    public Set<String> zrevrangeByScore(String key, String max, String min, int offset, int count)
    {
        Jedis jedis = jedisPool.getResource();
        try
        {
            return jedis.zrevrangeByScore(key, max, min, offset, count);
        }
        finally
        {
            jedisPool.returnResource(jedis);
        }
    }

    //
    // public Set<byte[]> zrangeByScore(byte[] key, double min, double max, int offset, int count)
    // {
    // return jedis.zrangeByScore(key, min, max, offset, count);
    // }
    //
    // public String watch(String... keys)
    // {
    // return jedis.watch(keys);
    // }
    //
    // public List<byte[]> hvals(byte[] key)
    // {
    // return jedis.hvals(key);
    // }
    //
    // public Long ttl(String key)
    // {
    // return jedis.ttl(key);
    // }
    //
    // public Long lpushx(byte[] key, byte[] string)
    // {
    // return jedis.lpushx(key, string);
    // }
    //
    // public List<byte[]> brpop(int timeout, byte[]... keys)
    // {
    // return jedis.brpop(timeout, keys);
    // }
    //
    // public Long rpush(byte[] key, byte[]... strings)
    // {
    // return jedis.rpush(key, strings);
    // }
    //
    // public byte[] spop(byte[] key)
    // {
    // return jedis.spop(key);
    // }
    //
    // public Set<byte[]> smembers(byte[] key)
    // {
    // return jedis.smembers(key);
    // }
    //
    // public Boolean setbit(String key, long offset, boolean value)
    // {
    // return jedis.setbit(key, offset, value);
    // }
    //
    // public Long setnx(String key, String value)
    // {
    // return jedis.setnx(key, value);
    // }
    //
    // public Set<byte[]> zrangeByScore(byte[] key, byte[] min, byte[] max, int offset, int count)
    // {
    // return jedis.zrangeByScore(key, min, max, offset, count);
    // }
    //
    // public Object eval(byte[] script, byte[] keyCount, byte[][] params)
    // {
    // return jedis.eval(script, keyCount, params);
    // }
    //
    // public String watch(byte[]... keys)
    // {
    // return jedis.watch(keys);
    // }
    //
    public Long scard(String key)
    {
        Jedis jedis = jedisPool.getResource();
        try
        {
            return jedis.scard(key);
        }
        finally
        {
            jedisPool.returnResource(jedis);
        }
    }

    //
    // public Long lpush(byte[] key, byte[]... strings)
    // {
    // return jedis.lpush(key, strings);
    // }
    //
    // public boolean isConnected()
    // {
    // return jedis.isConnected();
    // }
    //
    // public List<String> brpop(int timeout, String... keys)
    // {
    // return jedis.brpop(timeout, keys);
    // }
    //
    // public Object evalsha(String script)
    // {
    // return jedis.evalsha(script);
    // }
    //
    // public Set<String> zrangeByScore(String key, String min, String max)
    // {
    // return jedis.zrangeByScore(key, min, max);
    // }
    //
    // public List<String> sort(String key, SortingParams sortingParameters)
    // {
    // return jedis.sort(key, sortingParameters);
    // }
    //
    public Set<Tuple> zrangeByScoreWithScores(String key, double min, double max)
    {
        Jedis jedis = jedisPool.getResource();
        try
        {
            return jedis.zrangeByScoreWithScores(key, min, max);
        }
        finally
        {
            jedisPool.returnResource(jedis);
        }
    }

    //
    // public Long hincrBy(byte[] key, byte[] field, long value)
    // {
    // return jedis.hincrBy(key, field, value);
    // }
    //
    // public Boolean hexists(String key, String field)
    // {
    // return jedis.hexists(key, field);
    // }
    //
    // public String setex(byte[] key, int seconds, byte[] value)
    // {
    // return jedis.setex(key, seconds, value);
    // }
    //
    // public String slaveofNoOne()
    // {
    // return jedis.slaveofNoOne();
    // }
    //
    // public Long sort(byte[] key, SortingParams sortingParameters, byte[] dstkey)
    // {
    // return jedis.sort(key, sortingParameters, dstkey);
    // }
    //
    // public Long expireAt(byte[] key, long unixTime)
    // {
    // return jedis.expireAt(key, unixTime);
    // }
    //
    // public String bgsave()
    // {
    // return jedis.bgsave();
    // }
    //
    // public List<Long> scriptExists(byte[]... sha1)
    // {
    // return jedis.scriptExists(sha1);
    // }
    //
    // public Double zincrby(byte[] key, double score, byte[] member)
    // {
    // return jedis.zincrby(key, score, member);
    // }
    //
    // public List<byte[]> configGet(byte[] pattern)
    // {
    // return jedis.configGet(pattern);
    // }
    //
    // public Set<byte[]> keys(byte[] pattern)
    // {
    // return jedis.keys(pattern);
    // }
    //
    // public Long zrevrank(byte[] key, byte[] member)
    // {
    // return jedis.zrevrank(key, member);
    // }
    //
    public Set<String> zrangeByScore(String key, double min, double max)
    {
        Jedis jedis = jedisPool.getResource();
        try
        {
            return jedis.zrangeByScore(key, min, max);
        }
        finally
        {
            jedisPool.returnResource(jedis);
        }
    }

    //
    // public Boolean hexists(byte[] key, byte[] field)
    // {
    // return jedis.hexists(key, field);
    // }
    //
    // public byte[] objectEncoding(byte[] key)
    // {
    // return jedis.objectEncoding(key);
    // }
    //
    // public String mset(byte[]... keysvalues)
    // {
    // return jedis.mset(keysvalues);
    // }
    //
    // public Set<byte[]> zrevrange(byte[] key, int start, int end)
    // {
    // return jedis.zrevrange(key, start, end);
    // }
    //
    public Map<String, String> hgetAll(String key)
    {
        Jedis jedis = jedisPool.getResource();
        try
        {
            return jedis.hgetAll(key);
        }
        finally
        {
            jedisPool.returnResource(jedis);
        }
    }

    //
    // public List<byte[]> slowlogGetBinary(long entries)
    // {
    // return jedis.slowlogGetBinary(entries);
    // }
    //
    // public Set<Tuple> zrevrangeByScoreWithScores(byte[] key, byte[] max, byte[] min)
    // {
    // return jedis.zrevrangeByScoreWithScores(key, max, min);
    // }
    //
    // public Long zrem(byte[] key, byte[]... members)
    // {
    // return jedis.zrem(key, members);
    // }
    //
    // public byte[] scriptFlush()
    // {
    // return jedis.scriptFlush();
    // }
    //
    // public void psubscribe(JedisPubSub jedisPubSub, String... patterns)
    // {
    // jedis.psubscribe(jedisPubSub, patterns);
    // }
    //
    // public byte[] srandmember(byte[] key)
    // {
    // return jedis.srandmember(key);
    // }
    //
    // public byte[] configSet(byte[] parameter, byte[] value)
    // {
    // return jedis.configSet(parameter, value);
    // }
    //
    // public List<byte[]> blpop(int timeout, byte[]... keys)
    // {
    // return jedis.blpop(timeout, keys);
    // }
    //
    public Long expire(String key, int seconds)
    {
        Jedis jedis = jedisPool.getResource();
        try
        {
            return jedis.expire(key, seconds);
        }
        finally
        {
            jedisPool.returnResource(jedis);
        }
    }

    //
    // public Boolean exists(byte[] key)
    // {
    // return jedis.exists(key);
    // }
    //
    public Set<Tuple> zrangeByScoreWithScores(String key, String min, String max, int offset, int count)
    {
        Jedis jedis = jedisPool.getResource();
        try
        {
            return jedis.zrangeByScoreWithScores(key, min, max, offset, count);
        }
        finally
        {
            jedisPool.returnResource(jedis);
        }

    }

    //
    public Set<Tuple> zrangeByScoreWithScores(byte[] key, double min, double max, int offset, int count)
    {
        Jedis jedis = jedisPool.getResource();
        try
        {
            return jedis.zrangeByScoreWithScores(key, min, max, offset, count);
        }
        finally
        {
            jedisPool.returnResource(jedis);
        }
    }

    //
    // public void subscribe(JedisPubSub jedisPubSub, String... channels)
    // {
    // jedis.subscribe(jedisPubSub, channels);
    // }
    //
    // public Long sdiffstore(byte[] dstkey, byte[]... keys)
    // {
    // return jedis.sdiffstore(dstkey, keys);
    // }
    //
    // public Long lastsave()
    // {
    // return jedis.lastsave();
    // }
    //
    // public void disconnect()
    // {
    // jedis.disconnect();
    // }
    //
    // public List<Slowlog> slowlogGet(long entries)
    // {
    // return jedis.slowlogGet(entries);
    // }
    //
    public Set<String> zrevrangeByScore(String key, String max, String min)
    {
        Jedis jedis = jedisPool.getResource();
        try
        {
            return jedis.zrevrangeByScore(key, max, min);
        }
        finally
        {
            jedisPool.returnResource(jedis);
        }
    }

    //
    // public String rpoplpush(String srckey, String dstkey)
    // {
    // return jedis.rpoplpush(srckey, dstkey);
    // }
    //
    // public Long hlen(byte[] key)
    // {
    // return jedis.hlen(key);
    // }
    //
    // public String lset(byte[] key, int index, byte[] value)
    // {
    // return jedis.lset(key, index, value);
    // }
    //
    // public Long zunionstore(byte[] dstkey, ZParams params, byte[]... sets)
    // {
    // return jedis.zunionstore(dstkey, params, sets);
    // }
    //
    // public Set<Tuple> zrangeByScoreWithScores(byte[] key, byte[] min, byte[] max)
    // {
    // return jedis.zrangeByScoreWithScores(key, min, max);
    // }
    //
    // public String mset(String... keysvalues)
    // {
    // return jedis.mset(keysvalues);
    // }
    //
    // public String substr(String key, int start, int end)
    // {
    // return jedis.substr(key, start, end);
    // }
    //
    // public Double zscore(byte[] key, byte[] member)
    // {
    // return jedis.zscore(key, member);
    // }
    //
    // public String rename(byte[] oldkey, byte[] newkey)
    // {
    // return jedis.rename(oldkey, newkey);
    // }
    //
    public Long zcard(String key)
    {
        Jedis jedis = jedisPool.getResource();
        try
        {
            return jedis.zcard(key);
        }
        finally
        {
            jedisPool.returnResource(jedis);
        }
    }

    //
    // public Long renamenx(String oldkey, String newkey)
    // {
    // return jedis.renamenx(oldkey, newkey);
    // }
    //
    // public byte[] scriptKill()
    // {
    // return jedis.scriptKill();
    // }
    //
    // public String echo(String string)
    // {
    // return jedis.echo(string);
    // }
    //
    // public Long llen(byte[] key)
    // {
    // return jedis.llen(key);
    // }
    //
    // public Set<Tuple> zrevrangeByScoreWithScores(byte[] key, byte[] max, byte[] min, int offset, int count)
    // {
    // return jedis.zrevrangeByScoreWithScores(key, max, min, offset, count);
    // }
    //
    // public byte[] rpop(byte[] key)
    // {
    // return jedis.rpop(key);
    // }
    //
    public String flushAll()
    {
        Jedis jedis = jedisPool.getResource();
        try
        {
            return jedis.flushAll();
        }
        finally
        {
            jedisPool.returnResource(jedis);
        }
    }

    //
    // public Long sinterstore(byte[] dstkey, byte[]... keys)
    // {
    // return jedis.sinterstore(dstkey, keys);
    // }
    //
    // public Long append(String key, String value)
    // {
    // return jedis.append(key, value);
    // }
    //
    // public Long expire(byte[] key, int seconds)
    // {
    // return jedis.expire(key, seconds);
    // }
    //
    public Double zscore(String key, String member)
    {
        Jedis jedis = jedisPool.getResource();
        try
        {
            return jedis.zscore(key, member);
        }
        finally
        {
            jedisPool.returnResource(jedis);
        }
    }

    //
    // public Long del(byte[]... keys)
    // {
    // return jedis.del(keys);
    // }
    //
    // public Set<String> keys(String pattern)
    // {
    // return jedis.keys(pattern);
    // }
    //
    public Set<String> zrange(String key, long start, long end)
    {
        Jedis jedis = jedisPool.getResource();
        try
        {
            return jedis.zrange(key, start, end);
        }
        finally
        {
            jedisPool.returnResource(jedis);
        }
    }

    //
    // public List<String> mget(String... keys)
    // {
    // return jedis.mget(keys);
    // }
    //
    // public Set<String> sdiff(String... keys)
    // {
    // return jedis.sdiff(keys);
    // }
    //
    // public List<Object> pipelined(PipelineBlock jedisPipeline)
    // {
    // return jedis.pipelined(jedisPipeline);
    // }
    //
    // public Long zcount(String key, String min, String max)
    // {
    // return jedis.zcount(key, min, max);
    // }
    //
    // public Set<byte[]> zrange(byte[] key, int start, int end)
    // {
    // return jedis.zrange(key, start, end);
    // }
    //
    // public String slaveof(String host, int port)
    // {
    // return jedis.slaveof(host, port);
    // }
    //
    // public List<byte[]> hmget(byte[] key, byte[]... fields)
    // {
    // return jedis.hmget(key, fields);
    // }
    //
    // public byte[] echo(byte[] string)
    // {
    // return jedis.echo(string);
    // }
    //
    // public Set<Tuple> zrangeWithScores(String key, long start, long end)
    // {
    // return jedis.zrangeWithScores(key, start, end);
    // }
    //
    // public Object eval(String script)
    // {
    // return jedis.eval(script);
    // }
    //
    // public Long zrank(byte[] key, byte[] member)
    // {
    // return jedis.zrank(key, member);
    // }
    //
    // public Transaction multi()
    // {
    // return jedis.multi();
    // }
    //
    // public Long zremrangeByScore(String key, double start, double end)
    // {
    // return jedis.zremrangeByScore(key, start, end);
    // }
    //
    // public Set<Tuple> zrevrangeWithScores(byte[] key, int start, int end)
    // {
    // return jedis.zrevrangeWithScores(key, start, end);
    // }
    //
    // public Long zadd(byte[] key, double score, byte[] member)
    // {
    // return jedis.zadd(key, score, member);
    // }
    //
    // public String type(String key)
    // {
    // return jedis.type(key);
    // }
    //
    public Long incr(String key)
    {
        Jedis jedis = jedisPool.getResource();
        try
        {
            return jedis.incr(key);
        }
        finally
        {
            jedisPool.returnResource(jedis);
        }
    }

    //
    // public Boolean sismember(byte[] key, byte[] member)
    // {
    // return jedis.sismember(key, member);
    // }
    //
    public Set<Tuple> zrevrangeByScoreWithScores(String key, String max, String min, int offset, int count)
    {
        Jedis jedis = jedisPool.getResource();
        try
        {
            return jedis.zrevrangeByScoreWithScores(key, max, min, offset, count);
        }
        finally
        {
            jedisPool.returnResource(jedis);
        }
    }

    //
    // public Set<Tuple> zrangeByScoreWithScores(byte[] key, double min, double max)
    // {
    // return jedis.zrangeByScoreWithScores(key, min, max);
    // }
    //
    // public Long zinterstore(byte[] dstkey, byte[]... sets)
    // {
    // return jedis.zinterstore(dstkey, sets);
    // }
    //
    // public void subscribe(BinaryJedisPubSub jedisPubSub, byte[]... channels)
    // {
    // jedis.subscribe(jedisPubSub, channels);
    // }
    //
    // public List<byte[]> lrange(byte[] key, int start, int end)
    // {
    // return jedis.lrange(key, start, end);
    // }
    //
    // public List<String> hmget(String key, String... fields)
    // {
    // return jedis.hmget(key, fields);
    // }
    //
    // public byte[] hget(byte[] key, byte[] field)
    // {
    // return jedis.hget(key, field);
    // }
    //
    // public Long objectIdletime(byte[] key)
    // {
    // return jedis.objectIdletime(key);
    // }
    //
    // public String ltrim(String key, long start, long end)
    // {
    // return jedis.ltrim(key, start, end);
    // }
    //
    // public Long rpushx(String key, String string)
    // {
    // return jedis.rpushx(key, string);
    // }
    //
    // public Set<byte[]> hkeys(byte[] key)
    // {
    // return jedis.hkeys(key);
    // }
    //
    // public Set<String> sinter(String... keys)
    // {
    // return jedis.sinter(keys);
    // }
    //
    // public String shutdown()
    // {
    // return jedis.shutdown();
    // }
    //
    // public Set<Tuple> zrevrangeByScoreWithScores(byte[] key, double max, double min)
    // {
    // return jedis.zrevrangeByScoreWithScores(key, max, min);
    // }
    //
    // public Object evalsha(String sha1, List<String> keys, List<String> args)
    // {
    // return jedis.evalsha(sha1, keys, args);
    // }

    public Long zrem(String key, String... members)
    {
        Jedis jedis = jedisPool.getResource();
        try
        {
            return jedis.zrem(key, members);
        }
        finally
        {
            jedisPool.returnResource(jedis);
        }
    }

    //
    // public Long linsert(byte[] key, BinaryClient.LIST_POSITION where, byte[] pivot, byte[] value)
    // {
    // return jedis.linsert(key, where, pivot, value);
    // }
    //
    // public byte[] scriptLoad(byte[] script)
    // {
    // return jedis.scriptLoad(script);
    // }
    //
    // public Long objectRefcount(byte[] key)
    // {
    // return jedis.objectRefcount(key);
    // }
    //
    // public byte[] randomBinaryKey()
    // {
    // return jedis.randomBinaryKey();
    // }
    //
    // public Long setnx(byte[] key, byte[] value)
    // {
    // return jedis.setnx(key, value);
    // }
    //
    // public Set<String> hkeys(String key)
    // {
    // return jedis.hkeys(key);
    // }
    //
    public Set<Tuple> zrangeByScoreWithScores(String key, double min, double max, int offset, int count)
    {
        Jedis jedis = jedisPool.getResource();
        try
        {
            return jedis.zrangeByScoreWithScores(key, min, max, offset, count);
        }
        finally
        {
            jedisPool.returnResource(jedis);
        }

    }

    //
    // public List<Object> multi(TransactionBlock jedisTransaction)
    // {
    // return jedis.multi(jedisTransaction);
    // }
    //
    public String hget(String key, String field)
    {
        Jedis jedis = jedisPool.getResource();
        try
        {
            return jedis.hget(key, field);
        }
        finally
        {
            jedisPool.returnResource(jedis);
        }

    }

    //
    // public byte[] getSet(byte[] key, byte[] value)
    // {
    // return jedis.getSet(key, value);
    // }
    //
    // public Set<Tuple> zrangeWithScores(byte[] key, int start, int end)
    // {
    // return jedis.zrangeWithScores(key, start, end);
    // }
    //
    // public Long strlen(String key)
    // {
    // return jedis.strlen(key);
    // }
    //
    // public Long objectRefcount(String string)
    // {
    // return jedis.objectRefcount(string);
    // }
    //
    // public String ltrim(byte[] key, int start, int end)
    // {
    // return jedis.ltrim(key, start, end);
    // }
    //
    // public byte[] slowlogReset()
    // {
    // return jedis.slowlogReset();
    // }
    //
    // public Long incrBy(byte[] key, long integer)
    // {
    // return jedis.incrBy(key, integer);
    // }
    //
    // public Long lrem(byte[] key, int count, byte[] value)
    // {
    // return jedis.lrem(key, count, value);
    // }
    //
    // public void monitor(JedisMonitor jedisMonitor)
    // {
    // jedis.monitor(jedisMonitor);
    // }
    //
    // public byte[] rpoplpush(byte[] srckey, byte[] dstkey)
    // {
    // return jedis.rpoplpush(srckey, dstkey);
    // }
    //
    // public Long incrBy(String key, long integer)
    // {
    // return jedis.incrBy(key, integer);
    // }
    //
    // public Long zremrangeByScore(String key, String start, String end)
    // {
    // return jedis.zremrangeByScore(key, start, end);
    // }
    //
    // public Long zadd(byte[] key, Map<Double, byte[]> scoreMembers)
    // {
    // return jedis.zadd(key, scoreMembers);
    // }
    //
    // public Long renamenx(byte[] oldkey, byte[] newkey)
    // {
    // return jedis.renamenx(oldkey, newkey);
    // }
    //
    // public String debug(DebugParams params)
    // {
    // return jedis.debug(params);
    // }
    //
    // public long slowlogLen()
    // {
    // return jedis.slowlogLen();
    // }
    //
    public Long sadd(String key, String... members)
    {
        Jedis jedis = jedisPool.getResource();
        try
        {
            return jedis.sadd(key, members);
        }
        finally
        {
            jedisPool.returnResource(jedis);
        }
    }

    //
    // public Long publish(byte[] channel, byte[] message)
    // {
    // return jedis.publish(channel, message);
    // }
    //
    // public Long sinterstore(String dstkey, String... keys)
    // {
    // return jedis.sinterstore(dstkey, keys);
    // }
    //
    // public Set<Tuple> zrangeByScoreWithScores(String key, String min, String max)
    // {
    // return jedis.zrangeByScoreWithScores(key, min, max);
    // }
    //
    // public Set<Tuple> zrevrangeByScoreWithScores(byte[] key, double max, double min, int offset, int count)
    // {
    // return jedis.zrevrangeByScoreWithScores(key, max, min, offset, count);
    // }
    //
    public Long hset(String key, String field, String value)
    {
        Jedis jedis = jedisPool.getResource();
        try
        {
            return jedis.hset(key, field, value);
        }
        finally
        {
            jedisPool.returnResource(jedis);
        }

    }

    //
    // public String type(byte[] key)
    // {
    // return jedis.type(key);
    // }
    //
    // public Long decrBy(byte[] key, long integer)
    // {
    // return jedis.decrBy(key, integer);
    // }
    //
    public List<String> lrange(String key, long start, long end)
    {
        Jedis jedis = jedisPool.getResource();
        try
        {
            return jedis.lrange(key, start, end);
        }
        finally
        {
            jedisPool.returnResource(jedis);
        }
    }

    //
    // public Long decr(byte[] key)
    // {
    // return jedis.decr(key);
    // }
    //
    // public String randomKey()
    // {
    // return jedis.randomKey();
    // }
    //
    // public Set<Tuple> zrevrangeWithScores(String key, long start, long end)
    // {
    // return jedis.zrevrangeWithScores(key, start, end);
    // }
    //
    // public Long sunionstore(byte[] dstkey, byte[]... keys)
    // {
    // return jedis.sunionstore(dstkey, keys);
    // }
    //
    // public String unwatch()
    // {
    // return jedis.unwatch();
    // }
    //
    public String get(String key)
    {
        Jedis jedis = jedisPool.getResource();
        try
        {
            return jedis.get(key);
        }
        finally
        {
            jedisPool.returnResource(jedis);
        }
    }

    public <T> T get(String key, Class<T> classz)
    {
        Jedis jedis = jedisPool.getResource();
        try
        {
            String json = jedis.get(key);
            return StringUtils.isEmpty(json) ? null : JsonConverter.parse(json, classz);
        }
        finally
        {
            jedisPool.returnResource(jedis);
        }
    }

    //
    // public Set<byte[]> zrevrangeByScore(byte[] key, byte[] max, byte[] min)
    // {
    // return jedis.zrevrangeByScore(key, max, min);
    // }
    //
    // public Long objectIdletime(String string)
    // {
    // return jedis.objectIdletime(string);
    // }
    //
    // public Long srem(byte[] key, byte[]... member)
    // {
    // return jedis.srem(key, member);
    // }
    //
    // public Long move(byte[] key, int dbIndex)
    // {
    // return jedis.move(key, dbIndex);
    // }
    //
    // public Long smove(byte[] srckey, byte[] dstkey, byte[] member)
    // {
    // return jedis.smove(srckey, dstkey, member);
    // }
    //
    // public Client getClient()
    // {
    // return jedis.getClient();
    // }
    //
    public Boolean exists(String key)
    {
        Jedis jedis = jedisPool.getResource();
        try
        {
            return jedis.exists(key);
        }
        finally
        {
            jedisPool.returnResource(jedis);
        }
    }
}
