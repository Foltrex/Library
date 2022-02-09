package com.epam.library.logic.security;

import com.epam.library.models.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SecurityFilter implements Filter {

    private static final String LOGIN_PAGE = "/";
    private static final String ACCESS_DENIED_PAGE = "/jsp/accessDeniedView.jsp";

    private final SecurityChecker securityChecker;

    public SecurityFilter(SecurityChecker securityChecker) {
        this.securityChecker = securityChecker;
    }

    // TODO: write comments
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        String servletPath = request.getServletPath();
        if (servletPath.equals(LOGIN_PAGE)) {
            chain.doFilter(request, response);
            return;
        }

        HttpSession httpSession = request.getSession();

        if (!securityChecker.isUserHasPermissionToPage(request)) {
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
