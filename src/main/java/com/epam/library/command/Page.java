package com.epam.library.command;

import java.util.HashMap;
import java.util.Map;

public enum Page {
    LOGIN("index"),
    BOOK_DETAILS("bookDetails"),
    RENTAL_DETAILS("bookRentalDetails"),
    ERROR("errorPage"),
    BOOK_RENTALS("booksRentals"),
    READERS("readers"),
    LIBRARIANS("librarians"),
    AUTHORS("authors"),
    GENRES("genres"),
    BOOKS("books"),
    ABOUT("about");

    private final String name;

    private static final Map<String, Page> ELEMENTS = new HashMap<>();
    static {
        for (Page page: Page.values()) {
            ELEMENTS.put(page.name, page);
        }
    }

    Page(String name) {
        this.name = name;
    }

    public static Page valueOfName(String pageName) {
        return ELEMENTS.get(pageName);
    }

    public String getPath() {
        return ( !name.equals(LOGIN.name) ) ? String.format("/pages/%s.jsp", name) : String.format("/%s.jsp", name);
    }

    // here was some method like getPath
}
