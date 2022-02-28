package com.epam.library.service;

import com.epam.library.entity.Author;
import com.epam.library.exception.ServiceException;

import java.util.List;

public interface AuthorService {
    List<Author> getAuthors() throws ServiceException;
}
