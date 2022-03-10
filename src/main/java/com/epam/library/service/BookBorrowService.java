package com.epam.library.service;

import com.epam.library.entity.BookRental;
import com.epam.library.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface BookBorrowService {
    List<BookRental> getBorrows() throws ServiceException;

    Optional<BookRental> getBorrow(long id) throws ServiceException;

    void saveBookBorrow(BookRental bookBorrow) throws ServiceException;

    void deleteBookBorrow(long id) throws ServiceException;
}
