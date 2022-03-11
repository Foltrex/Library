package com.epam.library.security;

import com.epam.library.command.CommandName;
import com.epam.library.entity.Role;
import com.epam.library.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SecurityChecker {

    private static final String PAGE_REGEX = "(?<=/)\\w+\\.jsp";

    private static final String CORRECT_PAGES_REGEX = "/library/controller\\?command=[\\p{L}_]+";

    public boolean isUserHasPermissionToPage(HttpServletRequest request, Role userRole) {
        String currentPage = getCurrentPage(request);
        String requestParameter = getRequestParameter(request);
        CommandName requestCommand = CommandName.valueOfName(requestParameter);
        SecurityConfig securityConfig = SecurityConfig.getInstance();

        List<String> allowedPagesForUser = securityConfig.getAllowedPagesForRole(userRole);
        List<CommandName> allowedCommandForUser = securityConfig.getAllowedCommandsForRole(userRole);

        return allowedPagesForUser.contains(currentPage) || allowedCommandForUser.contains(requestCommand);
    }

    private String getCurrentPage(HttpServletRequest request) {
        Pattern pattern = Pattern.compile(PAGE_REGEX);
        Matcher matcher = pattern.matcher(request.getRequestURI());

        return matcher.find() ? matcher.group() : null;
    }

    private String getRequestParameter(HttpServletRequest request) {
        SecurityConfig config = SecurityConfig.getInstance();
        Pattern pattern = Pattern.compile(CORRECT_PAGES_REGEX);
        Matcher matcher = pattern.matcher(request.getRequestURI());

        return request.getParameter("command");
    }
}
