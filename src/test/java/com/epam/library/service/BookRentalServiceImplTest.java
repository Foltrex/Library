package com.epam.library.service;

import com.epam.library.dao.*;
import com.epam.library.entity.*;
import com.epam.library.exception.DaoException;
import com.epam.library.exception.ServiceException;
import com.epam.library.service.impl.BookRentalServiceImpl;
import com.google.common.collect.ImmutableList;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class BookRentalServiceImplTest {

    private final List<BookRental> bookRentals = ImmutableList.of(
            BookRental.builder()
                    .id(1L)
                    .user(User.builder().id(1L).build())
                    .rentedBook(Book.builder().id(1L).build())
                    .borrowDate(new Date())
                    .returnDate(new Date())
                    .rentalStatus(RentalStatus.RETURNED)
                    .build(),

            BookRental.builder()
                    .id(2L)
                    .user(User.builder().id(2L).build())
                    .rentedBook(Book.builder().id(2L).build())
                    .borrowDate(new Date())
                    .returnDate(new Date())
                    .rentalStatus(RentalStatus.RETURNED)
                    .build()
    );


    @Test
    public void testGetBookRentalsShouldReturnBookRentalsWhenDaoIsCorrect() throws DaoException, ServiceException {
        // given
        DaoHelperFactory daoHelperFactory = mock(DaoHelperFactory.class);
        DaoHelper helper = mock(DaoHelper.class);
        BookRentalDao dao = mock(BookRentalDao.class);

        when(daoHelperFactory.create()).thenReturn(helper);
        doNothing().when(helper).startTransaction();
        doNothing().when(helper).endTransaction();
        when(helper.createBookRentalDao()).thenReturn(dao);
        when(dao.getBooksRentals()).thenReturn(bookRentals);

        BookRentalServiceImpl service = new BookRentalServiceImpl(daoHelperFactory);
        List<BookRental> expectedBookRentals = bookRentals;

        // when
        List<BookRental> actualBookRentals = service.getBookRentals();

        // then
        Assert.assertEquals(expectedBookRentals, actualBookRentals);
        verify(daoHelperFactory, times(1)).create();
        verify(helper, times(1)).startTransaction();
        verify(helper, times(1)).endTransaction();
        verify(helper, times(1)).createBookRentalDao();
        verify(dao, times(1)).getBooksRentals();
    }


    @Test
    public void testGetBookRentalShouldReturnBookRentalWhenIdIsGiven() throws DaoException, ServiceException {
        // given
        DaoHelperFactory daoHelperFactory = mock(DaoHelperFactory.class);
        DaoHelper helper = mock(DaoHelper.class);
        BookRentalDao dao = mock(BookRentalDao.class);
        BookRental bookRental = BookRental.builder()
                .id(1L)
                .user(User.builder().id(1L).build())
                .rentedBook(Book.builder().id(1L).build())
                .borrowDate(new Date())
                .returnDate(new Date())
                .rentalStatus(RentalStatus.RETURNED)
                .build();

        Optional<BookRental> optionalBookRental = Optional.of(bookRental);

        when(daoHelperFactory.create()).thenReturn(helper);
        doNothing().when(helper).startTransaction();
        doNothing().when(helper).endTransaction();
        when(helper.createBookRentalDao()).thenReturn(dao);
        when(dao.findBookRentalById(anyLong())).thenReturn(optionalBookRental);

        BookRentalServiceImpl service = new BookRentalServiceImpl(daoHelperFactory);
        Optional<BookRental> expectedBookRental = optionalBookRental;

        // when
        Optional<BookRental> actualBookRental = service.getBookRental(1);

        // then
        Assert.assertEquals(expectedBookRental, actualBookRental);
        verify(daoHelperFactory, times(1)).create();
        verify(helper, times(1)).startTransaction();
        verify(helper, times(1)).endTransaction();
        verify(helper, times(1)).createBookRentalDao();
        verify(dao, times(1)).findBookRentalById(anyLong());
    }


    @Test
    public void testSaveBookRentalShouldSaveBookRentalAsReturnedWhenRentalStatusIsReturned() throws DaoException, ServiceException {
        // given
        DaoHelperFactory daoHelperFactory = mock(DaoHelperFactory.class);
        DaoHelper helper = mock(DaoHelper.class);
        BookRentalDao bookRentalDao = mock(BookRentalDao.class);
        BookDao bookDao = mock(BookDao.class);
        BookRental bookRental = BookRental.builder()
                .id(1L)
                .user(User.builder().id(1L).build())
                .rentedBook(Book.builder().id(1L).build())
                .borrowDate(new Date())
                .returnDate(new Date())
                .rentalStatus(RentalStatus.RETURNED)
                .build();


        when(daoHelperFactory.create()).thenReturn(helper);
        doNothing().when(helper).startTransaction();
        doNothing().when(helper).endTransaction();
        when(helper.createBookRentalDao()).thenReturn(bookRentalDao);
        when(helper.createBookDao()).thenReturn(bookDao);
        doNothing().when(bookDao).returnBook(anyLong());
        doNothing().when(bookRentalDao).saveBookRental(bookRental);

        BookRentalServiceImpl service = new BookRentalServiceImpl(daoHelperFactory);

        // when
        service.saveBookRental(bookRental);

        // then
        verify(daoHelperFactory, times(1)).create();
        verify(helper, times(1)).startTransaction();
        verify(helper, times(1)).endTransaction();
        verify(helper, times(1)).createBookRentalDao();
        verify(helper, times(1)).createBookDao();
        verify(bookDao, times(1)).returnBook(anyLong());
        verify(bookRentalDao, times(1)).saveBookRental(any(BookRental.class));
    }


    @Test
    public void testSaveBookRentalShouldSaveBookRentalAsIssuedWhenRentalStatusIsIssued() throws DaoException, ServiceException {
        // given
        DaoHelperFactory daoHelperFactory = mock(DaoHelperFactory.class);
        DaoHelper helper = mock(DaoHelper.class);
        BookRentalDao bookRentalDao = mock(BookRentalDao.class);
        BookDao bookDao = mock(BookDao.class);
        BookRental bookRental = BookRental.builder()
                .id(1L)
                .user(User.builder().id(1L).build())
                .rentedBook(Book.builder().id(1L).build())
                .borrowDate(new Date())
                .returnDate(new Date())
                .rentalStatus(RentalStatus.ISSUED_FOR_SUBSCRIPTION)
                .build();


        when(daoHelperFactory.create()).thenReturn(helper);
        doNothing().when(helper).startTransaction();
        doNothing().when(helper).endTransaction();
        when(helper.createBookRentalDao()).thenReturn(bookRentalDao);
        when(helper.createBookDao()).thenReturn(bookDao);
        doNothing().when(bookDao).borrowBook(anyLong());
        doNothing().when(bookRentalDao).saveBookRental(bookRental);

        BookRentalServiceImpl service = new BookRentalServiceImpl(daoHelperFactory);

        // when
        service.saveBookRental(bookRental);

        // then
        verify(daoHelperFactory, times(1)).create();
        verify(helper, times(1)).startTransaction();
        verify(helper, times(1)).endTransaction();
        verify(helper, times(1)).createBookRentalDao();
        verify(helper, times(1)).createBookDao();
        verify(bookDao, times(1)).borrowBook(anyLong());
        verify(bookRentalDao, times(1)).saveBookRental(any(BookRental.class));
    }


    @Test
    public void testDeleteBookRentalShouldDeleteBookRentalAndReturnBookWhenRentalStatusIsIssued() throws DaoException, ServiceException{
        // given
        DaoHelperFactory daoHelperFactory = mock(DaoHelperFactory.class);
        DaoHelper helper = mock(DaoHelper.class);
        BookRentalDao bookRentalDao = mock(BookRentalDao.class);
        BookDao bookDao = mock(BookDao.class);
        BookRental bookRental = BookRental.builder()
                .id(1L)
                .user(User.builder().id(1L).build())
                .rentedBook(Book.builder().id(1L).build())
                .borrowDate(new Date())
                .returnDate(new Date())
                .rentalStatus(RentalStatus.ISSUED_FOR_SUBSCRIPTION)
                .build();


        when(daoHelperFactory.create()).thenReturn(helper);
        doNothing().when(helper).startTransaction();
        doNothing().when(helper).endTransaction();
        when(helper.createBookRentalDao()).thenReturn(bookRentalDao);
        when(helper.createBookDao()).thenReturn(bookDao);
        doNothing().when(bookRentalDao).deleteBookRental(anyLong());
        doNothing().when(bookDao).returnBook(anyLong());

        BookRentalServiceImpl service = new BookRentalServiceImpl(daoHelperFactory);

        // when
        service.deleteBookRental(bookRental);

        // then
        verify(daoHelperFactory, times(1)).create();
        verify(helper, times(1)).startTransaction();
        verify(helper, times(1)).endTransaction();
        verify(helper, times(1)).createBookRentalDao();
        verify(helper, times(1)).createBookDao();
        verify(bookDao, times(1)).returnBook(anyLong());
        verify(bookRentalDao, times(1)).deleteBookRental(anyLong());
    }


    @Test
    public void testDeleteBookRentalShouldDeleteBookRentalWhenRentalStatusIsNotIssued() throws DaoException, ServiceException{
        // given
        DaoHelperFactory daoHelperFactory = mock(DaoHelperFactory.class);
        DaoHelper helper = mock(DaoHelper.class);
        BookRentalDao bookRentalDao = mock(BookRentalDao.class);
        BookDao bookDao = mock(BookDao.class);
        BookRental bookRental = BookRental.builder()
                .id(1L)
                .user(User.builder().id(1L).build())
                .rentedBook(Book.builder().id(1L).build())
                .borrowDate(new Date())
                .returnDate(new Date())
                .rentalStatus(RentalStatus.RETURNED)
                .build();

        when(daoHelperFactory.create()).thenReturn(helper);
        doNothing().when(helper).startTransaction();
        doNothing().when(helper).endTransaction();
        when(helper.createBookRentalDao()).thenReturn(bookRentalDao);
        when(helper.createBookDao()).thenReturn(bookDao);
        doNothing().when(bookRentalDao).deleteBookRental(anyLong());

        BookRentalServiceImpl service = new BookRentalServiceImpl(daoHelperFactory);

        // when
        service.deleteBookRental(bookRental);

        // then
        verify(daoHelperFactory, times(1)).create();
        verify(helper, times(1)).startTransaction();
        verify(helper, times(1)).endTransaction();
        verify(helper, times(1)).createBookRentalDao();
        verify(helper, times(1)).createBookDao();
        verify(bookRentalDao, times(1)).deleteBookRental(anyLong());
    }


    @Test
    public void testCalculateNumberOfRowsShouldCalculateCountOfRecordsWhenRecordsExist() throws DaoException, ServiceException {
        // given
        DaoHelperFactory daoHelperFactory = mock(DaoHelperFactory.class);
        DaoHelper helper = mock(DaoHelper.class);
        BookRentalDao dao = mock(BookRentalDao.class);

        when(daoHelperFactory.create()).thenReturn(helper);
        doNothing().when(helper).startTransaction();
        doNothing().when(helper).endTransaction();
        when(helper.createBookRentalDao()).thenReturn(dao);
        when(dao.calculateNumberOfRows()).thenReturn(3);

        BookRentalServiceImpl service = new BookRentalServiceImpl(daoHelperFactory);
        int expectedNumberOfRows = 3;

        // when
        int actualNumberOfRows = service.calculateNumberOfRows();

        // then
        Assert.assertEquals(expectedNumberOfRows, actualNumberOfRows);
        verify(helper, times(1)).startTransaction();
        verify(helper, times(1)).endTransaction();
        verify(helper, times(1)).createBookRentalDao();
        verify(dao, times(1)).calculateNumberOfRows();
    }
}
