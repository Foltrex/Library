package com.epam.library.service.implementation;

import com.epam.library.dao.BookRentalDao;
import com.epam.library.dao.BookDao;
import com.epam.library.dao.DaoHelper;
import com.epam.library.dao.DaoHelperFactory;
import com.epam.library.entity.BookRental;
import com.epam.library.entity.RentalStatus;
import com.epam.library.exception.DaoException;
import com.epam.library.exception.ServiceException;
import com.epam.library.service.BookRentalService;

import java.util.List;
import java.util.Optional;

public class BookRentalServiceImpl implements BookRentalService {

    private final DaoHelperFactory daoHelperFactory;

    public BookRentalServiceImpl(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }


    @Override
    public List<BookRental> getBookRentals() throws ServiceException {
        try (DaoHelper helper = daoHelperFactory.create()) {
            helper.startTransaction();
            BookRentalDao dao = helper.createBookRentalDao();
            List<BookRental> booksRentals = dao.getBooksRentals();
            helper.endTransaction();
            return booksRentals;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<BookRental> getBookRental(long id) throws ServiceException {
        try (DaoHelper helper = daoHelperFactory.create()) {
            helper.startTransaction();
            BookRentalDao dao = helper.createBookRentalDao();
            Optional<BookRental> book = dao.findBookRentalById(id);
            helper.endTransaction();
            return book;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void saveBookRental(BookRental bookRental) throws ServiceException {
        try (DaoHelper helper = daoHelperFactory.create()) {
            helper.startTransaction();
            BookRentalDao dao = helper.createBookRentalDao();
            BookDao bookDao = helper.createBookDao();

            if (bookRental.getRentalStatus() == RentalStatus.ISSUED_FOR_SUBSCRIPTION
                    || bookRental.getRentalStatus() == RentalStatus.ISSUED_TO_THE_READING_ROOM) {

                bookDao.borrowBook(bookRental.getRentedBook().getId());

            } else if (bookRental.getRentalStatus() == RentalStatus.RETURNED) {
                bookDao.returnBook(bookRental.getRentedBook().getId());
            }

            dao.saveBookRental(bookRental);
            helper.endTransaction();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteBookRental(BookRental bookRental) throws ServiceException {
        try (DaoHelper helper = daoHelperFactory.create()) {
            helper.startTransaction();
            BookRentalDao dao = helper.createBookRentalDao();
            BookDao bookDao = helper.createBookDao();
            dao.deleteBookRental(bookRental.getId());

            // TODO: make to return status
            if (bookRental.getRentalStatus() == RentalStatus.ISSUED_FOR_SUBSCRIPTION ||
                    bookRental.getRentalStatus() == RentalStatus.ISSUED_TO_THE_READING_ROOM) {
                bookDao.returnBook(bookRental.getRentedBook().getId());
            }

            helper.endTransaction();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
