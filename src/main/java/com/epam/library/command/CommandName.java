package com.epam.library.command;

public enum CommandName {
    LOGIN("login"),
    LOGOUT("logout"),
    CHANGE_LANGUAGE("change_language"),
    SHOW_BORROWS("show_borrows"),
    CHANGE_BORROW("change_borrow"),
    DELETE_BORROW("delete_borrow"),
    SAVE_BORROW("save_borrow"),
    SHOW_BOOKS("show_books"),
    BORROW_BOOK("borrow_book"),
    SEARCH_BOOK("search_book"),
    SHOW_BOOK_DETAILS("show_book_details"),
    SAVE_BOOK("save_book"),
    ADD_BOOK("add_book"),
    CHANGE_USER_BLOCKING("change_user_blocking"),
    SHOW_READERS("show_readers"),
    SHOW_LIBRARIANS("show_librarians"),
    SHOW_AUTHORS("show_authors"),
    ADD_AUTHOR("add_author"),
    SHOW_AUTHOR_BOOKS("show_author_books"),
    SHOW_GENRES("show_genres"),
    ADD_GENRE("add_genre"),
    SHOW_GENRE_BOOKS("show_genre_books");

    private final String name;

    CommandName(String name) {
        this.name = name;
    }

    public static CommandName valueOfName(String commandName) {
        commandName = commandName.toUpperCase();
        return CommandName.valueOf(commandName);
    }

    public String getCommandName() {
        return name;
    }

    public String getServletCommand(String servletName) {
        return String.format("/%s?command=%s", servletName, name);
    }
}
