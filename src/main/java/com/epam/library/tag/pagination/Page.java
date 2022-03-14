package com.epam.library.tag.pagination;

public abstract class Page {
    protected VariablePage variablePage;

    public Page(VariablePage variablePage) {
        this.variablePage = variablePage;
        calculateTotalPage();
    }

    public int getStartIndex() {
        return (getValidpageNum() - 1) * variablePage.pageSize;
    }

    public String getBackpageNum() {
        if (variablePage.pageNo <= 1) {
            return buildSpan("previous", variablePage);
        } else {
            return buildA("previous page", variablePage.getUrl() + (variablePage.pageNo - 1));
        }
    }

    public String getNextpageNum() {
        if (variablePage.pageNo >= variablePage.totalPage) {
            return buildSpan("next page", variablePage);
        } else {
            return buildA("next page", variablePage.getUrl() + (variablePage.pageNo + 1));
        }
    }

    /**
     *Calculate total pages
     */
    private void calculateTotalPage() {
        if (variablePage.totalSum % variablePage.pageSize == 0) {
            variablePage.totalPage = variablePage.totalSum / variablePage.pageSize;
        } else {
            variablePage.totalPage = variablePage.totalSum / variablePage.pageSize + 1;
        }

        if (variablePage.totalPage < variablePage.pageNo) {
            variablePage.pageNo = variablePage.totalPage;
        } else if (variablePage.pageNo < 1) {
            variablePage.pageNo = 1;
        }
    }

    protected String displayAll() {
        StringBuilder sBuilder = new StringBuilder(10);
        sBuilder.append(variablePage.split);
        for (int i = 1; i <= variablePage.totalPage; i++) {
            if (i == variablePage.pageNo) {
                sBuilder.append(i).append(variablePage.split);
            } else {
                sBuilder.append(buildA(variablePage, i)).append(variablePage.split);
            }
        }
        return sBuilder.toString();
    }

    /**
     *Abstract pagination method
     *
     */
    public abstract String pagination();

    /**
     *Print from the first page
     *
     */
    protected final String fromFirstPagePrint() {
        StringBuilder buffer = new StringBuilder(100);
        for (int i = 1; i <= variablePage.showPageNum; i++) {
            if (i == variablePage.pageNo) { // if it is the current page: do not add connection URL
                buffer.append(i).append(variablePage.split);
            } else {
                buffer.append(buildA(variablePage, i)).append(variablePage.split);
            }
        }
        return buffer.toString();
    }

    /**
     *Start printing from the last page
     *
     */
    protected final String fromLastPagePrint() {
        StringBuilder buffer = new StringBuilder(100);
        int startPage = variablePage.totalPage - (variablePage.showPageNum - 1);
        for (int i = startPage; i <= variablePage.totalPage; i++) {
            if (i == variablePage.pageNo) { // if it is the current page: do not add connection URL
                buffer.append(i).append(variablePage.split);
            } else {
                buffer.append(buildA(variablePage, i)).append(variablePage.split);
            }
        }
        return buffer.toString();
    }

    public String getFirstNo() {
        if (isExistsPagination()) {
            return buildA("home page", variablePage.url + 1);
        } else {
            return buildSpan("homepage", variablePage);
        }
    }

    /**
     *Determine whether there is paging
     *
     */
    private boolean isExistsPagination() {
        return variablePage.totalSum > 1 && variablePage.totalPage > 1;
    }

    public String getLastNo() {
        if (isExistsPagination()) {
            return buildA("last page", variablePage.url + variablePage.totalPage);
        } else {
            return buildSpan("tail page", variablePage);
        }
    }

    protected int getValidpageNum() {
        if (variablePage.totalPage < variablePage.pageNo) {
            return variablePage.pageNo = variablePage.totalPage;
        } else if (variablePage.pageNo < 1) {
            return variablePage.pageNo = 1;
        } else {
            return variablePage.pageNo;
        }
    }

    public VariablePage getPageContant() {
        return this.variablePage;
    }

    public abstract String buildSpan(String text, VariablePage variablePage);

    public abstract String buildA(String text, String url);

    public abstract String buildA(VariablePage variablePage, int num);

    public String printModifyPageSize(int pageSize) {
        StringBuilder builder = new StringBuilder(100);
        builder.append("per page < input type = text 'id = PageSize' style = 'width: 20 px' maxlength='2' name='pageSize'");
        if (pageSize > 0) {
            builder.append(" value='").append(pageSize).append("' ");
        }
        builder.append("/ > bar");
        return builder.toString();
    }
}
