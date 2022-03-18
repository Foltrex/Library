package com.epam.library.service;

import com.epam.library.dao.*;
import com.epam.library.entity.Author;
import com.epam.library.entity.Book;
import com.epam.library.entity.Genre;
import com.epam.library.exception.DaoException;
import com.epam.library.exception.ServiceException;
import com.epam.library.service.implementation.AuthorServiceImpl;
import com.epam.library.service.implementation.BookServiceImpl;
import com.epam.library.service.implementation.GenreServiceImpl;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class GenreServiceImplTest {

    private final List<Genre> genres = Arrays.asList(
            new Genre(1L, "Web Design"),
            new Genre(2L, "Russian Classic")
    );

    @Test
    public void testGetGenresShouldReturnGenresWhenDaoIsCorrect() throws DaoException, ServiceException {
        // given
        DaoHelperFactory daoHelperFactory = mock(DaoHelperFactory.class);
        DaoHelper helper = mock(DaoHelper.class);
        GenreDao dao = mock(GenreDao.class);

        when(daoHelperFactory.create()).thenReturn(helper);
        doNothing().when(helper).startTransaction();
        doNothing().when(helper).endTransaction();
        when(helper.createGenreDao()).thenReturn(dao);
        when(dao.getGenres()).thenReturn(genres);

        GenreServiceImpl service = new GenreServiceImpl(daoHelperFactory);
        List<Genre> expectedGenres = genres;

        // when
        List<Genre> actualGenres = service.getGenres();

        // then
        Assert.assertEquals(expectedGenres, actualGenres);
        verify(daoHelperFactory, times(1)).create();
        verify(helper, times(1)).startTransaction();
        verify(helper, times(1)).endTransaction();
        verify(helper, times(1)).createGenreDao();
        verify(dao, times(1)).getGenres();
    }


    @Test
    public void testSaveGenreShouldSaveGenreWhenGenreExist() throws DaoException, ServiceException {
        // given
        DaoHelperFactory daoHelperFactory = mock(DaoHelperFactory.class);
        DaoHelper helper = mock(DaoHelper.class);
        GenreDao dao = mock(GenreDao.class);

        when(daoHelperFactory.create()).thenReturn(helper);
        doNothing().when(helper).startTransaction();
        doNothing().when(helper).endTransaction();
        when(helper.createGenreDao()).thenReturn(dao);
        doNothing().when(dao).saveGenre(any(Genre.class));

        GenreServiceImpl service = new GenreServiceImpl(daoHelperFactory);

        // when
        service.saveGenre(new Genre(1L, "Russian Classic"));

        // then
        verify(daoHelperFactory, times(1)).create();
        verify(helper, times(1)).startTransaction();
        verify(helper, times(1)).endTransaction();
        verify(helper, times(1)).createGenreDao();
        verify(dao, times(1)).saveGenre(any(Genre.class));
    }


    @Test
    public void testCalculateNumberOfRowsShouldCalculateCountOfRecordsWhenRecordsExist() throws DaoException, ServiceException {
        // given
        DaoHelperFactory daoHelperFactory = mock(DaoHelperFactory.class);
        DaoHelper helper = mock(DaoHelper.class);
        GenreDao dao = mock(GenreDao.class);

        when(daoHelperFactory.create()).thenReturn(helper);
        doNothing().when(helper).startTransaction();
        doNothing().when(helper).endTransaction();
        when(helper.createGenreDao()).thenReturn(dao);
        when(dao.calculateNumberOfRows()).thenReturn(3);

        GenreServiceImpl service = new GenreServiceImpl(daoHelperFactory);
        int expectedNumberOfRows = 3;

        // when
        int actualNumberOfRows = service.calculateNumberOfRows();

        // then
        Assert.assertEquals(expectedNumberOfRows, actualNumberOfRows);
        verify(helper, times(1)).startTransaction();
        verify(helper, times(1)).endTransaction();
        verify(helper, times(1)).createGenreDao();
        verify(dao, times(1)).calculateNumberOfRows();
    }
}
