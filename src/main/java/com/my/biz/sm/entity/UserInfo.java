package com.my.biz.sm.entity;

import java.io.Serializable;
import java.util.Date;

public class UserInfo implements Serializable
{
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -7757070152016313935L;

    private Integer id;

    private String userName;

    private String passWord;

    private Date createTime;

    private Date lastUpdate;

    private Integer status;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getPassWord()
    {
        return passWord;
    }

    public void setPassWord(String passWord)
    {
        this.passWord = passWord == null ? null : passWord.trim();
    }

    public Date getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }

    public Date getLastUpdate()
    {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate)
    {
        this.lastUpdate = lastUpdate;
    }

    public Integer getStatus()
    {
        return status;
    }

    public void setStatus(Integer status)
    {
        this.status = status;
    }
}
