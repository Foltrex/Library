package com.epam.library.security;

import com.epam.library.entity.Role;
import com.epam.library.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SecurityChecker {

    private static final String PAGE_REGEX = "(?<=\\\\)\\w+\\.jsp";

    public boolean isUserHasPermissionToPage(HttpServletRequest request, Role userRole) {
        String currentPage = getCurrentPage(request);
        SecurityConfig securityConfig = SecurityConfig.getInstance();
;
        List<String> allowedPagesForUser = securityConfig.getAllowedPagesForRole(userRole);

        return currentPage != null && allowedPagesForUser.contains(currentPage);
    }

    private String getCurrentPage(HttpServletRequest request) {
        Pattern pattern = Pattern.compile(PAGE_REGEX);
        Matcher matcher = pattern.matcher(request.getRequestURI());

        return matcher.find() ? matcher.group() : null;
    }
}
