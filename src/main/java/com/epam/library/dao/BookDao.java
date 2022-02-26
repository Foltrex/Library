package com.epam.library.dao;

import com.epam.library.entity.Book;
import com.epam.library.exception.DaoException;

import java.util.List;

public interface BookDao {
    // TODO: is it ok to use an interface when there is a getAll() method
    List<Book> getBooks() throws DaoException;
}
