package com.epam.library.extractor.parameter.name;

public enum BookRequestParameterName {
    ID("bookId"),
    TITLE("bookTitle"),
    STOCK("bookStock");

    private final String name;

    BookRequestParameterName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
