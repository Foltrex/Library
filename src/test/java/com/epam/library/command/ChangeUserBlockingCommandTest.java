package com.epam.library.command;

import com.epam.library.command.impl.ChangeUserBlockingCommand;
import com.epam.library.entity.Role;
import com.epam.library.entity.User;
import com.epam.library.exception.PageCommandException;
import com.epam.library.exception.ServiceException;
import com.epam.library.service.AdminService;
import com.google.common.collect.ImmutableList;
import org.junit.Test;
import static org.mockito.Mockito.*;

import javax.servlet.http.HttpServletRequest;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

public class ChangeUserBlockingCommandTest {

    private final List<User> readers = ImmutableList.of(
            User.builder()
                    .id(1L)
                    .name("Hel")
                    .surname("Lo")
                    .phoneNumber("7788")
                    .login("1111")
                    .password("1111")
                    .role(Role.READER)
                    .isBanned(false)
                    .build(),

            User.builder()
                    .id(2L)
                    .name("Goog")
                    .surname("Bye")
                    .phoneNumber("8877")
                    .login("2222")
                    .password("2222")
                    .role(Role.READER)
                    .isBanned(false)
                    .build()
    );


    @Test
    public void testExecuteShouldChangeUserBlockingWhenUserIdIdGiven() throws ServiceException, PageCommandException {
        // given
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getParameter("userId")).thenReturn("1");
        when(request.getParameter("userRole")).thenReturn("reader");
        when(request.getParameter("userIsBanned")).thenReturn("0");

        AdminService service = mock(AdminService.class);
        doNothing().when(service).changeUserBlocking(anyLong(), anyBoolean());
        when(service.getUsers(any(Role.class))).thenReturn(readers);
        doNothing().when(request).setAttribute(anyString(), anyList());
        doNothing().when(request).setAttribute(anyString(), any(Role.class));

        ChangeUserBlockingCommand command = new ChangeUserBlockingCommand(service);

        // when
        CommandResult commandResult = command.execute(request);

        // then
        verify(service, times(1)).changeUserBlocking(anyLong(), anyBoolean());
        verify(service, times(1)).getUsers(any(Role.class));
        verify(request, times(1)).setAttribute(anyString(), anyList());
    }
}
