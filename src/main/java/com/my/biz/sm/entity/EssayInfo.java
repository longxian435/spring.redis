package com.my.biz.sm.entity;

import java.io.Serializable;
import java.util.Date;

public class EssayInfo implements Serializable
{

    /**
     * 1863874207377938117L
     */
    private static final long serialVersionUID = 1863874207377938117L;

    private Integer id;

    private String title;

    private String content;

    private Boolean status;

    private Date createTime;

    private Integer creatorId;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title == null ? null : title.trim();
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content == null ? null : content.trim();
    }

    public Boolean getStatus()
    {
        return status;
    }

    public void setStatus(Boolean status)
    {
        this.status = status;
    }

    public Date getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }

    public Integer getCreatorId()
    {
        return creatorId;
    }

    public void setCreatorId(Integer creatorId)
    {
        this.creatorId = creatorId;
    }
}
