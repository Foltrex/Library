package com.epam.library.command.implementation;

import com.epam.library.command.Command;
import com.epam.library.command.CommandResult;
import com.epam.library.entity.Author;
import com.epam.library.entity.Book;
import com.epam.library.exception.PageCommandException;
import com.epam.library.exception.ServiceException;
import com.epam.library.service.BookService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ShowAuthorBooksCommand implements Command {

    private static final String BOOKS_PAGE = "/pages/books.jsp";

    private final BookService bookService;

    public ShowAuthorBooksCommand(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req) throws ServiceException, PageCommandException {
        Author author = extractAuthorFromRequest(req);

        List<Book> authorBooks = bookService.searchBooksByAuthorId(author.getId());
        req.setAttribute("books", authorBooks);
        req.setAttribute("author", author);

        return CommandResult.forward(BOOKS_PAGE);
    }

    private Author extractAuthorFromRequest(HttpServletRequest req) {
        Long authorId = Long.valueOf(req.getParameter("authorId"));
        String authorName = req.getParameter("authorName");
        String authorSurname = req.getParameter("authorSurname");
        return new Author(authorId, authorName, authorSurname);
    }
}
