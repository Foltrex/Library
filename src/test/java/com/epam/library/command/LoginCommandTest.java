package com.epam.library.command;

import com.epam.library.command.impl.LoginCommand;
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

    private final User registeredUser = new User(1L, "Ali", "Baba", "1234", "admin", "admin", Role.LIBRARIAN, false);
    private final User bannedUser = new User(2L, "Muhamed", "Ali", "3294", "reader", "reader", Role.READER, true);

    @Test
    public void testExecuteShouldLoginUserWhenLoginAndPasswordIsCorrect() throws ServiceException, PageCommandException {
        // given
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getParameter("userLogin")).thenReturn("1111");
        when(request.getParameter("userPassword")).thenReturn("1111");
        when(request.getParameter("g-recaptcha-response")).thenReturn("98sydf9h");

        Optional<User> optionalUser = Optional.of(registeredUser);
        UserService service = mock(UserService.class);
        when(service.login(anyString(), anyString())).thenReturn(optionalUser);

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
        verify(service, times(1)).login(anyString(), anyString());
        verify(checker, times(1)).verify(anyString());
        verify(request, times(1)).getSession();
        verify(session, times(2)).setAttribute(anyString(), any());
    }

    @Test
    public void testExecuteShouldNotAuthorizeUserWhenLoginAndPasswordIsWrong() throws ServiceException, PageCommandException {
        // given
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getParameter("userLogin")).thenReturn("wrongLogin");
        when(request.getParameter("userPassword")).thenReturn("wrongPassword");
        when(request.getParameter("g-recaptcha-response")).thenReturn("98sydf9h");

        Optional<User> optionalUser = Optional.of(bannedUser);
        UserService service = mock(UserService.class);
        when(service.login(anyString(), anyString())).thenReturn(optionalUser);

        ReCaptchaChecker checker = mock(ReCaptchaChecker.class);
        when(checker.verify(anyString())).thenReturn(true);

        doNothing().when(request).setAttribute(anyString(), anyString());

        LoginCommand command = new LoginCommand(service, checker);

        // when
        CommandResult commandResult = command.execute(request);

        // then
        verify(request, times(3)).getParameter(anyString());
        verify(service, times(1)).login(anyString(), anyString());
        verifyNoInteractions(checker);
        verify(request, times(1)).setAttribute(anyString(), anyString());
    }
}
