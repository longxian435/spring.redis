package com.my.biz.sm.service;

import com.my.biz.sm.commons.page.PageData;
import com.my.biz.sm.commons.page.PageParam;
import com.my.biz.sm.entity.EssayInfo;
import com.my.biz.sm.entity.UserInfo;

public interface IEssayService
{
    /**
     * 添加
     * @param essayInfo
     * @return
     */
    public Integer addEssayInfo(EssayInfo essayInfo);

    /**
     * 修改
     * @param essayInfo
     */
    public void updateEssayInfo(EssayInfo essayInfo);

    /**
     * 跟椐id 查询
     * @param id
     * @return
     */
    public EssayInfo getEssayInfoMyId(Integer id);
    
    public PageData<EssayInfo> findEssayInfo(PageParam<EssayInfo> page);
}
