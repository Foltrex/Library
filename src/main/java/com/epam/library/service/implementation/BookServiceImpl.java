package com.epam.library.service.implementation;

import com.epam.library.dao.*;
import com.epam.library.entity.Book;
import com.epam.library.entity.User;
import com.epam.library.exception.DaoException;
import com.epam.library.exception.ServiceException;
import com.epam.library.service.BookService;

import java.util.List;
import java.util.Optional;

public class BookServiceImpl implements BookService {

    private final DaoHelperFactory daoHelperFactory;

    public BookServiceImpl(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }

    @Override
    public Optional<Book> findBook(Long id) throws ServiceException {
        List<Book> books = getBooks();
        if (books.size() == 1) {
            return Optional.of(books.get(0));
        } else if (books.size() > 1) {
            throw new IllegalArgumentException("More than one book");
        } else {
            return Optional.empty();
        }
    }

    @Override
    public List<Book> getBooks() throws ServiceException {
        try (DaoHelper helper = daoHelperFactory.create()) {
            helper.startTransaction();
            BookDao dao = helper.createBookDao();
            List<Book> books = dao.getBooks();
            helper.endTransaction();
            return books;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
