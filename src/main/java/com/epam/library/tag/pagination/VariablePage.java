package com.epam.library.tag.pagination;

public class VariablePage {
    public static final String SPLIT = " ";

    /** Shows page number */
    private int showPageNum;

    /** Connection URL */
    private String url;

    /** Current page number */
    private int pageNo;

    /** Total page number */
    private int totalPage = 1;

    /** Total number of articles */
    private int totalSum;

    /** Number of items per page */
    private int pageSize;

    public VariablePage(int showPageNum, String url, int pageNo, int totalSum, int pageSize) {
        this.showPageNum = showPageNum;
        this.url = url;
        this.pageNo = Math.max(pageNo, 1);
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
