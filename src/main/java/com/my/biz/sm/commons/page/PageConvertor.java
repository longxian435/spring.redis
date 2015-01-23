package com.my.biz.sm.commons.page;


import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.github.miemiedev.mybatis.paginator.domain.Paginator;

/**
 * 带记录总数的分页有关对象转换器，本类依赖mybatis-paginator-1.2.13包
 * 
 * @author Lion Hua
 * @date 2014年8月4日
 */
public class PageConvertor
{
    /**
     * 带返回总数的分页
     * 
     * @param page
     * @return
     * @author Lion Hua
     * @date 2014年8月4日
     */
    public static <T> PageBounds toPageBounds(PageParam<T> page)
    {
        PageBounds pb = new PageBounds(page.getPageNo(), page.getPageSize());
        return pb;
    }

    public static <T> PageData<T> toPageData(PageList<T> list)
    {
        Paginator paginator = list.getPaginator();
        return new PageData<T>(paginator.getPage(), paginator.getLimit(), paginator.getTotalCount(), list);
    }
}
