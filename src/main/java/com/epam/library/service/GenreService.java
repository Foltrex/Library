package com.epam.library.service;

import com.epam.library.entity.Book;
import com.epam.library.entity.Genre;
import com.epam.library.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface GenreService {

    List<Genre> getGenres() throws ServiceException;

    void saveGenre(Genre genre) throws ServiceException;

}
