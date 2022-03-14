package com.epam.library.service;

import com.epam.library.entity.BookRental;
import com.epam.library.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface BookRentalService extends EntityService {
    List<BookRental> getBookRentals() throws ServiceException;

    Optional<BookRental> getBookRental(long id) throws ServiceException;

    void saveBookRental(BookRental bookRental) throws ServiceException;

    void deleteBookRental(BookRental bookRental) throws ServiceException;
}
