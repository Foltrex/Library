//package com.epam.library.filter;
//
//import com.epam.library.entity.Role;
//import com.epam.library.entity.User;
//import com.epam.library.security.SecurityChecker;
//
//import javax.servlet.*;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//
//public class SecurityFilter implements Filter {
//
//    private static final String LOGIN_PAGE = "login";
//    private static final String ACCESS_DENIED_PAGE = "/jsp/accessDeniedView.jsp";
//
//    private final SecurityChecker securityChecker;
//
//    public SecurityFilter(SecurityChecker securityChecker) {
//        this.securityChecker = securityChecker;
//    }
//
//
//    @Override
//    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
//        HttpServletRequest request = (HttpServletRequest) req;
//        HttpServletResponse response = (HttpServletResponse) resp;
//
//        String commandLine = request.getParameter("command");
//        if (commandLine.equals(LOGIN_PAGE)) {
//            chain.doFilter(request, response);
//            return;
//        }
//
//        HttpSession session = request.getSession();
//        Role userRole = (Role) session.getAttribute("userRole");
//        if (!securityChecker.isUserHasPermissionToPage(request, userRole)) {
//            ServletContext servletContext = request.getServletContext();
//            RequestDispatcher dispatcher = servletContext.getRequestDispatcher(ACCESS_DENIED_PAGE);
//            dispatcher.forward(request, response);
//            return;
//        }
//
//        chain.doFilter(request, response);
//    }
//
//    @Override
//    public void init(FilterConfig filterConfig) {
//    }
//
//    @Override
//    public void destroy() {
//    }
//}
