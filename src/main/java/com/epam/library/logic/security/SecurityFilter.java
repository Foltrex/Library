package com.epam.library.logic.security;

import com.epam.library.models.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SecurityFilter implements Filter {

    private static final String LOGIN_PAGE = "/";

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
        User loginedUser = (User) httpSession.getAttribute("user");
        HttpServletRequest wrapRequest = request;

        if (loginedUser != null) {
            String userLogin = loginedUser.getLogin();
            String role = String.valueOf(loginedUser.getRole());

            wrapRequest = new UserRoleRequestWrapper(request, userLogin, role);
        }

        if (securityChecker.isSecurityPage(request)) {
            if (!securityChecker.hasPermission(wrapRequest)) {

                ServletContext servletContext = request.getServletContext();
                RequestDispatcher dispatcher = servletContext.getRequestDispatcher("WEB-INF/jsp/accessDeniedView.jsp");
                dispatcher.forward(request, response);
                return;
            }
        }

        chain.doFilter(wrapRequest, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {
    }
}
