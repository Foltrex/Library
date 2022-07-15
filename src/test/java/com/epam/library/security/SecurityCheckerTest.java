package com.epam.library.security;

import com.epam.library.entity.Role;
import org.junit.Assert;
import org.junit.Test;

import static org.mockito.Mockito.*;

import javax.servlet.http.HttpServletRequest;


public class SecurityCheckerTest {

    private static final String LOGIN_PAGE = "/library/index.jsp";
    private static final String LOGIN_COMMAND = "login";
    private static final String CONTEXT_PATH = "/library/";

    private final SecurityChecker checker = new SecurityChecker();


    @Test
    public void testIsLoginPageShouldReturnTrueWhenPageIsLogin() {
        // given
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getRequestURI()).thenReturn(LOGIN_PAGE);
        when(request.getParameter("command")).thenReturn("not_login_command");

        // when
        boolean isLoginPage = checker.isAuthorizationPage(request);

        // then
        Assert.assertTrue(isLoginPage);
    }


    @Test
    public void testIsLoginPageShouldReturnTrueWhenCommandIsLogin() {
        // given
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getRequestURI()).thenReturn("not_login_uri");
        when(request.getParameter("command")).thenReturn(LOGIN_COMMAND);

        // when
        boolean isLoginCommand = checker.isAuthorizationPage(request);

        // then
        Assert.assertTrue(isLoginCommand);
    }

    @Test
    public void testIsLoginPageShouldReturnTrueWhenUriIsOnlyContextPage() {
        // given
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getRequestURI()).thenReturn(CONTEXT_PATH);
        when(request.getParameter("command")).thenReturn("not_login_command");

        // when
        boolean isLoginPage = checker.isAuthorizationPage(request);

        // then
        Assert.assertTrue(isLoginPage);
    }


    @Test
    public void testIsUserHasPermissionToContentShouldReturnTrueWhenFileIsCss() {
        // given
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getParameter("command")).thenReturn("not_allowed_command");
        when(request.getRequestURI()).thenReturn("some_context/file.css");

        // when
        boolean isUserHasPermissionToContent = checker.isUserHasPermissionToContent(request, Role.ADMIN);

        Assert.assertTrue(isUserHasPermissionToContent);
    }


    @Test
    public void testIsUserHasPermissionToContentShouldReturnTrueWhenFileIsPng() {
        // given
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getParameter("command")).thenReturn("not_allowed_command");
        when(request.getRequestURI()).thenReturn("some_context/file.png");

        // when
        boolean isUserHasPermissionToContent = checker.isUserHasPermissionToContent(request, Role.ADMIN);

        Assert.assertTrue(isUserHasPermissionToContent);
    }


    @Test
    public void testIsUserHasPermissionToContentShouldReturnTrueWhenFileIsJs() {
        // given
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getParameter("command")).thenReturn("not_allowed_command");
        when(request.getRequestURI()).thenReturn("some_context/file.js");

        // when
        boolean isUserHasPermissionToContent = checker.isUserHasPermissionToContent(request, Role.ADMIN);

        Assert.assertTrue(isUserHasPermissionToContent);
    }
}
