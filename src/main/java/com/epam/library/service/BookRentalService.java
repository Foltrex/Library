package com.epam.library.service;

import com.epam.library.entity.Book;
import com.epam.library.entity.BookRental;
import com.epam.library.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface BookRentalService {
    List<BookRental> getRentals() throws ServiceException;

    Optional<BookRental> getRental(long id) throws ServiceException;

    void saveBookRental(BookRental bookRental) throws ServiceException;

    void deleteBookRental(long id) throws ServiceException;
}
