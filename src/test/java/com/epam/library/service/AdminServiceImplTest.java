package com.epam.library.service;

import com.epam.library.dao.DaoHelper;
import com.epam.library.dao.DaoHelperFactory;
import com.epam.library.dao.UserDao;
import com.epam.library.dao.UserRoleDao;
import com.epam.library.entity.Role;
import com.epam.library.entity.User;
import com.epam.library.exception.DaoException;
import com.epam.library.exception.ServiceException;
import com.epam.library.service.impl.AdminServiceImpl;
import org.junit.Assert;
import org.junit.Test;

import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

public class AdminServiceImplTest {

    private final List<User> readers = Arrays.asList(
            new User(1L, "Dmitriy", "Reznov", "+796002485317", "Vorkuta", "Nova6", Role.READER, false),
            new User(2L, "David", "Mayson", "+15052347502", "agent", "CIA", Role.READER, false)
    );


    @Test
    public void testGetUsersShouldReturnAllUsersOfApplicationWhenDaoIsCorrect() throws DaoException, ServiceException {
        // given
        DaoHelperFactory daoHelperFactory = mock(DaoHelperFactory.class);
        DaoHelper helper = mock(DaoHelper.class);
        UserRoleDao dao = mock(UserRoleDao.class);

        when(daoHelperFactory.create()).thenReturn(helper);
        doNothing().when(helper).startTransaction();
        doNothing().when(helper).endTransaction();
        when(helper.createUserRoleDao()).thenReturn(dao);
        when(dao.findUsersByRole(any(Role.class))).thenReturn(readers);


        AdminServiceImpl service = new AdminServiceImpl(daoHelperFactory);
        List<User> expectedUsers = readers;

        // when
        List<User> actualUsers = service.getUsers(Role.READER);

        // then
        Assert.assertEquals(expectedUsers, actualUsers);

        verify(daoHelperFactory, times(1)).create();
        verify(helper, times(1)).startTransaction();
        verify(helper, times(1)).endTransaction();
        verify(helper, times(1)).createUserRoleDao();
        verify(dao, times(1)).findUsersByRole(any(Role.class));
    }

    @Test
    public void testChangeUserBlockingShouldChangeUserBlockingWhenUserExist() throws DaoException, ServiceException {
        // given
        DaoHelperFactory daoHelperFactory = mock(DaoHelperFactory.class);
        DaoHelper helper = mock(DaoHelper.class);
        UserDao dao = mock(UserDao.class);

        when(daoHelperFactory.create()).thenReturn(helper);
        doNothing().when(helper).startTransaction();
        doNothing().when(helper).endTransaction();
        when(helper.createUserDao()).thenReturn(dao);
        doNothing().when(dao).updateUserBlockingById(anyLong(), anyBoolean());

        AdminServiceImpl service = new AdminServiceImpl(daoHelperFactory);

        // when
        service.changeUserBlocking(1L, false);

        // then
        verify(daoHelperFactory, times(1)).create();
        verify(helper, times(1)).startTransaction();
        verify(helper, times(1)).endTransaction();
        verify(helper, times(1)).createUserDao();
        verify(dao, times(1)).updateUserBlockingById(anyLong(), anyBoolean());
    }
}
