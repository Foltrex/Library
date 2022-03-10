package com.epam.library.dao;

import com.epam.library.entity.BookRental;
import com.epam.library.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface BookBorrowDao {

    List<BookRental> getBooksBorrows() throws DaoException;

    void deleteBookBorrow(long id) throws DaoException;

    void saveBookBorrow(BookRental bookBorrow) throws DaoException;

    Optional<BookRental> findBookBorrowById(long id) throws DaoException;
}
