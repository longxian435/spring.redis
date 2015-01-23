package com.my.biz.sm.service;


import com.my.biz.sm.commons.page.PageData;
import com.my.biz.sm.commons.page.PageParam;
import com.my.biz.sm.entity.UserInfo;

public interface UserService
{

    public UserInfo getUserInfoById(Integer id);

    public int  addUserInfo(UserInfo userInfo);
    
    public PageData<UserInfo> findUserInfo(PageParam<UserInfo> page);

    void delUserInfo(Integer id);

}
