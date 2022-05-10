package com.epam.library.command.impl;

import com.epam.library.command.Command;
import com.epam.library.command.CommandResult;
import com.epam.library.command.Page;
import com.epam.library.command.Paginator;
import com.epam.library.entity.Author;
import com.epam.library.entity.Book;
import com.epam.library.exception.PageCommandException;
import com.epam.library.exception.ServiceException;
import com.epam.library.extractor.RequestExtractor;
import com.epam.library.extractor.impl.AuthorRequestExtractor;
import com.epam.library.service.BookService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ShowAuthorBooksCommand implements Command {

    private final RequestExtractor<Author> requestExtractor = new AuthorRequestExtractor();

    private final BookService bookService;
    private final Paginator paginator;

    public ShowAuthorBooksCommand(BookService bookService) {
        this.bookService = bookService;
        this.paginator = new Paginator(bookService, 4);
    }

    @Override
    public CommandResult execute(HttpServletRequest req) throws ServiceException, PageCommandException {
        Author author = requestExtractor.extract(req);
        int currentPage = paginator.findPageNo(req);
        List<Book> authorBooks = bookService.searchBooksFromPositionByAuthorId(author.getId(), currentPage, paginator.getRecordsPerPage());
        paginator.setPaginationParameters(req);
        req.setAttribute("books", authorBooks);
        req.setAttribute("author", author);

        return CommandResult.forward(Page.BOOKS.getPath());
    }
}
