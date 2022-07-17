package com.epam.library.service;

import com.epam.library.entity.Author;
import com.epam.library.exception.ServiceException;

import java.util.List;

public interface AuthorService extends EntityService {
    List<Author> getAuthors() throws ServiceException;

    void saveAuthor(Author author) throws ServiceException;
}
