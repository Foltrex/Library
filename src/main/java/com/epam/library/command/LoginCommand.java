package com.epam.library.command;

import com.epam.library.logic.LoginLogic;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class LoginCommand implements Command {
    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";

    @Override
    public String execute(HttpServletRequest request) {
        String login = request.getParameter(PARAM_NAME_LOGIN);
        String pass = request.getParameter(PARAM_NAME_PASSWORD);

        String page = null;
        LoginLogic loginLogic = new LoginLogic();
        if (loginLogic.checkLogin(login, pass)) {
            HttpSession httpSession = request.getSession();
            httpSession.setAttribute("user", login);
            page = "WEB-INF/jsp/main.jsp";
        } else {
            request.setAttribute("errorLoginPassMessage", "Invalid password or login");
            page = "index.jsp";
        }

        return page;
    }
}
