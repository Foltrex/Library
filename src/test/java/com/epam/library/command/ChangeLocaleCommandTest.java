package com.epam.library.command;

import com.epam.library.command.implementation.ChangeLocaleCommand;
import com.epam.library.exception.PageCommandException;
import com.epam.library.exception.ServiceException;
import org.junit.Test;
import static org.mockito.Mockito.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ChangeLocaleCommandTest {

    @Test
    public void testExecuteShouldChangeLocaleWhenIsRequested() throws PageCommandException, ServiceException {
        // given
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getParameter(anyString())).thenReturn("en_US");

        HttpSession session = mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);
        doNothing().when(session).setAttribute(anyString(), anyString());

        ChangeLocaleCommand command = new ChangeLocaleCommand();

        // when
        CommandResult commandResult = command.execute(request);

        // then
        verify(request, times(1)).getParameter(anyString());
        verify(request, times(1)).getSession();
        verify(session, times(1)).setAttribute(anyString(), anyString());
    }
}
