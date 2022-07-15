package com.epam.library.command;

import java.util.Arrays;
import java.util.List;

import com.epam.library.command.impl.ShowUsersCommand;
import com.epam.library.entity.Role;
import com.epam.library.entity.User;
import com.epam.library.exception.PageCommandException;
import com.epam.library.exception.ServiceException;
import com.epam.library.service.AdminService;
import com.google.common.collect.ImmutableList;
import org.junit.Test;

import static org.mockito.Mockito.*;

import javax.servlet.http.HttpServletRequest;

public class ShowUsersCommandTest {

    private final List<User> users = ImmutableList.of(
            User.builder()
                    .id(1L)
                    .name("Jay")
                    .surname("Kratt")
                    .phoneNumber("+375443539693")
                    .login("1111")
                    .password("1111")
                    .role(Role.READER)
                    .isBanned(false)
                    .build(),
            User.builder()
                    .id(2L)
                    .name("Ray")
                    .surname("Pratt")
                    .phoneNumber("+375292435894")
                    .login("2222")
                    .password("2222")
                    .role(Role.READER)
                    .isBanned(false)
                    .build()
    );


    @Test
    public void testExecuteShouldShowUsersWhenAdminSendRequest() throws ServiceException, PageCommandException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        AdminService adminService = mock(AdminService.class);
        when(adminService.getUsers(any(Role.class))).thenReturn(users);
        doNothing().when(request).setAttribute(anyString(), any());

        ShowUsersCommand command = new ShowUsersCommand(adminService, Role.READER);

        // when
        CommandResult commandResult = command.execute(request);

        // then
        verify(adminService, times(1)).getUsers(any(Role.class));
        verify(request, times(1)).setAttribute(anyString(), any());
    }
}
