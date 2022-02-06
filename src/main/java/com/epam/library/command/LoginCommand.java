package com.epam.library.command;

import com.epam.library.logic.LoginLogic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginCommand implements Command {
    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";
    private static final String RE_CAPTCHA = "g-recaptcha-response";

    @Override
    public String execute(HttpServletRequest request) {
        String login = request.getParameter(PARAM_NAME_LOGIN);
        String password = request.getParameter(PARAM_NAME_PASSWORD);
        String gReCaptchaResponse = request.getParameter(RE_CAPTCHA);

        String page = null;
        LoginLogic loginLogic = new LoginLogic();
        if (loginLogic.checkLogin(login, password, gReCaptchaResponse)) {
            HttpSession httpSession = request.getSession();
            httpSession.setAttribute("user", login);
            page = "WEB-INF/jsp/main.jsp";
        } else {
            request.setAttribute("errorLoginPassMessage", "Invalid credentials or you're a robot");
            page = "index.jsp";
        }

        return page;
    }
}
