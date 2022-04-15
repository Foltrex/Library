package com.epam.library.extractor.parameter.name;

public enum AuthorRequestParameterName {
    ID("authorId"),
    NAME("authorName"),
    SURNAME("authorSurname");

    private final String name;

    AuthorRequestParameterName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
