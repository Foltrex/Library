package com.epam.library.tag.pagination;

public class VariablePage extends SimpleVariable {
    protected String split = " ";
    protected String style = "class='page'";

    public VariablePage(int showPageNum, String url, int pageNo, int totalSum, int pageSize) {
        super(showPageNum, url, Math.max(pageNo, 1), totalSum, pageSize);
    }


    public String getSplit() {
        return split;
    }

    public void setSplit(String split) {
        this.split = split;
    }
}
