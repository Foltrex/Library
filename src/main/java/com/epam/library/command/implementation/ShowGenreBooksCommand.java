package com.epam.library.command.implementation;

import com.epam.library.command.Command;
import com.epam.library.command.CommandResult;
import com.epam.library.command.Page;
import com.epam.library.command.Paginator;
import com.epam.library.entity.Book;
import com.epam.library.entity.Genre;
import com.epam.library.exception.PageCommandException;
import com.epam.library.exception.ServiceException;
import com.epam.library.extractor.RequestExtractor;
import com.epam.library.extractor.implementation.GenreRentalRequestExtractor;
import com.epam.library.service.BookService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ShowGenreBooksCommand implements Command {

    private final RequestExtractor<Genre> requestExtractor = new GenreRentalRequestExtractor();

    private final BookService bookService;
    private final Paginator paginator;

    public ShowGenreBooksCommand(BookService bookService) {
        this.bookService = bookService;
        this.paginator = new Paginator(bookService, 4);
    }

    @Override
    public CommandResult execute(HttpServletRequest req) throws ServiceException, PageCommandException {
        Genre genre = requestExtractor.extract(req);
        int currentPage = paginator.findPageNo(req);
        List<Book> genreBooks = bookService.searchBooksFromPositionByGenreId(genre.getId(), currentPage, paginator.getRecordsPerPage());
        paginator.setPaginationParameters(req);
        req.setAttribute("books", genreBooks);
        req.setAttribute("genre", genre);

        return CommandResult.forward(Page.BOOKS.getPath());
    }
}
