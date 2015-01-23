package com.my.biz.sm.commons.util;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Tuple;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.my.biz.sm.commons.page.PageParam;
import com.my.biz.sm.commons.string.StringUtil;

public abstract class CacheUtils
{
    private static final Logger log = LoggerFactory.getLogger(CacheUtils.class);
    private static final String SEPARATOR = "_";

    /**
     * 根据多个因子生成key的前缀字符串
     * 
     * @param factors
     *        生成因子
     */
    public static String genKey(Object... factors)
    {
        return StringUtil.join(SEPARATOR, Lists.transform(Lists.newArrayList(factors), new Function<Object, Object>()
        {
            @Override
            public Object apply(Object factor)
            {
                checkNotNull(factor);
                if (factor instanceof Date)
                {
                    return ((Date) factor).getTime();
                }
                return factor;
            }
        }).toArray());
    }

    /**
     * 生成符合Redis里Sorted Set数据结构的按范围搜索查询参数. 请参考zrange语法
     * 
     * @param cursor
     *        传进来的当前查询游标
     * @param asc
     *        如果是升序(score从小到大)则为true
     * @return 生成一个区间范围. 为(left, right]形式, 即左开右闭
     */
    public static Pair<String, String> genRangeByCursor(Double cursor, boolean asc)
    {
        Pair<String, String> range = new Pair<String, String>();
        String left = null;
        String right = null;
        if (asc)
        {
            if (cursor == null)
            {
                left = "-inf";
            }
            else
            {
                left = "(" + cursor;
            }
            right = "+inf";
        }
        else
        {
            if (cursor == null)
            {
                left = "+inf";
            }
            else
            {
                left = "(" + cursor;
            }
            right = "-inf";
        }
        range.setFirst(left);
        range.setSecond(right);
        return range;
    }

    /**
     * 对象查询工厂
     * 
     * @param <K>
     *        查询key值
     * @param <V>
     *        查询结果
     */
    public abstract static class ObjectFactory<K, V>
    {
        public abstract V get(K key);
    }

    /**
     * 从非cache中查询出来后刷新cache处理器
     * 
     * @param <V>
     *        最新对象
     */
    public abstract static class FlushCacheCallback<V>
    {
        public abstract void flush(V obj);
    }

    /**
     * 先从cache中取值, 如果cache中有, 直接返回, 如果没有, 再从db或者其它地方(数据真实存在的地方)取值, 并处理 该值, 如更新cache中的内容
     * 
     * @param key
     * @param cacheFactory
     * @param realFactory
     * @param flushCallback
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K, V> V getValue(K key, ObjectFactory<K, V> cacheFactory, ObjectFactory<K, V> realFactory,
            FlushCacheCallback<V> flushCallback)
    {
        V obj = cacheFactory.get(key);
        if (obj != null)
        {
            return obj;
        }
        obj = realFactory.get(key);
        if (obj != null)
        {
            flushCallback.flush(obj);
        }
        return obj;
    }

    /**
     * 从redis的Sorted Set列表中查询指定条数的数据. 因为每次从cache中取出的数据不一定满足业务要求, 所以要循环 遍历, 直到查找的数量达到要求的条数为止, 或者cache中不再包含任何数据.
     * 
     * @param pageParam
     *        里面必需包含两个属性: cursor 当前查询所依赖的偏移基数, 对应于 cache 中的 score值
     * @param cacheQuery
     *        基于pageParm里的 p, pageSize, cursor 属性, 从cache中查询指定条数的原始数据
     * @param transFun
     *        把从cache中取出来的数据中, 每个元素转换成我们业务需要的对象, 可以返回null
     * @param <T>
     *        最后返回数据的对象
     * @param <D>
     *        查询参数
     * @return
     */
    public static <T, D> List<T> paginationByCuror(PageParam<D> pageParam, ObjectFactory<D, Set<Tuple>> cacheQuery,
            Function<String, T> transFun)
    {
        int pageSize = pageParam.getPageSize();
        checkNotNull(pageParam.getCursor());
        List<T> result = Lists.newArrayListWithCapacity(pageSize);
        int counter = 0;
        while (result.size() < pageSize)
        {
            counter++;
            if (counter > 5)
            {
                log.warn("dirty data is too much, param: {}, bizName: {}", pageParam.getP(), cacheQuery.getClass());
                break;
            }
            Set<Tuple> ids = cacheQuery.get(pageParam.getP());
            if (ids.size() == 0)
            {
                break;
            }
            T item;
            for (Tuple id : ids)
            {
                try
                {
                    item = transFun.apply(id.getElement());
                    if (item != null)
                    {
                        result.add(item);
                    }
                }
                catch (Exception ex)
                {
                    log.debug("trans: {} fail, key is : {}, errorMsg is : {}", transFun.getClass(), id.getElement(),
                            ex.getMessage());
                }
                // 重置分页偏移
                pageParam.setCursor((long) id.getScore());
            }
        }
        return result;
    }

}
