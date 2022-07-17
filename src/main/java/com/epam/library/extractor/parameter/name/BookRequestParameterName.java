package com.epam.library.extractor.parameter.name;

/** Request parameters associated with the {@link com.epam.library.entity.Book} */
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
