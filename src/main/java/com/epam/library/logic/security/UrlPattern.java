package com.epam.library.logic.security;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;
import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Map;

public class UrlPattern {

    public String getUrlPattern(HttpServletRequest request) {

        String servletPath = request.getServletPath();
        String pathInfo = request.getPathInfo();
        String urlPattern = null;

        if (pathInfo != null) {
            urlPattern = servletPath + "/*";
            return urlPattern;
        }

        ServletContext servletContext = request.getServletContext();
        urlPattern = servletPath;
        if (hasUrlPattern(servletContext, urlPattern)) {
            return urlPattern;
        }

        return "/";
    }



    private boolean hasUrlPattern(ServletContext servletContext, String urlPattern) {
        Map<String, ? extends ServletRegistration> registrations = servletContext.getServletRegistrations();

        for (ServletRegistration servletRegistration : registrations.values()) {
            Collection<String> mappings = servletRegistration.getMappings();
            if (mappings.contains(urlPattern)) {
                return true;
            }
        }

        return false;
    }
}
