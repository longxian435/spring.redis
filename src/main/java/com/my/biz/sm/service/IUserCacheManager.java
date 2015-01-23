package com.my.biz.sm.service;

import com.my.biz.sm.entity.UserInfo;

public interface IUserCacheManager
{
    /**
     * 获得用户信息
     * @param userId
     * @return
     */
    UserInfo getUser(Integer userId);

    /**
     * 保存获删除
     * @param userInfo
     * @param isClean
     */
    void flushUser(UserInfo userInfo, boolean isClean);

    //用户登录次数添加
    void incrUserLoginCount(Integer userId);

    //获取用户登次数
    int getUserLoginCount(Integer userId);
    
    /**
     * 用户举报
     * @param userId
     * @param count
     * @return
     */
    int increUserReportCount(Long userId, int count);

    /**
     * get 举报数
     * @param userId
     * @return
     */
    int getUserReportCount(Long userId);

    
}
