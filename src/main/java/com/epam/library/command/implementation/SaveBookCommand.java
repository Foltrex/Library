package com.epam.library.command.implementation;

import com.epam.library.command.Command;
import com.epam.library.command.CommandResult;
import com.epam.library.command.Page;
import com.epam.library.command.Paginator;
import com.epam.library.entity.Author;
import com.epam.library.entity.Book;
import com.epam.library.entity.Genre;
import com.epam.library.exception.PageCommandException;
import com.epam.library.exception.ServiceException;
import com.epam.library.service.BookService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class SaveBookCommand implements Command {

    private final BookService service;
    private final Paginator paginator;

    public SaveBookCommand(BookService service) {
        this.service = service;
        this.paginator = new Paginator(service, 4);
    }

    @Override
    public CommandResult execute(HttpServletRequest req) throws ServiceException, PageCommandException {
        Book book = extractBookFromRequest(req);
        service.saveBook(book);

        int currentPage = paginator.findPageNo(req);
        List<Book> books = service.findPartOfBooks(currentPage, paginator.getRecordsPerPage());
        paginator.setPaginationParameters(req);
        req.setAttribute("books", books);

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
