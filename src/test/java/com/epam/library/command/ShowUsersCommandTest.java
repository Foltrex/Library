package com.epam.library.command;

import java.util.Arrays;
import java.util.List;

import com.epam.library.command.implementation.ShowUsersCommand;
import com.epam.library.entity.Role;
import com.epam.library.entity.User;
import com.epam.library.exception.PageCommandException;
import com.epam.library.exception.ServiceException;
import com.epam.library.service.AdminService;
import org.junit.Test;

import static org.mockito.Mockito.*;

import javax.servlet.http.HttpServletRequest;

public class ShowUsersCommandTest {

    private final List<User> users = Arrays.asList(
            new User(1L, "Jay", "Kratt", "+375443539693", "1111", "1111", Role.READER, false),
            new User(2L, "Ray", "Pratt", "+375292435894", "2222", "2222", Role.READER, false)
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
