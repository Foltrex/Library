package com.epam.library.security;

import com.epam.library.entity.Role;
import com.epam.library.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class SecurityChecker {

    public boolean isUserHasPermissionToPage(HttpServletRequest request, User user) {
        String currentPage = getCurrentPage(request);
        SecurityConfig securityConfig = SecurityConfig.getInstance();

        Role userRole = user.getRole();
        List<String> allowedPagesForUser = securityConfig.getAllowedPagesForRole(userRole);

        return allowedPagesForUser.contains(currentPage);
    }

    private String getCurrentPage(HttpServletRequest request) {
        String[] list = request.getRequestURI().split("/");
        String page = null;
        if (list[list.length - 1].indexOf(".jsp") > 0) {
            page = list[list.length - 1];
        }
        return page;
    }
}
