package com.epam.library.command.implementation;

import com.epam.library.command.Command;
import com.epam.library.command.CommandResult;
import com.epam.library.command.ReCaptchaChecker;
import com.epam.library.exception.ServiceException;
import com.epam.library.entity.User;
import com.epam.library.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class LoginCommand implements Command {

    private static final String MAIN_PAGE = "pages/main.jsp";
    private static final String LOGIN_PAGE = "/index.jsp";

    private final ReCaptchaChecker checker = new ReCaptchaChecker();

    private final UserService service;

    public LoginCommand(UserService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request) throws ServiceException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String gReCaptchaResponse = request.getParameter("g-recaptcha-response");

        Optional<User> user = service.login(login, password);

        CommandResult result;
        if (user.isPresent() && checker.verify(gReCaptchaResponse)) {
            HttpSession httpSession = request.getSession();

            User registeredUser = user.get();
            httpSession.setAttribute("userId", registeredUser.getId());
            httpSession.setAttribute("userRole", registeredUser.getRole());
            result = CommandResult.redirect(MAIN_PAGE);
        } else {
            request.setAttribute("errorLoginPassMessage", "Invalid credentials or you're a robot");
            result = CommandResult.forward(LOGIN_PAGE);
        }

        return result;
    }
}
