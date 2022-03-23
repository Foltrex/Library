package com.epam.library.command;

import com.epam.library.command.implementation.LoginCommand;
import com.epam.library.command.implementation.LogoutCommand;
import com.epam.library.entity.Role;
import com.epam.library.entity.User;
import com.epam.library.service.UserService;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class LogoutCommandTest {

    @Test
    public void testExecuteShouldLogoutUserWhenLogout() {
        // given
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpSession session = mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);
        doNothing().when(session).invalidate();

        LogoutCommand command = new LogoutCommand();

        // when
        CommandResult commandResult = command.execute(request);

        // then
        verify(request, times(1)).getSession();
        verify(session, times(1)).invalidate();
    }
}
