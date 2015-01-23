package com.my.biz.sm.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Tuple;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.google.common.base.Function;
import com.my.biz.sm.commons.page.PageConvertor;
import com.my.biz.sm.commons.page.PageData;
import com.my.biz.sm.commons.page.PageParam;
import com.my.biz.sm.commons.util.CacheUtils;
import com.my.biz.sm.db.EssayInfoDao;
import com.my.biz.sm.db.EssayInfoExample;
import com.my.biz.sm.db.UserInfoExample;
import com.my.biz.sm.entity.EssayInfo;
import com.my.biz.sm.entity.UserInfo;
import com.my.biz.sm.service.IEssayCacheManager;
import com.my.biz.sm.service.IEssayService;

@Service("essayService")
public class IEssayServiceImpl implements IEssayService
{

    @Autowired
    private IEssayCacheManager essayCacheManager;

    @Autowired
    private EssayInfoDao essayInfoDao;

    @Override
    public Integer addEssayInfo(EssayInfo essayInfo)
    {
        Date now=new Date();
        essayInfo.setCreateTime(now);
        essayInfo.setStatus(true);
        Integer id = essayInfoDao.insert(essayInfo);
        essayCacheManager.zaddEssayInfo(id, now);
        return id;
    }

    @Override
    public void updateEssayInfo(EssayInfo essayInfo)
    {
        essayInfoDao.updateByPrimaryKeySelective(essayInfo);
    }

    @Override
    public EssayInfo getEssayInfoMyId(Integer id)
    {
        EssayInfo essayInfo = essayCacheManager.getEssayInfoById(id);
        if (essayInfo != null)
        {
            return essayInfo;
        }
        essayInfo = essayInfoDao.selectByPrimaryKey(id);
        if (essayInfoDao != null)
        {
            System.out.println("--查数据库-->");
            essayCacheManager.flushEssayInfo(essayInfo);
        }
        return essayInfo;
    }

    @Override
    public PageData<EssayInfo> findEssayInfo(PageParam<EssayInfo> page)
    {
        EssayInfo esayInfo = page.getP();
        PageBounds pb = PageConvertor.toPageBounds(page);
        EssayInfoExample example = new EssayInfoExample();
        if (esayInfo != null && StringUtils.isNotEmpty(esayInfo.getTitle()))
        {
            example.createCriteria().andTitleNotEqualTo(esayInfo.getTitle());
        }
        PageList<EssayInfo> list = essayInfoDao.selectByExample(example, pb);
        return PageConvertor.toPageData(list);
    }
    
//    @Override
    public PageData<EssayInfo> queryCacheEssayInfos(final PageParam<Long> pageParam)
    {
        List<EssayInfo> topics = CacheUtils.paginationByCuror(pageParam, new CacheUtils.ObjectFactory<Long, Set<Tuple>>()
        {
            @Override
            public Set<Tuple> get(Long userId)
            {
                return essayCacheManager.getEssayInfoIds(pageParam.getCursor(), pageParam.getPageSize());
            }
        }, new Function<String, EssayInfo>()
        {
            @Override
            public EssayInfo apply(String idStr)
            {
                EssayInfo topic = getEssayInfoMyId(Integer.valueOf(idStr));
                return topic;
            }
        });
        return new PageData<>(pageParam.getCursor(), topics);
    }

}
