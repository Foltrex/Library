package com.epam.library.service;

import com.epam.library.dao.AuthorDao;
import com.epam.library.dao.DaoHelper;
import com.epam.library.dao.DaoHelperFactory;
import com.epam.library.entity.Author;
import com.epam.library.exception.DaoException;
import com.epam.library.exception.ServiceException;
import com.epam.library.service.impl.AuthorServiceImpl;
import com.google.common.collect.ImmutableList;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

import static org.mockito.Mockito.mock;

public class AuthorServiceImplTest {

    private final List<Author> authors = ImmutableList.of(
            new Author(1L, "Alexander", "Pushkin"),
            new Author(2L, "Leo", "Tolstoy")
    );

    @Test
    public void testGetAuthorsShouldReturnAuthorsWhenDaoIsCorrect() throws DaoException, ServiceException {
        // given
        DaoHelperFactory daoHelperFactory = mock(DaoHelperFactory.class);
        DaoHelper helper = mock(DaoHelper.class);
        AuthorDao dao = mock(AuthorDao.class);

        when(daoHelperFactory.create()).thenReturn(helper);
        doNothing().when(helper).startTransaction();
        doNothing().when(helper).endTransaction();
        when(helper.createAuthorDao()).thenReturn(dao);
        when(dao.getAuthors()).thenReturn(authors);

        AuthorServiceImpl service = new AuthorServiceImpl(daoHelperFactory);
        List<Author> expectedAuthors = authors;

        // when
        List<Author> actualAuthors = service.getAuthors();

        // then
        Assert.assertEquals(expectedAuthors, actualAuthors);
        verify(daoHelperFactory, times(1)).create();
        verify(helper, times(1)).startTransaction();
        verify(helper, times(1)).endTransaction();
        verify(helper, times(1)).createAuthorDao();
        verify(dao, times(1)).getAuthors();
    }

    @Test
    public void testSaveAuthorShouldSaveAuthorWhenAuthorParametersExist() throws DaoException, ServiceException {
        // given
        DaoHelperFactory daoHelperFactory = mock(DaoHelperFactory.class);
        DaoHelper helper = mock(DaoHelper.class);
        AuthorDao dao = mock(AuthorDao.class);

        when(daoHelperFactory.create()).thenReturn(helper);
        doNothing().when(helper).startTransaction();
        doNothing().when(helper).endTransaction();
        when(helper.createAuthorDao()).thenReturn(dao);
        doNothing().when(dao).saveAuthor(any(Author.class));

        AuthorServiceImpl service = new AuthorServiceImpl(daoHelperFactory);

        // when
        service.saveAuthor(new Author(3L, "Anjey", "Sapkowski"));

        // then
        verify(daoHelperFactory, times(1)).create();
        verify(helper, times(1)).startTransaction();
        verify(helper, times(1)).endTransaction();
        verify(helper, times(1)).createAuthorDao();
        verify(dao, times(1)).saveAuthor(any(Author.class));
    }

    @Test
    public void testCalculateNumberOfRowsShouldCalculateCountOfRecordsWhenRecordsExist() throws DaoException, ServiceException {
        // given
        DaoHelperFactory daoHelperFactory = mock(DaoHelperFactory.class);
        DaoHelper helper = mock(DaoHelper.class);
        AuthorDao dao = mock(AuthorDao.class);

        when(daoHelperFactory.create()).thenReturn(helper);
        doNothing().when(helper).startTransaction();
        doNothing().when(helper).endTransaction();
        when(helper.createAuthorDao()).thenReturn(dao);
        when(dao.calculateNumberOfRows()).thenReturn(3);

        AuthorServiceImpl service = new AuthorServiceImpl(daoHelperFactory);
        int expectedNumberOfRows = 3;

        // when
        int actualNumberOfRows = service.calculateNumberOfRows();

        // then
        Assert.assertEquals(expectedNumberOfRows, actualNumberOfRows);
        verify(helper, times(1)).startTransaction();
        verify(helper, times(1)).endTransaction();
        verify(helper, times(1)).createAuthorDao();
        verify(dao, times(1)).calculateNumberOfRows();
    }
}
