package com.my.biz.sm.commons.page;

import java.io.Serializable;

/**
 * 数据库、内存分页对象. 包含当前页数据及分页信息，P为入参的类型。
 * 
 * @author: hualong
 * @date 2014年2月20日 上午10:05:40
 */
public class PageParam<P> implements Serializable
{
    private static final long serialVersionUID = 1L;

    public final static int DEFAULT_PAGE_No = 1;
    public final static int DEFAULT_PAGE_SIZE = 20;

    /**
     * 以Bean形式包装查询条件（不含参数：页号，每页记录数，总记录数，总分页数，是否有前一页，否有下一页）
     */
    private P p;

    /**
     * 跳转页数，页数是从第一页是从1开始计算的
     */
    private int pageNo = DEFAULT_PAGE_No;

    /**
     * 每页的记录数(每页尺寸)
     */
    private int pageSize = DEFAULT_PAGE_SIZE;

    /**
     * 总记录数
     */
    private int dataTotal = 0;

    private Long cursor;

    public PageParam()
    {

    }

    public PageParam(int pageNo, int pageSize)
    {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

    public PageParam(P p, int pageNo, int pageSize)
    {
        super();
        this.p = p;
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

    public PageParam(P p, int pageNo, int pageSize, Long cursor)
    {
        this(p, pageNo, pageSize);
        this.cursor = cursor;
    }

    public P getP()
    {
        return p;
    }

    public void setP(P p)
    {
        this.p = p;
    }

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

    public int getDataTotal()
    {
        return dataTotal;
    }

    public void setDataTotal(int dataTotal)
    {
        this.dataTotal = dataTotal;
    }

    public int getOffset(){
        return (getPageNo() - 1) * getPageSize();
    }

    public Long getCursor() {
        return cursor;
    }

    public void setCursor(Long cursor) {
        this.cursor = cursor;
    }

    @Override
    public String toString()
    {
        return "PageParam [p=" + p + ", pageNo=" + pageNo + ", pageSize=" + pageSize + ", dataTotal=" + dataTotal + "]";
    }

}
