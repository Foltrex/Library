package com.epam.library.filter;

import com.epam.library.entity.Role;
import com.epam.library.security.SecurityChecker;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SecurityFilter implements Filter {
    private static final String ERROR_PAGE = "/pages/errorPage.jsp";

    private static final SecurityChecker SECURITY_CHECKER = new SecurityChecker();

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;

        HttpSession session = request.getSession();
        Role userRole = (Role) session.getAttribute("userRole");

        if (SECURITY_CHECKER.isUserHasPermissionToContent(request, userRole) || SECURITY_CHECKER.isLoginPage(request)) {
            chain.doFilter(req, resp);
        } else {
            ServletContext servletContext = request.getServletContext();
            RequestDispatcher dispatcher = servletContext.getRequestDispatcher(ERROR_PAGE);
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
