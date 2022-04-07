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

    private final UserService service;
    private final ReCaptchaChecker checker;

    public LoginCommand(UserService service, ReCaptchaChecker checker) {
        this.service = service;
        this.checker = checker;
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
            result = CommandResult.forward(Page.LOGIN.getPath());
        } else {
            request.setAttribute("errorLoginPassMessage", "Invalid credentials or you're a robot");
            result = CommandResult.forward(Page.LOGIN.getPath());
        }

        return result;
    }
}
