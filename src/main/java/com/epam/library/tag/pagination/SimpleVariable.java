package com.epam.library.tag.pagination;

public class SimpleVariable {
    /**
     *Show page number
     */
    protected int showPageNum = 10;
    /**
     *Connection URL
     */
    protected String url = null;
    /**
     *Current page number
     */
    protected int pageNo = 1;
    /**
     *Total page number
     */
    protected int totalPage = 1;
    /**
     *Total number of articles
     */
    protected int totalSum = 0;
    /**
     *Number of items per page
     */
    protected int pageSize = 10;

    public SimpleVariable(int showPageNum, String url, int pageNo, int totalSum, int pageSize) {
        this.showPageNum = showPageNum;
        this.url = url;
        this.pageNo = pageNo;
        this.totalSum = totalSum;
        this.pageSize = pageSize;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalSum() {
        return totalSum;
    }

    public void setTotalSum(int totalSum) {
        this.totalSum = totalSum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getShowPageNum() {
        return showPageNum;
    }

    public void setShowPageNum(int showPageNum) {
        this.showPageNum = showPageNum;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }
}
