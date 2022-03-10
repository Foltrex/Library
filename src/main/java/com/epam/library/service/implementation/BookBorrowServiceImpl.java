package com.epam.library.service.implementation;

import com.epam.library.dao.BookBorrowDao;
import com.epam.library.dao.DaoHelper;
import com.epam.library.dao.DaoHelperFactory;
import com.epam.library.entity.BookRental;
import com.epam.library.exception.DaoException;
import com.epam.library.exception.ServiceException;
import com.epam.library.service.BookBorrowService;

import java.util.List;
import java.util.Optional;

public class BookBorrowServiceImpl implements BookBorrowService {

    private final DaoHelperFactory daoHelperFactory;

    public BookBorrowServiceImpl(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }


    @Override
    public List<BookRental> getBorrows() throws ServiceException {
        try (DaoHelper helper = daoHelperFactory.create()) {
            helper.startTransaction();
            BookBorrowDao dao = helper.createBookBorrowDao();
            List<BookRental> booksRentals = dao.getBooksBorrows();
            helper.endTransaction();
            return booksRentals;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<BookRental> getBorrow(long id) throws ServiceException {
        try (DaoHelper helper = daoHelperFactory.create()) {
            helper.startTransaction();
            BookBorrowDao dao = helper.createBookBorrowDao();
            Optional<BookRental> book = dao.findBookBorrowById(id);
            helper.endTransaction();
            return book;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void saveBookBorrow(BookRental bookBorrow) throws ServiceException {
        try (DaoHelper helper = daoHelperFactory.create()) {
            helper.startTransaction();
            BookBorrowDao dao = helper.createBookBorrowDao();
            dao.saveBookBorrow(bookBorrow);
            helper.endTransaction();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteBookBorrow(long id) throws ServiceException {
        try (DaoHelper helper = daoHelperFactory.create()) {
            helper.startTransaction();
            BookBorrowDao dao = helper.createBookBorrowDao();
            dao.deleteBookBorrow(id);
            helper.endTransaction();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
