package com.epam.library.extractor.parameter.name;

/** Request parameters associated with the {@link com.epam.library.entity.Genre} */
public enum GenreRequestParameterName {
    ID("genreId"),
    NAME("genreName");

    private final String name;

    GenreRequestParameterName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
