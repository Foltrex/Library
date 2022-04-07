package com.epam.library.command.implementation;

import com.epam.library.command.Command;
import com.epam.library.command.CommandResult;
import com.epam.library.command.Page;
import com.epam.library.command.Paginator;
import com.epam.library.entity.Book;
import com.epam.library.exception.PageCommandException;
import com.epam.library.exception.ServiceException;
import com.epam.library.service.BookService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

// TODO: make autocomplete
public class SearchBookCommand implements Command {

    private final BookService bookService;
    private final Paginator paginator;

    public SearchBookCommand(BookService bookService) {
        this.bookService = bookService;
        this.paginator = new Paginator(bookService, 4);
    }

    @Override
    public CommandResult execute(HttpServletRequest req) throws ServiceException, PageCommandException {
        String title = req.getParameter("bookTitle");
        int currentPage = paginator.findPageNo(req);
        List<Book> books;
        if (title != null && !title.isEmpty()) {
            books = bookService.searchBooksFromPositionByBookTitle(title, currentPage, paginator.getRecordsPerPage());
        } else {
            books = bookService.findPartOfBooks(currentPage, paginator.getRecordsPerPage());
        }
        paginator.setPaginationParameters(req);
        req.setAttribute("books", books);
        req.setAttribute("bookTitle", title);
        return CommandResult.forward(Page.BOOKS.getPath());
    }
}
