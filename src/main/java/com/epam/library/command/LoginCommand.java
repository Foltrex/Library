package com.epam.library.command;

import com.epam.library.logic.LoginLogic;

import jakarta.servlet.http.HttpServletRequest;

public class LoginCommand implements Command {
    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";

    @Override
    public String execute(HttpServletRequest request) {
        String login = request.getParameter(PARAM_NAME_LOGIN);
        String pass = request.getParameter(PARAM_NAME_PASSWORD);

        String page = null;
        if (LoginLogic.checkLogin(login, pass)) {
            request.getSession().setAttribute("user", login);
            page = "WEB-INF/jsp/main.jsp";
        } else {
            request.setAttribute("errorLoginPassMessage", "Invalid password or login");
            page = "WEB-INF/jsp/login.jsp";
        }

        return page;
    }
}
