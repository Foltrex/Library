package com.epam.library.service;

import com.epam.library.entity.Book;
import com.epam.library.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface BookService {

    Optional<Book> findBook(Long id) throws ServiceException;

    List<Book> getBooks() throws ServiceException;
}
