package com.epam.library.service;

import com.epam.library.entity.Book;
import com.epam.library.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> getBooks() throws ServiceException;

    Optional<Book> getBook(long id) throws ServiceException;

    void saveBook(Book book) throws ServiceException;
}
