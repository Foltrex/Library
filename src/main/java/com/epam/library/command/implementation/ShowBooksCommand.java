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

public class ShowBooksCommand implements Command {

    private final BookService bookService;

    private final Paginator paginator;
    private static final int RECORDS_PER_PAGE = 4;

    public ShowBooksCommand(BookService bookService) {
        this.bookService = bookService;
        this.paginator = new Paginator(bookService, RECORDS_PER_PAGE);
    }

    @Override
    public CommandResult execute(HttpServletRequest req) throws ServiceException, PageCommandException {
        int currentPage = paginator.findPageNo(req);
        List<Book> books = bookService.findPartOfBooks(currentPage, paginator.getRecordsPerPage());
        paginator.setPaginationParameters(req);

        // for search bar autocomplete
        List<Book> allBooks = bookService.getBooks();
        String bookTitlesString = parseTitleOfAllBooksInListToString(allBooks);

        req.setAttribute("books", books);
        req.setAttribute("bookTitles", bookTitlesString);
        return CommandResult.forward(Page.BOOKS.getPath());
    }

    private String parseTitleOfAllBooksInListToString(List<Book> books) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < books.size(); ++i) {
            Book book = books.get(i);

            if (i != books.size() - 1) {
                stringBuilder.append(book.getTitle()).append("|");
            } else {
                stringBuilder.append(book.getTitle());
            }
        }

        return stringBuilder.toString();
    }
}
