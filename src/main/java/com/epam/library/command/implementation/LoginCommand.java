package com.epam.library.command.implementation;

import com.epam.library.command.*;
import com.epam.library.exception.ServiceException;
import com.epam.library.entity.User;
import com.epam.library.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class LoginCommand implements Command {

    private static final String MAIN_PAGE_COMMAND = CommandName.SHOW_BOOKS.getServletCommand("controller");

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
        if (user.isPresent() && !user.get().isBanned() && checker.verify(gReCaptchaResponse)) {
            HttpSession httpSession = request.getSession();

            User registeredUser = user.get();
            httpSession.setAttribute("userId", registeredUser.getId());
            httpSession.setAttribute("userRole", registeredUser.getRole());
            result = CommandResult.redirect(request.getContextPath() + MAIN_PAGE_COMMAND);
        } else if (user.isPresent() && user.get().isBanned()) {
            request.setAttribute("errorLoginPassMessage", "You was banned by admin");
            result = CommandResult.forward(Page.LOGIN.getName());
        } else {
            request.setAttribute("errorLoginPassMessage", "Invalid credentials or you're a robot");
            result = CommandResult.forward(Page.LOGIN.getName());
        }

        return result;
    }
}
