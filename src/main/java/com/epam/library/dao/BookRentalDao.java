package com.epam.library.dao;

import com.epam.library.entity.BookRental;
import com.epam.library.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface BookRentalDao {

    List<BookRental> getBooksRentals() throws DaoException;

    void deleteBookRental(long id) throws DaoException;

    void saveBookRental(BookRental bookRental) throws DaoException;

    Optional<BookRental> findBookRentalById(long id) throws DaoException;
}
