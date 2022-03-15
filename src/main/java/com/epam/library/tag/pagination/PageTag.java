package com.epam.library.tag.pagination;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.io.Serial;

public class PageTag extends TagSupport {
    private int pageNo;
    private int pageSize = 10;
    private int totalSum;
    private int showPage = 10;
    private String url;

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalSum() {
        return totalSum;
    }

    public void setTotalSum(int totalSum) {
        this.totalSum = totalSum;
    }

    public int getShowPage() {
        return showPage;
    }

    public void setShowPage(int showPage) {
        this.showPage = showPage;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int doEndTag() throws JspException {
        if (pageSize == 0) {
            return TagSupport.SKIP_PAGE; // do not display paging
        } else if (pageSize > totalSum) {
            return TagSupport.SKIP_BODY; // do not display Pagination
        }
        JspWriter out = pageContext.getOut();

        try {
            if (!url.contains("pageNo")) {
                url += (url.contains("?") ? "&": "?") + "pageNo=";
            }

            url = pageContext.getServletContext().getContextPath() + url;
            VariablePage variablePage = new VariablePage(showPage, url, pageNo, totalSum, pageSize);
            Page page = new HtmlPage(variablePage);
            out.print(String.format("%s %s %s %s %s",
                    page.getFirstNo(),
                    page.getBackPageNum(),
                    page.pagination(),
                    page.getNextPageNum(),
                    page.getLastNo())
            );

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return super.doEndTag();
    }

    @Override
    public void release() {
        url = null;
        pageNo = 0;
        totalSum = 0;
        pageSize = 10;
        super.release();
    }
}
