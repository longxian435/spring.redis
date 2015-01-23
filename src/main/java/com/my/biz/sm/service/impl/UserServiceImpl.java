package com.my.biz.sm.service.impl;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.my.biz.sm.commons.page.PageConvertor;
import com.my.biz.sm.commons.page.PageData;
import com.my.biz.sm.commons.page.PageParam;
import com.my.biz.sm.db.UserInfoDao;
import com.my.biz.sm.db.UserInfoExample;
import com.my.biz.sm.entity.UserInfo;
import com.my.biz.sm.service.IUserCacheManager;
import com.my.biz.sm.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService
{
    private final static Logger log = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserInfoDao userInfoDao;
    
    @Autowired
    private IUserCacheManager cacheManager;

    public UserInfo getUserInfoById(Integer id)
    {
        //redis 计数量加1
        cacheManager.incrUserLoginCount(id);
        
        UserInfo userInfo = cacheManager.getUser(id);
        if (userInfo != null)
        {
            System.out.println("--查resid--->");
            return userInfo;
        }
        userInfo = userInfoDao.selectByPrimaryKey(id);
        if (userInfo != null)
        {
            System.out.println("--查数据库-->");
            cacheManager.flushUser(userInfo, false);
        }
        return userInfo;
    }

    public int addUserInfo(UserInfo userInfo)
    {
        Date now=new Date();
        userInfo.setCreateTime(now);
        userInfo.setLastUpdate(now);
        int falg=userInfoDao.insert(userInfo);
        userInfo.setId(falg);
        cacheManager.flushUser(userInfo, false);
        return falg;
    }

    @Override
    public PageData<UserInfo> findUserInfo(PageParam<UserInfo> param)
    {
        UserInfo userInfo = param.getP();
        PageBounds pb = PageConvertor.toPageBounds(param);
        UserInfoExample example = new UserInfoExample();
        if (userInfo != null && StringUtils.isNotEmpty(userInfo.getUserName()))
        {
            example.createCriteria().andUserNameEqualTo(userInfo.getUserName());
        }
        PageList<UserInfo> list = userInfoDao.selectByExample(example, pb);
        return PageConvertor.toPageData(list);
    }
    
    @Override
    public void delUserInfo(Integer id)
    {
        userInfoDao.deleteByPrimaryKey(id);
    }

}
