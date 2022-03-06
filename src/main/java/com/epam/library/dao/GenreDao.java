package com.epam.library.dao;

import com.epam.library.entity.Author;
import com.epam.library.entity.Genre;
import com.epam.library.exception.DaoException;

import java.util.List;

public interface GenreDao {

    List<Genre> getGenres() throws DaoException;

    void saveGenre(Genre genre) throws DaoException;
}
