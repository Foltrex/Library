package com.epam.library.command.impl;

import com.epam.library.command.Command;
import com.epam.library.command.CommandResult;
import com.epam.library.command.Page;
import com.epam.library.entity.Genre;
import com.epam.library.exception.PageCommandException;
import com.epam.library.exception.ServiceException;
import com.epam.library.extractor.RequestExtractor;
import com.epam.library.extractor.impl.GenreRentalRequestExtractor;
import com.epam.library.service.GenreService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/** Saves new genre in DB */
public class AddGenreCommand implements Command {

    private final RequestExtractor<Genre> requestExtractor = new GenreRentalRequestExtractor();

    private final GenreService genreService;

    public AddGenreCommand(GenreService genreService) {
        this.genreService = genreService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req) throws ServiceException, PageCommandException {
        Genre genre = requestExtractor.extract(req);
        genreService.saveGenre(genre);

        List<Genre> genres = genreService.getGenres();
        req.setAttribute("genres", genres);
        return CommandResult.forward(Page.GENRES.getPath());
    }
}
