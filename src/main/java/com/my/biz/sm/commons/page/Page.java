package com.my.biz.sm.commons.page;


public class Page
{
    private Integer pageNo = 1;
    private Integer pageSize = 20;
    private Integer total;

    public int getPageNo()
    {
        return pageNo;
    }

    public void setPageNo(int pageNo)
    {
        this.pageNo = pageNo;
    }

    public int getPageSize()
    {
        return pageSize;
    }

    public void setPageSize(int pageSize)
    {
        this.pageSize = pageSize;
    }

    public int getTotal()
    {
        return total;
    }

    public void setTotal(int total)
    {
        this.total = total;
    }

    @Override
    public String toString()
    {
        return String.format("Page [pageNo=%s, pageSize=%s, total=%s]", pageNo, pageSize, total);
    }

}
