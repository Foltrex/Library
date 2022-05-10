package com.epam.library.service;

import com.epam.library.dao.*;
import com.epam.library.entity.Author;
import com.epam.library.entity.Book;
import com.epam.library.entity.Genre;
import com.epam.library.exception.DaoException;
import com.epam.library.exception.ServiceException;
import com.epam.library.service.impl.BookServiceImpl;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class BookServiceImplTest {

    private final List<Book> books = Arrays.asList(
            new Book(1L, "Effective Programming", Author.createAuthorWithOnlyIDField(2L), 2, Genre.createGenreWithOnlyIDField(2L)),
            new Book(2L, "Java Puzzlers" , Author.createAuthorWithOnlyIDField(2L), 1, Genre.createGenreWithOnlyIDField(2L))
    );

    @Test
    public void testGetBooksShouldReturnBooksWhenDaoIsCorrect() throws DaoException, ServiceException {
        // given
        DaoHelperFactory daoHelperFactory = mock(DaoHelperFactory.class);
        DaoHelper helper = mock(DaoHelper.class);
        BookDao dao = mock(BookDao.class);

        when(daoHelperFactory.create()).thenReturn(helper);
        doNothing().when(helper).startTransaction();
        doNothing().when(helper).endTransaction();
        when(helper.createBookDao()).thenReturn(dao);
        when(dao.getBooks()).thenReturn(books);

        BookServiceImpl service = new BookServiceImpl(daoHelperFactory);
        List<Book> expectedBooks = books;

        // when
        List<Book> actualBooks = service.getBooks();

        // then
        Assert.assertEquals(expectedBooks, actualBooks);
        verify(daoHelperFactory, times(1)).create();
        verify(helper, times(1)).startTransaction();
        verify(helper, times(1)).endTransaction();
        verify(helper, times(1)).createBookDao();
        verify(dao, times(1)).getBooks();
    }


    @Test
    public void testSearchBookByIdShouldReturnBookWithCorrespondingId() throws DaoException, ServiceException {
        // given
        DaoHelperFactory daoHelperFactory = mock(DaoHelperFactory.class);
        DaoHelper helper = mock(DaoHelper.class);
        SearchBookDao dao = mock(SearchBookDao.class);
        Optional<Book> book = Optional.of(
                new Book(1L, "Parallel Programming in C++", Author.createAuthorWithOnlyIDField(1L),
                        1, Genre.createGenreWithOnlyIDField(1L))
        );


        when(daoHelperFactory.create()).thenReturn(helper);
        doNothing().when(helper).startTransaction();
        doNothing().when(helper).endTransaction();
        when(helper.createSearchBookDao()).thenReturn(dao);
        when(dao.searchBookById(anyLong())).thenReturn(book);

        BookServiceImpl service = new BookServiceImpl(daoHelperFactory);
        Optional<Book> expectedBook = book;

        // when
        Optional<Book> actualBook = service.searchBookById(1L);

        // then
        Assert.assertEquals(expectedBook, actualBook);
        verify(daoHelperFactory, times(1)).create();
        verify(helper, times(1)).startTransaction();
        verify(helper, times(1)).endTransaction();
        verify(helper, times(1)).createSearchBookDao();
        verify(dao, times(1)).searchBookById(anyLong());
    }


    @Test
    public void testSaveBookShouldSaveBookWhenBookExist() throws DaoException, ServiceException {
        // given
        DaoHelperFactory daoHelperFactory = mock(DaoHelperFactory.class);
        DaoHelper helper = mock(DaoHelper.class);
        BookDao dao = mock(BookDao.class);
        Book book =  new Book(1L, "Parallel Programming in C++", Author.createAuthorWithOnlyIDField(1L), 1, Genre.createGenreWithOnlyIDField(1L));


        when(daoHelperFactory.create()).thenReturn(helper);
        doNothing().when(helper).startTransaction();
        doNothing().when(helper).endTransaction();
        when(helper.createBookDao()).thenReturn(dao);
        doNothing().when(dao).saveBook(any(Book.class));

        BookServiceImpl service = new BookServiceImpl(daoHelperFactory);

        // when
        service.saveBook(book);

        // then
        verify(daoHelperFactory, times(1)).create();
        verify(helper, times(1)).startTransaction();
        verify(helper, times(1)).endTransaction();
        verify(helper, times(1)).createBookDao();
        verify(dao, times(1)).saveBook(any(Book.class));
    }


    @Test
    public void testFindPartOfBooksShouldReturnSomeBooksCurrentPage() throws DaoException, ServiceException {
        // given
        DaoHelperFactory daoHelperFactory = mock(DaoHelperFactory.class);
        DaoHelper helper = mock(DaoHelper.class);
        BookDao dao = mock(BookDao.class);

        when(daoHelperFactory.create()).thenReturn(helper);
        doNothing().when(helper).startTransaction();
        doNothing().when(helper).endTransaction();
        when(helper.createBookDao()).thenReturn(dao);
        when(dao.getBooksFromPosition(anyInt(), anyInt())).thenReturn(books);

        BookServiceImpl service = new BookServiceImpl(daoHelperFactory);
        List<Book> expectedBooks = books;

        // when
        List<Book> actualBooks = service.findPartOfBooks(2, 4);

        // then
        Assert.assertEquals(expectedBooks, actualBooks);
        verify(daoHelperFactory, times(1)).create();
        verify(helper, times(1)).startTransaction();
        verify(helper, times(1)).endTransaction();
        verify(helper, times(1)).createBookDao();
        verify(dao, times(1)).getBooksFromPosition(anyInt(), anyInt());
    }


    @Test
    public void testSearchBooksFromPositionByAuthorIdShouldReturnCertainBooksFromCurtainPosition() throws DaoException, ServiceException {
        // given
        DaoHelperFactory daoHelperFactory = mock(DaoHelperFactory.class);
        DaoHelper helper = mock(DaoHelper.class);
        SearchBookDao dao = mock(SearchBookDao.class);

        when(daoHelperFactory.create()).thenReturn(helper);
        doNothing().when(helper).startTransaction();
        doNothing().when(helper).endTransaction();
        when(helper.createSearchBookDao()).thenReturn(dao);
        when(dao.searchBooksFromPositionByAuthorId(anyLong(), anyInt(), anyInt())).thenReturn(books);

        BookServiceImpl service = new BookServiceImpl(daoHelperFactory);
        List<Book> expectedBooks = books;

        // when
        List<Book> actualBooks = service.searchBooksFromPositionByAuthorId(2L, 1, 4);

        // then
        Assert.assertEquals(expectedBooks, actualBooks);
        verify(daoHelperFactory, times(1)).create();
        verify(helper, times(1)).startTransaction();
        verify(helper, times(1)).endTransaction();
        verify(helper, times(1)).createSearchBookDao();
        verify(dao, times(1)).searchBooksFromPositionByAuthorId(anyLong(), anyInt(), anyInt());
    }


    @Test
    public void testSearchBooksFromPositionByGenreIdShouldReturnCertainBooksFromCurtainPosition() throws DaoException, ServiceException {
        // given
        DaoHelperFactory daoHelperFactory = mock(DaoHelperFactory.class);
        DaoHelper helper = mock(DaoHelper.class);
        SearchBookDao dao = mock(SearchBookDao.class);

        when(daoHelperFactory.create()).thenReturn(helper);
        doNothing().when(helper).startTransaction();
        doNothing().when(helper).endTransaction();
        when(helper.createSearchBookDao()).thenReturn(dao);
        when(dao.searchBooksFromPositionByGenreId(anyLong(), anyInt(), anyInt())).thenReturn(books);

        BookServiceImpl service = new BookServiceImpl(daoHelperFactory);
        List<Book> expectedBooks = books;

        // when
        List<Book> actualBooks = service.searchBooksFromPositionByGenreId(2L, 1, 4);

        // then
        Assert.assertEquals(expectedBooks, actualBooks);
        verify(daoHelperFactory, times(1)).create();
        verify(helper, times(1)).startTransaction();
        verify(helper, times(1)).endTransaction();
        verify(helper, times(1)).createSearchBookDao();
        verify(dao, times(1)).searchBooksFromPositionByGenreId(anyLong(), anyInt(), anyInt());
    }


    @Test
    public void testSearchBooksFromPositionByTitleShouldReturnCertainBooksFromCurtainPosition() throws DaoException, ServiceException {
        // given
        DaoHelperFactory daoHelperFactory = mock(DaoHelperFactory.class);
        DaoHelper helper = mock(DaoHelper.class);
        SearchBookDao dao = mock(SearchBookDao.class);

        when(daoHelperFactory.create()).thenReturn(helper);
        doNothing().when(helper).startTransaction();
        doNothing().when(helper).endTransaction();
        when(helper.createSearchBookDao()).thenReturn(dao);
        when(dao.searchBooksFromPositionByBookTitle(anyString(), anyInt(), anyInt())).thenReturn(books.subList(0, 0));

        BookServiceImpl service = new BookServiceImpl(daoHelperFactory);
        List<Book> expectedBooks = books.subList(1, 1);

        // when
        List<Book> actualBooks = service.searchBooksFromPositionByBookTitle("Effective Programming", 1, 1);

        // then
        Assert.assertEquals(expectedBooks, actualBooks);
        verify(daoHelperFactory, times(1)).create();
        verify(helper, times(1)).startTransaction();
        verify(helper, times(1)).endTransaction();
        verify(helper, times(1)).createSearchBookDao();
        verify(dao, times(1)).searchBooksFromPositionByBookTitle(anyString(), anyInt(), anyInt());
    }


    @Test
    public void testCalculateNumberOfRowsShouldCalculateCountOfRecordsWhenRecordsExist() throws DaoException, ServiceException {
        // given
        DaoHelperFactory daoHelperFactory = mock(DaoHelperFactory.class);
        DaoHelper helper = mock(DaoHelper.class);
        BookDao dao = mock(BookDao.class);

        when(daoHelperFactory.create()).thenReturn(helper);
        doNothing().when(helper).startTransaction();
        doNothing().when(helper).endTransaction();
        when(helper.createBookDao()).thenReturn(dao);
        when(dao.calculateNumberOfRows()).thenReturn(3);

        BookServiceImpl service = new BookServiceImpl(daoHelperFactory);
        int expectedNumberOfRows = 3;

        // when
        int actualNumberOfRows = service.calculateNumberOfRows();

        // then
        Assert.assertEquals(expectedNumberOfRows, actualNumberOfRows);
        verify(helper, times(1)).startTransaction();
        verify(helper, times(1)).endTransaction();
        verify(helper, times(1)).createBookDao();
        verify(dao, times(1)).calculateNumberOfRows();
    }
}
