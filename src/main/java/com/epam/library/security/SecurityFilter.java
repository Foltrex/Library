package com.epam.library.security;

import com.epam.library.entity.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SecurityFilter implements Filter {

    private static final String LOGIN_PAGE = "login";
    private static final String ACCESS_DENIED_PAGE = "/jsp/accessDeniedView.jsp";

    private final SecurityChecker securityChecker;

    public SecurityFilter(SecurityChecker securityChecker) {
        this.securityChecker = securityChecker;
    }


    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        String commandLine = request.getParameter("command");
        if (commandLine.equals(LOGIN_PAGE)) {
            chain.doFilter(request, response);
            return;
        }

        HttpSession session = request.getSession();
        User loginedUser = (User) session.getAttribute("user");
        if (!securityChecker.isUserHasPermissionToPage(request, loginedUser)) {
            ServletContext servletContext = request.getServletContext();
            RequestDispatcher dispatcher = servletContext.getRequestDispatcher(ACCESS_DENIED_PAGE);
            dispatcher.forward(request, response);
            return;
        }

        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void destroy() {
    }
}
