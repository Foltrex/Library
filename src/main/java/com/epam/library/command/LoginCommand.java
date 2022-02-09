package com.epam.library.command;

import com.epam.library.data.connect.ConnectionPool;
import com.epam.library.data.dao.UserDAO;
import com.epam.library.logic.login.LoginLogic;
import com.epam.library.models.User;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.SQLException;

public class LoginCommand implements Command {
    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";
    private static final String RE_CAPTCHA = "g-recaptcha-response";

    // TODO: dao find user to save user in session

    @Override
    public String execute(HttpServletRequest request) {
        String login = request.getParameter(PARAM_NAME_LOGIN);
        String password = request.getParameter(PARAM_NAME_PASSWORD);
        String gReCaptchaResponse = request.getParameter(RE_CAPTCHA);

        User user = searchUserByLogin(login);

        String page = null;
        LoginLogic loginLogic = new LoginLogic(user);
        if (loginLogic.checkLogin(login, password, gReCaptchaResponse)) {
            HttpSession httpSession = request.getSession();
            httpSession.setAttribute("user", login);
            page = "/jsp/main.jsp";
        } else {
            request.setAttribute("errorLoginPassMessage", "Invalid credentials or you're a robot");
            page = "index.jsp";
        }

        return page;
    }

    private User searchUserByLogin(String login) {

       User user = null;
        try {
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            Connection connection = connectionPool.getConnection();
            UserDAO userDAO = new UserDAO(connection);

            user = userDAO.findUserByLogin(login);

        } catch (NamingException | SQLException e) {
            e.printStackTrace();
        } finally {
            // TODO: maybe call close method for connetion pool
        }

        // return new User(1, "Ira", "Korchigina", "+375444567890", "bumaga", "tree", Role.READER);
        return user;
    }
}
