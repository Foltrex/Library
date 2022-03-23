package com.epam.library.tag.pagination;

public abstract class Page {
    protected VariablePage variablePage;

    public Page(VariablePage variablePage) {
        this.variablePage = variablePage;
        calculateTotalPage();
    }

    public int getStartIndex() {
        return (getValidPageNum() - 1) * variablePage.getPageSize();
    }

    public String getBackPageNum() {
        if (variablePage.getPageNo() <= 1) {
            return buildSpan("&#8249;", variablePage);
        } else {
            return buildA("&#8249;", variablePage.getUrl() + (variablePage.getPageNo() - 1));
        }
    }

    public String getNextPageNum() {
        if (variablePage.getPageNo() >= variablePage.getTotalPage()) {
            return buildSpan("&#8250;", variablePage);
        } else {
            return buildA("&#8250;", variablePage.getUrl() + (variablePage.getPageNo() + 1));
        }
    }

    /**
     *Calculate total pages
     */
    private void calculateTotalPage() {
        if (variablePage.getTotalSum() % variablePage.getPageSize() == 0) {
            variablePage.setTotalPage(variablePage.getTotalSum() / variablePage.getPageSize());
        } else {
            variablePage.setTotalPage(variablePage.getTotalSum() / variablePage.getPageSize() + 1);
        }

        if (variablePage.getTotalPage() < variablePage.getPageNo()) {
            variablePage.setPageNo(variablePage.getTotalPage());
        } else if (variablePage.getPageNo() < 1) {
            variablePage.setPageNo(1);
        }
    }

    protected String displayAll() {
        StringBuilder sBuilder = new StringBuilder(10);
        sBuilder.append(VariablePage.SPLIT);
        for (int i = 1; i <= variablePage.getTotalPage(); i++) {
            if (variablePage.getPageNo() == i) {
                sBuilder.append(buildSpan(i, variablePage)).append(VariablePage.SPLIT);
            } else {
                sBuilder.append(buildA(variablePage, i)).append(VariablePage.SPLIT);
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
        for (int i = 1; i <= variablePage.getShowPageNum(); i++) {
            if (i == variablePage.getPageNo()) { // if it is the current page: do not add connection URL
                buffer.append(i).append(VariablePage.SPLIT);
            } else {
                buffer.append(buildA(variablePage, i)).append(VariablePage.SPLIT);
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
        int startPage = variablePage.getTotalPage() - (variablePage.getShowPageNum() - 1);
        for (int i = startPage; i <= variablePage.getTotalPage(); i++) {
            if (i == variablePage.getPageNo()) { // if it is the current page: do not add connection URL
                buffer.append(i).append(VariablePage.SPLIT);
            } else {
                buffer.append(buildA(variablePage, i)).append(VariablePage.SPLIT);
            }
        }
        return buffer.toString();
    }

    public String getFirstNo() {
        if (isExistsPagination()) {
            return buildA("&laquo;", variablePage.getUrl() + 1);
        } else {
            return buildSpan("&laquo;", variablePage);
        }
    }

    /**
     *Determine whether there is paging
     *
     */
    private boolean isExistsPagination() {
        return variablePage.getTotalSum() > 1 && variablePage.getTotalPage() > 1;
    }

    public String getLastNo() {
        if (isExistsPagination()) {
            return buildA("&raquo;", variablePage.getUrl() + variablePage.getTotalPage());
        } else {
            return buildSpan("&raquo;", variablePage);
        }
    }

    protected int getValidPageNum() {
        if (variablePage.getTotalPage() < variablePage.getPageNo()) {
            variablePage.setPageNo(variablePage.getTotalPage());
        } else if (variablePage.getPageNo() < 1) {
            variablePage.setPageNo(1);
        }

        return variablePage.getPageNo();
    }

    public VariablePage getPageContant() {
        return this.variablePage;
    }

    public abstract String buildSpan(String text, VariablePage variablePage);

    public abstract String buildSpan(int num, VariablePage variablePage);

    public abstract String buildA(String text, String url);

    public abstract String buildA(VariablePage variablePage, int num);

}
