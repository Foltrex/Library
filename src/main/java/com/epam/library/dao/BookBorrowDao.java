package com.epam.library.dao;

import com.epam.library.entity.BookBorrow;
import com.epam.library.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface BookBorrowDao {

    List<BookBorrow> getBooksBorrows() throws DaoException;

    void deleteBookBorrow(long id) throws DaoException;

    void saveBookBorrow(BookBorrow bookBorrow) throws DaoException;

    Optional<BookBorrow> findBookBorrowById(long id) throws DaoException;
}
