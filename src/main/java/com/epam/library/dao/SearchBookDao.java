package com.epam.library.dao;

import com.epam.library.entity.Book;
import com.epam.library.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface SearchBookDao extends EntityDao {

    Optional<Book> searchBookById(long id) throws DaoException;

    @Deprecated
    List<Book> searchBooksByAuthorId(long id) throws DaoException;

    @Deprecated
    List<Book> searchBooksByGenreId(Long id) throws DaoException;

    @Deprecated
    List<Book> searchBooksByTitle(String title) throws DaoException;

    List<Book> searchBooksFromPositionByAuthorId(Long id, int startingPosition, int recordsPerPage) throws DaoException;

    List<Book> searchBooksFromPositionByGenreId(Long id, int startingPosition, int recordsPerPage) throws DaoException;

    List<Book> searchBooksFromPositionByBookTitle(String title, int startingPosition, int recordsPerPage) throws DaoException;
}
