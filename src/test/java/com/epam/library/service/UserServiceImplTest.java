package com.epam.library.service;

import com.epam.library.dao.*;
import com.epam.library.entity.*;
import com.epam.library.exception.DaoException;
import com.epam.library.exception.ServiceException;
import com.epam.library.service.implementation.AuthorServiceImpl;
import com.epam.library.service.implementation.BookRentalServiceImpl;
import com.epam.library.service.implementation.UserServiceImpl;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class UserServiceImplTest {

    @Test
    public void testLoginShouldReturnUserWhenLoginAndPasswordIsCorrect() throws DaoException, ServiceException {
        // given
        DaoHelperFactory daoHelperFactory = mock(DaoHelperFactory.class);
        DaoHelper helper = mock(DaoHelper.class);
        UserDao dao = mock(UserDao.class);
        User user = new User(
                1L, "Anna", "DeWitt", "+15556352794", "buker", "nightingale", Role.LIBRARIAN, false
        );
        Optional<User> optionalUser = Optional.of(user);

        when(daoHelperFactory.create()).thenReturn(helper);
        doNothing().when(helper).startTransaction();
        doNothing().when(helper).endTransaction();
        when(helper.createUserDao()).thenReturn(dao);
        when(dao.findUserByLoginAndPassword(anyString(), anyString())).thenReturn(optionalUser);

        UserServiceImpl service = new UserServiceImpl(daoHelperFactory);
        Optional<User> expectedUser = optionalUser;

        // when
        Optional<User> actualUser = service.login("buker", "nightingalse");

        // then
        Assert.assertEquals(expectedUser, actualUser);
        verify(daoHelperFactory, times(1)).create();
        verify(helper, times(1)).startTransaction();
        verify(helper, times(1)).endTransaction();
        verify(helper, times(1)).createUserDao();
        verify(dao, times(1)).findUserByLoginAndPassword(anyString(), anyString());
    }


    @Test
    public void testCalculateNumberOfRowsShouldCalculateCountOfRecordsWhenRecordsExist() throws DaoException, ServiceException {
        // given
        DaoHelperFactory daoHelperFactory = mock(DaoHelperFactory.class);
        DaoHelper helper = mock(DaoHelper.class);
        UserDao dao = mock(UserDao.class);

        when(daoHelperFactory.create()).thenReturn(helper);
        doNothing().when(helper).startTransaction();
        doNothing().when(helper).endTransaction();
        when(helper.createUserDao()).thenReturn(dao);
        when(dao.calculateNumberOfRows()).thenReturn(3);

        UserServiceImpl service = new UserServiceImpl(daoHelperFactory);
        int expectedNumberOfRows = 3;

        // when
        int actualNumberOfRows = service.calculateNumberOfRows();

        // then
        Assert.assertEquals(expectedNumberOfRows, actualNumberOfRows);
        verify(helper, times(1)).startTransaction();
        verify(helper, times(1)).endTransaction();
        verify(helper, times(1)).createUserDao();
        verify(dao, times(1)).calculateNumberOfRows();
    }
}
