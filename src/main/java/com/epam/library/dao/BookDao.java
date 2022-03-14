package com.epam.library.dao;

import com.epam.library.entity.Book;
import com.epam.library.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface BookDao extends EntityDao {
    List<Book> getBooks() throws DaoException;

    void saveBook(Book book) throws DaoException;

    int calculateBooksNumber() throws DaoException;

    List<Book> getBooksFromPosition(int startingPosition, int recordsPerPage) throws DaoException;

    // make separate interface
    void borrowBook(Long id) throws DaoException;

    void returnBook(Long id) throws DaoException;
}
