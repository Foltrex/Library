package com.epam.library.command.implementation;

import com.epam.library.command.Command;
import com.epam.library.command.CommandResult;
import com.epam.library.command.Page;
import com.epam.library.entity.Author;
import com.epam.library.entity.Book;
import com.epam.library.entity.Genre;
import com.epam.library.exception.PageCommandException;
import com.epam.library.exception.ServiceException;
import com.epam.library.service.BookService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class SaveBookToDatabaseCommand implements Command {

    private static final int RECORDS_PER_PAGE = 3;

    private final BookService service;

    public SaveBookToDatabaseCommand(BookService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest req) throws ServiceException, PageCommandException {
        Book book = extractBookFromRequest(req);
        service.saveBook(book);

        int currentPage = 1;
        List<Book> books = service.findPartOfBooks(currentPage, RECORDS_PER_PAGE);
        int totalRows = service.calculateBooksNumber();

        int numberOfPages = (int) Math.ceil(totalRows * 1.0 / RECORDS_PER_PAGE);

        req.setAttribute("books", books);
        req.setAttribute("currentPage", currentPage);
        req.setAttribute("numberOfPages", numberOfPages);

        return CommandResult.forward(Page.BOOKS.getName());
    }

    private Book extractBookFromRequest(HttpServletRequest req) {
        String bookIdParam = req.getParameter("bookId");
        Long bookId = bookIdParam != null && !bookIdParam.isEmpty() ? Long.valueOf(bookIdParam) : null;
        String bookTitle = req.getParameter("bookTitle");
        Long bookAuthorId = Long.valueOf(req.getParameter("bookAuthor"));
        int bookStock = Integer.parseInt(req.getParameter("bookStock"));
        Long bookGenreId = Long.valueOf(req.getParameter("bookGenre"));

        Author partiallyInitializedAuthor = Author.createAuthorWithOnlyIDField(bookAuthorId);
        Genre partiallyInitializedGenre = Genre.createGenreWithOnlyIDField(bookGenreId);
        return new Book(bookId, bookTitle, partiallyInitializedAuthor, bookStock, partiallyInitializedGenre);
    }
}
