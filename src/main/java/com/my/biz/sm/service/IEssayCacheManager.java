package com.my.biz.sm.service;

import java.util.Date;
import java.util.Set;

import redis.clients.jedis.Tuple;

import com.my.biz.sm.commons.page.PageParam;
import com.my.biz.sm.entity.EssayInfo;

public interface IEssayCacheManager
{
    public EssayInfo getEssayInfoById(Integer id);

    /**
     * 添加Cache  zadd
     * @param creatorId
     * @param id
     * @param time
     */
    public void zaddEssayInfo(Integer id, Date time);

    /**
     * 删除Cache zrem
     * @param essayInfo
     */
    public  void zremEssayInfo(EssayInfo essayInfo);

    /**
     * 添加Cache
     * @param essayInfo
     * @param isClean
     */
    public void flushEssayInfo(EssayInfo essayInfo);

    /**
     * 删除
     * @param essayId
     */
    public void clearEssayInfo(Long essayId);

    
    Set<Tuple> getEssayInfoIds(Long cursor, int pageSize);

    Set<Long> getEssayInfoSetId(Long id, PageParam pageParam, boolean asc);
    
    
    
    
}
