package com.epam.library.service.implementation;

import com.epam.library.dao.*;
import com.epam.library.entity.Book;
import com.epam.library.entity.Genre;
import com.epam.library.exception.DaoException;
import com.epam.library.exception.ServiceException;
import com.epam.library.service.GenreService;

import java.util.List;

public class GenreServiceImpl implements GenreService {

    private final DaoHelperFactory daoHelperFactory;

    public GenreServiceImpl(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }

    @Override
    public List<Genre> getGenres() throws ServiceException {
        try (DaoHelper helper = daoHelperFactory.create()) {
            helper.startTransaction();
            GenreDao dao = helper.createGenreDao();
            List<Genre> genres = dao.getGenres();
            helper.endTransaction();
            return genres;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
