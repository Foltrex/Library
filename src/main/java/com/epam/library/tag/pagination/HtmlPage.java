package com.epam.library.tag.pagination;

public class HtmlPage extends Page {

    public HtmlPage(VariablePage variablePage) {
        super(variablePage);
    }

    @Override
    public String pagination() {
        //If paginated; and the number of pages is less than or equal to the number of pages to display
        if (variablePage.getTotalPage() > 1 && variablePage.getTotalPage() <= variablePage.getShowPageNum()) {
            return displayAll();
            //If the number of page breaks: greater than the number of displayed pages
        } else if (variablePage.getTotalPage() > 1) {
            return displayCompositeContent(variablePage);
        } else {
            return "1";
        }
    }

    private String displayCompositeContent(VariablePage variablePage) {
        if (variablePage.getPageNo() == 1) { // the current page is equal to the first page
            return fromFirstPagePrint();
        } else if (variablePage.getPageNo() == variablePage.getTotalPage()) { // the current page is equal to the last page
            return fromLastPagePrint();
        } else { // if the current page is not the first page or the last page
            return displayCentralPages(variablePage);
        }
    }

    private String displayCentralPages(VariablePage variablePage) {
        if (variablePage.getShowPageNum() % 2 == 0) {
            // page number can be divided equally
             return paginateOfEvenNumberOfPagesWithCutContent(variablePage);
        } else {
            // when the number of printed pages is not even:
             return paginateOfOddNumberOfPagesWithCutContent(variablePage);
        }
    }

    private String paginateOfEvenNumberOfPagesWithCutContent(VariablePage variablePage) {
        StringBuilder pagination = new StringBuilder();
        int halfOfPrintedPaginationLinks = variablePage.getShowPageNum() / 2;
        if (variablePage.getPageNo() >= halfOfPrintedPaginationLinks) {
            int firstIndexOfPrintedPaginationLinks = variablePage.getPageNo() - halfOfPrintedPaginationLinks;
            if (variablePage.getPageNo() + halfOfPrintedPaginationLinks >= variablePage.getTotalPage()) {
                return fromLastPagePrint();
            } else {
                if (firstIndexOfPrintedPaginationLinks == 0) {
                    firstIndexOfPrintedPaginationLinks = 1;
                }

                fillPaginationList(pagination, variablePage, firstIndexOfPrintedPaginationLinks);
            }
        } else {
            return fromFirstPagePrint();
        }

        return pagination.toString();
    }

    private String paginateOfOddNumberOfPagesWithCutContent(VariablePage variablePage) {
        StringBuilder pagination = new StringBuilder();
        int halfOfPrintedPaginationLinks = variablePage.getShowPageNum() / 2 + 1;
        if (variablePage.getPageNo() >= halfOfPrintedPaginationLinks && variablePage.getPageNo() + halfOfPrintedPaginationLinks < variablePage.getTotalPage()) {
            int firstIndexOfPrintedPaginationLinks = variablePage.getPageNo() - halfOfPrintedPaginationLinks + 1;
            fillPaginationList(pagination, variablePage, firstIndexOfPrintedPaginationLinks);
        } else if (variablePage.getPageNo() <= halfOfPrintedPaginationLinks) { // from page 1
            return fromFirstPagePrint();
        } else {
            return fromLastPagePrint();
        }

        return pagination.toString();
    }

    private void fillPaginationList(StringBuilder pagination, VariablePage variablePage, int firstIndex) {
        for (int i = firstIndex; i < variablePage.getShowPageNum() + firstIndex; i++) {
            if (variablePage.getPageNo() == i) { // if it is the current page: do not add connection URL
                pagination.append(buildSpan(i, variablePage)).append(VariablePage.SPLIT);
            } else {
                pagination.append(buildA(variablePage, i)).append(VariablePage.SPLIT);
            }
        }
    }

    @Override
    public String buildSpan(String text, VariablePage variablePage) {
        return "<span style=\"color:gray;\">" + text + "</span>";
    }

    @Override
    public String buildSpan(int num, VariablePage variablePage) {
        return buildSpan(String.valueOf(num), variablePage);
    }

    @Override
    public String buildA(String text, String url) {
        return "<a href=\"" + url + "\">" + text + "</a>";
    }

    @Override
    public String buildA(VariablePage variablePage, int num) {
        return ("<a href=\"" + variablePage.getUrl() + num + "\">" + num + "</a>");
    }
}
