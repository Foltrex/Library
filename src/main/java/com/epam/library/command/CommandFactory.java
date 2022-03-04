package com.epam.library.command;

import com.epam.library.command.implementation.*;
import com.epam.library.dao.DaoHelperFactory;
import com.epam.library.entity.Role;
import com.epam.library.service.implementation.*;

public class CommandFactory {

    private static final String LOGIN_COMMAND = "login";
    private static final String LOGOUT_COMMAND = "logout";

    private static final String CHANGE_LOCALE_COMMAND = "change_language";

    private static final String SHOW_BORROWS_COMMAND = "show_borrows";
    private static final String CHANGE_BORROW_COMMAND = "change_borrow";
    private static final String DELETE_BORROW_COMMAND = "delete_borrow";
    private static final String SAVE_BORROW_COMMAND = "save_borrow";
    private static final String SHOW_BOOKS_COMMAND = "show_books";
    private static final String BORROW_BOOK_COMMAND = "borrow_book";
    private static final String SEARCH_BOOK_COMMAND = "search_book";

    private static final String SHOW_BOOK_DETAILS_COMMAND = "show_book_details";
    private static final String SAVE_BOOK_TO_DATABASE_COMMAND = "save_book_to_database";
    private static final String ADD_BOOK_COMMAND = "add_book";

    private static final String SHOW_READERS_COMMAND = "show_readers";
    private static final String SHOW_LIBRARIANS_COMMAND = "show_librarians";

    // TODO: the following commands to do through the dropdown menu
    private static final String ADD_AUTHORS_COMMAND = "add_author";
    private static final String ADD_GENRES_COMMAND = "add_genre";


    private final DaoHelperFactory factory = new DaoHelperFactory();


    public Command defineCommand(String command) {
        switch (command) {
            case LOGIN_COMMAND:
                return new LoginCommand(new UserServiceImpl(factory));
            case LOGOUT_COMMAND:
                return new LogoutCommand();
            case CHANGE_LOCALE_COMMAND:
                return new ChangeLocaleCommand();
            case SHOW_BORROWS_COMMAND:
                return new ShowBookBorrowsListCommand(new BookBorrowServiceImpl(factory));
            case CHANGE_BORROW_COMMAND:
                return new ChangeBorrowCommand(new BookBorrowServiceImpl(factory));
            case DELETE_BORROW_COMMAND:
                return new DeleteBorrowCommand(new BookBorrowServiceImpl(factory));
            case SAVE_BORROW_COMMAND:
                return new SaveBookBorrowToDatabaseCommand(new BookBorrowServiceImpl(factory));
            case SHOW_BOOK_DETAILS_COMMAND:
                return new ShowBookDetailsCommand
                        (new BookServiceImpl(factory), new AuthorServiceImpl(factory), new GenreServiceImpl(factory));
            case SHOW_BOOKS_COMMAND:
                return new ShowBooksCommand(new BookServiceImpl(factory));
            case BORROW_BOOK_COMMAND:
                return new BorrowBookCommand(new BookBorrowServiceImpl(factory));
            case SEARCH_BOOK_COMMAND:
                return new SearchBookCommand(new BookServiceImpl(factory));
            case SAVE_BOOK_TO_DATABASE_COMMAND:
                return new SaveBookToDatabaseCommand(new BookServiceImpl(factory));
            case ADD_BOOK_COMMAND:
                return new AddBookCommand(new AuthorServiceImpl(factory), new GenreServiceImpl(factory));
            case SHOW_READERS_COMMAND:
                return new ShowUsersCommand(new AdminServiceImpl(factory), Role.READER);
            case SHOW_LIBRARIANS_COMMAND:
                return new ShowUsersCommand(new AdminServiceImpl(factory), Role.LIBRARIAN);
            default:
                throw new IllegalArgumentException("Unknown command = " + command);
        }
    }
}
