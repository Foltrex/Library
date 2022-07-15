package com.epam.library.filter;

import javax.servlet.*;
import java.io.IOException;

/** Sets utf encoding */
public class EncodingFilter implements Filter {

    private String encoding = "utf-8";
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding(encoding);
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String encodingParam = filterConfig.getInitParameter("encoding");
        if (encodingParam != null) {
            encoding = encodingParam;
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
