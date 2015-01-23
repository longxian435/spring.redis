package com.my.biz.sm.service.impl;

import java.util.Date;
import java.util.Set;

import org.springframework.stereotype.Service;

import redis.clients.jedis.Tuple;

import com.my.biz.sm.commons.page.PageParam;
import com.my.biz.sm.commons.redis.JedisProxy;
import com.my.biz.sm.commons.util.CacheUtils;
import com.my.biz.sm.commons.util.CollectionUtils;
import com.my.biz.sm.commons.util.Pair;
import com.my.biz.sm.entity.EssayInfo;
import com.my.biz.sm.entity.UserInfo;
import com.my.biz.sm.service.IEssayCacheManager;

@Service("essayCacheManager")
public class EssayCacheManagerImpl implements IEssayCacheManager
{
    public static final String ESSAY_PREFIX = "essay_";

    public static final String ESSAY_ITEM = ESSAY_PREFIX + "i";

    public static final String ESSAY_ZSET = ESSAY_PREFIX + "z_s";

    @Override
    public EssayInfo getEssayInfoById(Integer id)
    {
        JedisProxy jedisProxy = JedisProxy.getLRUCache();
        EssayInfo essayInfo = jedisProxy.get(CacheUtils.genKey(ESSAY_ITEM, id), EssayInfo.class);
        return essayInfo;
    }

    @Override
    public void flushEssayInfo(EssayInfo essayInfo)
    {
        JedisProxy.getLRUCache().set(CacheUtils.genKey(ESSAY_ITEM, essayInfo.getId()), essayInfo);
    }

    @Override
    public void clearEssayInfo(Long essayId)
    {
        JedisProxy jedis = JedisProxy.getLRUCache();
        jedis.del(CacheUtils.genKey(ESSAY_ITEM, essayId));
    }

    @Override
    public void zaddEssayInfo(Integer id, Date time)
    {
        JedisProxy.getPersistCache().zadd(ESSAY_ZSET, time.getTime(), id.toString());
    }

    @Override
    public void zremEssayInfo(EssayInfo essayInfo)
    {
        JedisProxy.getPersistCache().zrem(ESSAY_ZSET, essayInfo.getId().toString());
    }

    @Override
    public Set<Tuple> getEssayInfoIds(Long cursor, int pageSize)
    {
        Pair<String, String> range = CacheUtils.genRangeByCursor(cursor == null ? null : cursor.doubleValue(), false);
        Set<Tuple> ids = JedisProxy.getPersistCache().zrevrangeByScoreWithScores(ESSAY_ZSET, range.first, range.second,
                0, pageSize);
        return ids;
    }

    @Override
    public Set<Long> getEssayInfoSetId(Long id, PageParam pageParam, boolean asc)
    {
       System.out.println(JedisProxy.getPersistCache().zcard(ESSAY_ZSET));
        
        Long cursor = pageParam.getCursor();
        Pair<String, String> range = CacheUtils.genRangeByCursor(cursor == null ? null : cursor.doubleValue(), asc);
        Set<String> ids=JedisProxy.getPersistCache().zrangeByScore(ESSAY_ZSET, range.first, range.second,
                pageParam.getOffset(), pageParam.getPageSize());
        return CollectionUtils.convertStrings2Longs(ids);  
    }
    
    
}
