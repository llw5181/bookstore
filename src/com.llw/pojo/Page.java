package com.llw.pojo;

import java.util.List;

/**
 * Page是分页的模型对象
 *
 * @param <T> 是具体的模块的javaBean类
 */
public class Page<T> {

    /* 每页显示的记录条数 */
    public static final Integer PAGE_SIZE = 4;

    //当前页码 由客户端传递给服务端
    private Integer pageNo;
    //总页码数  数据库表中的的总记录条数/每页显示记录条数得出
    private Integer pageTotal;
    //总记录条数 由数据库DQL查询出表中所有的记录条数
    private Integer pageTotalCount;
    //每页显示的记录条数 由客户端页面布局决定，传递给服务端
    private Integer pageSize = PAGE_SIZE;
    //分页的当前页数据 由DQL语句的limit查询出
    private List<T> items;
    // 分页条的请求地址
    private String url;


    public Page(Integer pageNo, Integer pageTotal, Integer pageTotalCount, Integer pageSize, List<T> items, String url) {
        this.pageNo = pageNo;
        this.pageTotal = pageTotal;
        this.pageTotalCount = pageTotalCount;
        this.pageSize = pageSize;
        this.items = items;
        this.url = url;
    }

    public Page() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        /* 数据边界的有效检查 */
        if (pageNo < 1) {
            pageNo = 1;
        }
        if (pageNo > pageTotal) {
            pageNo = pageTotal;
        }
        this.pageNo = pageNo;

    }

    public Integer getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(Integer pageTotal) {
        this.pageTotal = pageTotal;
    }

    public Integer getPageTotalCount() {
        return pageTotalCount;
    }

    public void setPageTotalCount(Integer pageTotalCount) {
        this.pageTotalCount = pageTotalCount;
    }

    @Override
    public String toString() {
        return "Page{" +
                "pageNo=" + pageNo +
                ", pageTotal=" + pageTotal +
                ", pageTotalCount=" + pageTotalCount +
                ", pageSize=" + pageSize +
                ", items=" + items +
                ", url='" + url + '\'' +
                '}';
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

}
