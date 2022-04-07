package com.epam.library.command.implementation;

import com.epam.library.command.Command;
import com.epam.library.command.CommandResult;
import com.epam.library.command.Page;
import com.epam.library.entity.Genre;
import com.epam.library.exception.PageCommandException;
import com.epam.library.exception.ServiceException;
import com.epam.library.service.GenreService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class AddGenreCommand implements Command {

    private static final String GENRES_PAGE = "/pages/genres.jsp";

    private final GenreService genreService;

    public AddGenreCommand(GenreService genreService) {
        this.genreService = genreService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req) throws ServiceException, PageCommandException {
        Genre genre = extractGenreFromRequest(req);
        genreService.saveGenre(genre);

        List<Genre> genres = genreService.getGenres();
        req.setAttribute("genres", genres);
        return CommandResult.forward(Page.GENRES.getPath());
    }

    private Genre extractGenreFromRequest(HttpServletRequest req) {
        String genreName = req.getParameter("genreName");
        return new Genre(null, genreName);
    }
}
