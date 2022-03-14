package com.epam.library.tag.pagination;

public class HtmlPage extends Page {

    public HtmlPage(VariablePage variablePage) {
        super(variablePage);
    }

    @Override
    public String pagination() {
        StringBuilder printNo = new StringBuilder();
        //If paginated; and the number of pages is less than or equal to the number of pages to display
        if (variablePage.totalPage > 1 && variablePage.totalPage <= variablePage.showPageNum) {
            return displayAll();
            //If the number of page breaks: greater than the number of displayed pages
        } else if (variablePage.totalPage > 1) {
            if (variablePage.pageNo == 1) { // the current page is equal to the first page
                return fromFirstPagePrint();
            } else if (variablePage.pageNo == variablePage.totalPage) { // the current page is equal to the last page
                return fromLastPagePrint();
            } else { // if the current page is not the first page or the last page
                if (variablePage.showPageNum % 2 == 0) { // page number can be divided equally
                    int print$No = variablePage.showPageNum / 2;
                    if (variablePage.pageNo >= print$No) {
                        int indexNoInt = variablePage.pageNo - print$No;
                        if (variablePage.pageNo + print$No >= variablePage.totalPage) {
                            return fromLastPagePrint();
                        } else {
                            if (indexNoInt == 0)
                                indexNoInt = 1;
                            for (int i = indexNoInt; i < (variablePage.showPageNum + indexNoInt); i++) {
                                if (i == variablePage.pageNo) { // if it is the current page: do not add connection URL
                                    printNo.append(i).append(variablePage.split);
                                } else {
                                    printNo.append(buildA(variablePage, i)).append(variablePage.split);
                                }
                            }
                        }
                    } else {
                        return fromFirstPagePrint();
                    }
                } else { // when the number of printed pages is not even:
                    int printNoInt = variablePage.showPageNum / 2 + 1;
                    if (variablePage.pageNo >= printNoInt && variablePage.pageNo + printNoInt < variablePage.totalPage) {
                        int indexNoInt = variablePage.pageNo - printNoInt + 1;
                        for (int i = indexNoInt; i < variablePage.showPageNum + indexNoInt; i++) {
                            if (variablePage.pageNo == i) { // if it is the current page: do not add connection URL
                                printNo.append(i).append(variablePage.split);
                            } else {
                                printNo.append(buildA(variablePage, i)).append(variablePage.split);
                            }
                        }
                    } else if (variablePage.pageNo <= printNoInt) { // from page 1
                        return fromFirstPagePrint();
                    } else {
                        return fromLastPagePrint();
                    }
                }
            }

            return (printNo.toString());
        } else {
            return "1";
        }
    }

    public String getBackpageNum() {
        if (variablePage.pageNo <= 1) {
            return buildSpan("previous", variablePage);
        } else {
            return buildA("previous page", variablePage.url + (variablePage.pageNo - 1));
        }
    }

    public String getNextpageNum() {
        if (variablePage.pageNo >= variablePage.totalPage) {
            return buildSpan("next page", variablePage);
        } else {
            return buildA("next page", variablePage.url + (variablePage.pageNo + 1));
        }
    }

    @Override
    public String buildSpan(String text, VariablePage variablePage) {
        return "<span style=\"color:gray;\">" + text + "</span>";
    }

    @Override
    public String buildA(String text, String url) {
        return "<a href=\"" + url + "\">" + text + "</a>";
    }

    @Override
    public String buildA(VariablePage variablePage, int num) {
        return ("<a href=\"" + variablePage.url + num + "\">" + num + "</a>");
    }
}
