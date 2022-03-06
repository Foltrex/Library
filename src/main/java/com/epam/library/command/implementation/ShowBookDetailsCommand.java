package com.epam.library.command.implementation;

import com.epam.library.command.Command;
import com.epam.library.command.CommandResult;
import com.epam.library.entity.Author;
import com.epam.library.entity.Book;
import com.epam.library.entity.Genre;
import com.epam.library.exception.PageCommandException;
import com.epam.library.exception.ServiceException;
import com.epam.library.service.AuthorService;
import com.epam.library.service.BookService;
import com.epam.library.service.GenreService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

public class ShowBookDetailsCommand implements Command {

    private static final String BOOK_PAGE = "/pages/bookDetails.jsp";
    private static final String ERROR_PAGE = "/pages/errorPage.jsp";

    private final BookService bookService;
    private final AuthorService authorService;
    private final GenreService  genreService;

    public ShowBookDetailsCommand(BookService bookService, AuthorService authorService, GenreService genreService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.genreService = genreService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req) throws ServiceException, PageCommandException {
        long id = Long.parseLong(req.getParameter("id"));

        Optional<Book> optionalBook = bookService.searchBookById(id);

        // for full list of authors and genres
        List<Author> authors = authorService.getAuthors();
        List<Genre> genres = genreService.getGenres();


        // TODO: make saving and creating book with this command
        CommandResult result;
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            req.setAttribute("book", book);
            req.setAttribute("authors", authors);
            req.setAttribute("genres", genres);
            result = CommandResult.forward(BOOK_PAGE);
        } else {
            req.setAttribute("errorMessage", "Book doesn't found");
            result = CommandResult.forward(ERROR_PAGE);
        }

        return result;
    }
}
