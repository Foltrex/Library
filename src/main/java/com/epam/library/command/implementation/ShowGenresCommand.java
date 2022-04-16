package com.epam.library.command.implementation;

import com.epam.library.command.Command;
import com.epam.library.command.CommandResult;
import com.epam.library.command.Page;
import com.epam.library.command.Paginator;
import com.epam.library.entity.Genre;
import com.epam.library.exception.PageCommandException;
import com.epam.library.exception.ServiceException;
import com.epam.library.service.GenreService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ShowGenresCommand implements Command {

    // TODO: make pagination
    private final GenreService genreService;

    private final Paginator paginator;
    private static final int RECORDS_PER_PAGE = 6;

    public ShowGenresCommand(GenreService genreService) {
        this.genreService = genreService;
        paginator = new Paginator(genreService, RECORDS_PER_PAGE);
    }

    @Override
    public CommandResult execute(HttpServletRequest req) throws ServiceException, PageCommandException {
        int currentPage = paginator.findPageNo(req);
        List<Genre> genres = genreService.findPartOfGenres(currentPage, paginator.getRecordsPerPage());
        paginator.setPaginationParameters(req);
        req.setAttribute("genres", genres);
        return CommandResult.forward(Page.GENRES.getPath());
    }
}
