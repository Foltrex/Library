package com.epam.library.command.implementation;

import com.epam.library.command.Command;
import com.epam.library.command.CommandResult;
import com.epam.library.command.Page;
import com.epam.library.entity.Author;
import com.epam.library.entity.Genre;
import com.epam.library.exception.PageCommandException;
import com.epam.library.exception.ServiceException;
import com.epam.library.service.GenreService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ShowGenresCommand implements Command {

    // TODO: make pagination
    private final GenreService genreService;

    public ShowGenresCommand(GenreService genreService) {
        this.genreService = genreService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req) throws ServiceException, PageCommandException {
        List<Genre> genres = genreService.getGenres();
        req.setAttribute("genres", genres);
        return CommandResult.forward(Page.GENRES.getName());
    }
}
