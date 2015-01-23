package com.my.biz.sm.db;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.my.biz.sm.entity.EssayInfo;
import com.my.biz.sm.entity.UserInfo;

public interface EssayInfoDao
{
    int countByExample(EssayInfoExample example);

    int deleteByExample(EssayInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(EssayInfo record);

    int insertSelective(EssayInfo record);

    List<EssayInfo> selectByExample(EssayInfoExample example);

    PageList<EssayInfo> selectByExample(EssayInfoExample example, PageBounds pageBounds);

    EssayInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") EssayInfo record, @Param("example") EssayInfoExample example);

    int updateByExample(@Param("record") EssayInfo record, @Param("example") EssayInfoExample example);

    int updateByPrimaryKeySelective(EssayInfo record);

    int updateByPrimaryKey(EssayInfo record);
}
