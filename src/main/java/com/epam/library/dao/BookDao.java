package com.epam.library.dao;

import com.epam.library.entity.Book;
import com.epam.library.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface BookDao {
    List<Book> getBooks() throws DaoException;

    Optional<Book> searchBookById(long id) throws DaoException;

    List<Book> searchBooksByAuthorId(long id) throws DaoException;

    List<Book> searchBooksByGenreId(Long id) throws DaoException;

    void saveBook(Book book) throws DaoException;

    List<Book> searchBooksByTitle(String title) throws DaoException;

    int calculateBooksNumber() throws DaoException;

    List<Book> getBooksFromPosition(int startingPosition, int recordsPerPage) throws DaoException;
}
