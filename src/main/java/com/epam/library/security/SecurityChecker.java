package com.epam.library.security;

import com.epam.library.command.CommandName;
import com.epam.library.command.Page;
import com.epam.library.entity.Role;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SecurityChecker {

    private final List<String> fileExtensions = Arrays.asList(".png", ".css", ".js", ".ico");

    private static final String PAGE_REGEX = "(?<=(library))/(\\w+\\.jsp)?$";

    public boolean isLoginPage(HttpServletRequest request) {
        String requestURI = request.getRequestURI();

        Pattern pattern = Pattern.compile(PAGE_REGEX);
        Matcher matcher = pattern.matcher(requestURI);
        String pageSuffix = matcher.find() ? matcher.group() : null;
        String loginPage = Page.LOGIN.getName();

        String commandLine = request.getParameter("command");
        CommandName command = CommandName.valueOfName(commandLine);

        return loginPage.equals(pageSuffix) || requestURI.equals("/library/") || CommandName.LOGIN.equals(command);
    }

    public boolean isUserHasPermissionToContent(HttpServletRequest request, Role userRole) {
        return isUserHasPermissionToFile(request) || isUserHasPermissionToCommand(request, userRole);
    }

    private boolean isUserHasPermissionToCommand(HttpServletRequest request, Role userRole) {
        String commandLine = request.getParameter("command");
        if (commandLine != null && userRole != null) {
            SecurityConfig securityConfig = SecurityConfig.getInstance();
            List<CommandName> allowedCommandForUser = securityConfig.getAllowedCommandsForRole(userRole);
            CommandName requestCommand = CommandName.valueOfName(commandLine);
            return allowedCommandForUser.contains(requestCommand);
        }

        return false;
    }

    private boolean isUserHasPermissionToFile(HttpServletRequest request) {
        String requestURI = request.getRequestURI();

        boolean isEndWithExtension = false;
        ListIterator<String> listIterator = fileExtensions.listIterator();
        while (listIterator.hasNext() && !isEndWithExtension) {
            String fileExtension = listIterator.next();
            isEndWithExtension = requestURI.endsWith(fileExtension);
        }

        return isEndWithExtension;
    }
}
