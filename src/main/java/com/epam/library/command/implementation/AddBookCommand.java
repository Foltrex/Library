package com.epam.library.command.implementation;

import com.epam.library.command.Command;
import com.epam.library.command.CommandResult;
import com.epam.library.command.Page;
import com.epam.library.entity.Author;
import com.epam.library.entity.Genre;
import com.epam.library.exception.PageCommandException;
import com.epam.library.exception.ServiceException;
import com.epam.library.service.AuthorService;
import com.epam.library.service.GenreService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class AddBookCommand implements Command {

    private final AuthorService authorService;
    private final GenreService genreService;

    public AddBookCommand(AuthorService authorService, GenreService genreService) {
        this.authorService = authorService;
        this.genreService = genreService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req) throws ServiceException, PageCommandException {
        // for full list of authors and gernes
        List<Author> authors = authorService.getAuthors();
        List<Genre> genres = genreService.getGenres();

        req.setAttribute("authors", authors);
        req.setAttribute("genres", genres);

        return CommandResult.forward(Page.BOOK_DETAILS.getPath());
    }
}
