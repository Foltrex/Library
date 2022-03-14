package com.epam.library.service;

import com.epam.library.entity.Book;
import com.epam.library.exception.DaoException;
import com.epam.library.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface BookService extends EntityService {
    List<Book> searchBooksByTitle(String title) throws ServiceException;

    List<Book> searchBooksByAuthorId(Long id) throws ServiceException;

    List<Book> searchBooksByGenreId(Long id) throws ServiceException;

    List<Book> getBooks() throws ServiceException;

    Optional<Book> searchBookById(long id) throws ServiceException;

    void saveBook(Book book) throws ServiceException;

    List<Book> findPartOfBooks(int currentPage, int recordsPerPage) throws ServiceException;

    int calculateBooksNumber() throws ServiceException;

    List<Book> searchBooksFromPositionByAuthorId(Long id, int startingPosition, int recordsPerPage) throws ServiceException;

    List<Book> searchBooksFromPositionByGenreId(Long id, int startingPosition, int recordsPerPage) throws ServiceException;

    List<Book> searchBooksFromPositionByBookTitle(String title, int startingPosition, int recordsPerPage) throws ServiceException;

}
