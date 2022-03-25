package com.epam.library.command;

import com.epam.library.command.implementation.ChangeLocaleCommand;
import com.epam.library.command.implementation.ChangeUserBlockingCommand;
import com.epam.library.entity.Author;
import com.epam.library.entity.Role;
import com.epam.library.entity.User;
import com.epam.library.exception.PageCommandException;
import com.epam.library.exception.ServiceException;
import com.epam.library.service.AdminService;
import org.junit.Test;
import static org.mockito.Mockito.*;

import javax.servlet.http.HttpServletRequest;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

public class ChangeUserBlockingCommandTest {

    private final List<User> readers = Arrays.asList(
            new User(1L, "Hel", "Lo", "7788", "1111", "1111", Role.READER, false),
            new User(2L, "Goog", "Bye", "8877", "2222", "2222", Role.READER, false)
    );


    // TODO: el was changed(test it)
    @Test
    public void testExecuteShouldChangeUserBlockingWhenUserIdIdGiven() throws ServiceException, PageCommandException {
        // given
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getParameter("userId")).thenReturn("1");
        when(request.getParameter("userRole")).thenReturn("reader");
        when(request.getParameter("userBlocking")).thenReturn("0");

        AdminService service = mock(AdminService.class);
        doNothing().when(service).changeUserBlocking(anyLong(), anyBoolean());
        when(service.getUsers(any(Role.class))).thenReturn(readers);
        doNothing().when(request).setAttribute(anyString(), anyList());
        doNothing().when(request).setAttribute(anyString(), any(Role.class));

        ChangeUserBlockingCommand command = new ChangeUserBlockingCommand(service);

        // when
        CommandResult commandResult = command.execute(request);

        // then
        verify(request, times(3)).getParameter(anyString());
        verify(service, times(1)).changeUserBlocking(anyLong(), anyBoolean());
        verify(service, times(1)).getUsers(any(Role.class));
        verify(request, times(1)).setAttribute(anyString(), anyList());
    }
}
