package com.epam.library.filter;

import com.epam.library.command.Page;
import com.epam.library.entity.Role;
import com.epam.library.security.SecurityChecker;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SecurityFilter implements Filter {
    private static final String ACCESS_DENIED_PAGE = "/pages/accessDeniedView.jsp";

    private static final String PAGE_REGEX = "(?<=(library))/(\\w+\\.jsp)?$";

    private final SecurityChecker securityChecker = new SecurityChecker();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        String requestURI = request.getRequestURI();

        Pattern pattern = Pattern.compile(PAGE_REGEX);
        Matcher matcher = pattern.matcher(requestURI);
        String pageSuffix = matcher.find() ? matcher.group() : null;
        String loginPage = Page.LOGIN.getName();

        HttpSession session = request.getSession();
        Role userRole = (Role) session.getAttribute("userRole");

        if (securityChecker.isUserHasPermissionToContent(request, userRole)
                || loginPage.equals(pageSuffix) || requestURI.equals("/library/")) {

            chain.doFilter(req, resp);
        } else {
            ServletContext servletContext = request.getServletContext();
            RequestDispatcher dispatcher = servletContext.getRequestDispatcher(ACCESS_DENIED_PAGE);
            dispatcher.forward(req, resp);
        }
    }

    @Override
    public void destroy() {

    }
}
