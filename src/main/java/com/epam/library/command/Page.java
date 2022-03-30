package com.epam.library.command;

public enum Page {
    LOGIN("/index.jsp"),
    BOOK_DETAILS("/pages/bookDetails.jsp"),
    RENTAL_DETAILS("/pages/bookRentalDetails.jsp"),
    ERROR("/pages/errorPage.jsp"),
    BOOK_RENTALS("/pages/booksRentals.jsp"),
    READERS("/pages/readers.jsp"),
    LIBRARIANS("/pages/librarians.jsp"),
    AUTHORS("/pages/authors.jsp"),
    GENRES("/pages/genres.jsp"),
    BOOKS("/pages/books.jsp"),
    ABOUT("/pages/about.jsp");

    private final String name;

    Page(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
