package com.epam.library.filter;

import com.epam.library.command.Page;
import com.epam.library.entity.Role;
import com.epam.library.security.SecurityChecker;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/** Checks logged in user */
public class SecurityFilter implements Filter {

    private final SecurityChecker securityChecker = new SecurityChecker();

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;

        HttpSession session = request.getSession();
        Role userRole = (Role) session.getAttribute("userRole");

        if (securityChecker.isUserHasPermissionToContent(request, userRole) || securityChecker.isAuthorizationPage(request)) {
            chain.doFilter(req, resp);
        } else {
            ServletContext servletContext = request.getServletContext();
            RequestDispatcher dispatcher = servletContext.getRequestDispatcher(Page.ERROR.getPath());
            dispatcher.forward(req, resp);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
