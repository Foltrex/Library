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

    private static final String BAN_USER_COMMAND = "change_user_blocking";
    private static final String SHOW_READERS_COMMAND = "show_readers";
    private static final String SHOW_LIBRARIANS_COMMAND = "show_librarians";

    private static final String SHOW_AUTHORS_COMMAND = "show_authors";
    private static final String ADD_AUTHOR_COMMAND = "add_author";
    private static final String SHOW_AUTHOR_BOOKS_COMMAND = "show_author_books";

    private static final String SHOW_GENRES_COMMAND = "show_genres";
    private static final String ADD_GENRE_COMMAND = "add_genre";
    private static final String SHOW_GENRE_BOOKS_COMMAND = "show_genre_books";

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
            case BAN_USER_COMMAND:
                return new ChangeUserBlockingCommand(new AdminServiceImpl(factory));
            case SHOW_READERS_COMMAND:
                return new ShowUsersCommand(new AdminServiceImpl(factory), Role.READER);
            case SHOW_LIBRARIANS_COMMAND:
                return new ShowUsersCommand(new AdminServiceImpl(factory), Role.LIBRARIAN);
            case SHOW_AUTHORS_COMMAND:
                return new ShowAuthorsCommand(new AuthorServiceImpl(factory));
            case ADD_AUTHOR_COMMAND:
                return new AddAuthorCommand(new AuthorServiceImpl(factory));
            case SHOW_AUTHOR_BOOKS_COMMAND:
                return new ShowAuthorBooksCommand(new BookServiceImpl(factory));
            case SHOW_GENRES_COMMAND:
                return new ShowGenresCommand(new GenreServiceImpl(factory));
            case ADD_GENRE_COMMAND:
                return new AddGenreCommand(new GenreServiceImpl(factory));
            case SHOW_GENRE_BOOKS_COMMAND:
                return new ShowGenreBooksCommand(new BookServiceImpl(factory));
            default:
                throw new IllegalArgumentException("Unknown command = " + command);
        }
    }
}
