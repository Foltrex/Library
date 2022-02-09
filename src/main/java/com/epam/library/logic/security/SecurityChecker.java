package com.epam.library.logic.security;

import com.epam.library.models.Role;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class SecurityChecker {

    private final SecurityConfig securityConfig;
    private final UrlPattern urlPattern;

    public SecurityChecker(SecurityConfig securityConfig, UrlPattern urlPattern) {
        this.securityConfig = securityConfig;
        this.urlPattern = urlPattern;
    }

    public boolean isUserHasPermissionToPage(HttpServletRequest request) {
        String pageUrlPattern = urlPattern.getUrlPattern(request);

        for (Role role : Role.values()) {

            String roleString = String.valueOf(role);
            if (!request.isUserInRole(roleString)) {
                continue;
            }

            List<String> allowedPagesForRole = securityConfig.getAllowedPagesForRole(role);
            if (allowedPagesForRole != null && allowedPagesForRole.contains(pageUrlPattern)) {
                return true;
            }
        }

        return false;
    }
}
