package com.epam.library.command;

import com.epam.library.command.implementation.LoginCommand;
import com.epam.library.entity.Role;
import com.epam.library.entity.User;
import com.epam.library.exception.PageCommandException;
import com.epam.library.exception.ServiceException;
import com.epam.library.service.UserService;
import org.junit.Test;

import static org.mockito.Mockito.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class LoginCommandTest {

    @Test
    public void testExecuteShouldLoginUserWhenLoginAndPasswordIsCorrect() throws ServiceException, PageCommandException {
        // given
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getParameter("login")).thenReturn("1111");
        when(request.getParameter("password")).thenReturn("1111");
        when(request.getParameter("g-recaptcha-response")).thenReturn("98sydf9h");

        Optional<User> user = Optional.of(new User(1L, "Ali", "Baba", "1234", "admin", "admin", Role.LIBRARIAN, false));
        UserService service = mock(UserService.class);
        when(service.login(anyString(), anyString())).thenReturn(user);

        ReCaptchaChecker checker = mock(ReCaptchaChecker.class);
        when(checker.verify(anyString())).thenReturn(true);

        HttpSession session = mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);
        doNothing().when(session).setAttribute(anyString(), any());
        when(request.getContextPath()).thenReturn("/library/");

        LoginCommand command = new LoginCommand(service, checker);

        // when
        CommandResult commandResult = command.execute(request);

        // then
        verify(request, times(3)).getParameter(anyString());
        verify(service, times(1)).login(anyString(), anyString());
        verify(checker, times(1)).verify(anyString());
        verify(request, times(1)).getSession();
        verify(session, times(2)).setAttribute(anyString(), any());
    }

    // TODO: write for banned users
}
