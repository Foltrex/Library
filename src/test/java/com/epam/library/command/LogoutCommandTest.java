package com.epam.library.command;

import com.epam.library.command.impl.LogoutCommand;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.mockito.ArgumentMatchers.any;
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
