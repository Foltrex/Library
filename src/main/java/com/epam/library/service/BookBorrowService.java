package com.epam.library.service;

import com.epam.library.entity.BookBorrow;
import com.epam.library.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface BookBorrowService {
    List<BookBorrow> getBorrows() throws ServiceException;

    Optional<BookBorrow> getBorrow(long id) throws ServiceException;

    void saveBookBorrow(BookBorrow bookBorrow) throws ServiceException;

    void deleteBookBorrow(long id) throws ServiceException;
}
