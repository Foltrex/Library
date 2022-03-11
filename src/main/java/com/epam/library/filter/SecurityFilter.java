package com.epam.library.filter;

import com.epam.library.command.Page;
import com.epam.library.entity.Role;
import com.epam.library.entity.User;
import com.epam.library.security.SecurityChecker;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SecurityFilter implements Filter {

    private static final String ACCESS_DENIED_PAGE = "/pages/accessDeniedView.jsp";

    private final SecurityChecker securityChecker = new SecurityChecker();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        String commandLine = request.getParameter("command");
        if (commandLine != null && commandLine.equals(Page.LOGIN.getName()) || request.getRequestURI().endsWith(".css") || request.getRequestURI().endsWith(".js") || request.getRequestURI().endsWith(".png")) {
            chain.doFilter(request, response);
            return;
        }

        HttpSession session = request.getSession();
        if (session.getAttribute("userRole") != null) {
            Role userRole = (Role) session.getAttribute("userRole");
            if (!securityChecker.isUserHasPermissionToPage(request, userRole)) {
                ServletContext servletContext = request.getServletContext();
                RequestDispatcher dispatcher = servletContext.getRequestDispatcher(ACCESS_DENIED_PAGE);
                dispatcher.forward(req, resp);
                return;
            }
        }

        chain.doFilter(req, resp);
    }

    @Override
    public void destroy() {

    }
}
