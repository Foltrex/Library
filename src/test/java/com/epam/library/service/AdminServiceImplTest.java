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
import com.google.common.collect.ImmutableList;
import org.junit.Assert;
import org.junit.Test;

import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

public class AdminServiceImplTest {

    private final List<User> readers = ImmutableList.of(
            User.builder()
                    .id(1L)
                    .name("Dmitriy")
                    .surname("Reznov")
                    .phoneNumber("+796002485317")
                    .login("Vorkuta")
                    .password("Nova6")
                    .role(Role.READER)
                    .isBanned(false)
                    .build(),
            User.builder()
                    .id(2L)
                    .name("David")
                    .surname("Mayson")
                    .phoneNumber("+15052347502")
                    .login("agent")
                    .password("CIA")
                    .role(Role.READER)
                    .isBanned(false)
                    .build()
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
