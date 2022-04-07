package com.epam.library.command.implementation;

import com.epam.library.command.Command;
import com.epam.library.command.CommandName;
import com.epam.library.command.CommandResult;
import com.epam.library.command.Page;
import com.epam.library.entity.*;
import com.epam.library.exception.PageCommandException;
import com.epam.library.exception.ServiceException;
import com.epam.library.service.UserService;
import com.epam.library.validator.UserValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

// TODO: write tests
public class SaveUserCommand implements Command {
    private static final String MAIN_PAGE_COMMAND = CommandName.SHOW_BOOKS.getServletCommand("controller");

    private final UserService userService;

    private final UserValidator validator;

    public SaveUserCommand(UserService userService, UserValidator validator) {
        this.userService = userService;
        this.validator = validator;
    }

    // TODO: finish  with this, test, validator, dao and localization of this page
    // TODO: users don't save
    @Override
    public CommandResult execute(HttpServletRequest req) throws ServiceException, PageCommandException {
        User user = extractUserFromRequest(req);
        String secondEnteredPassword = req.getParameter("password-repeat");

        CommandResult result;
        if (validator.isPasswordCoincidental(user.getPassword(), secondEnteredPassword)) {
            userService.signUp(user);

            Optional<User> optionalUser = userService.login(user.getLogin(), user.getPassword());
            User registeredUser = optionalUser.get();

            HttpSession httpSession = req.getSession();
            httpSession.setAttribute("userId", registeredUser.getId());
            httpSession.setAttribute("userRole", registeredUser.getRole());
            result = CommandResult.redirect(req.getContextPath() + MAIN_PAGE_COMMAND);
        } else {
            req.setAttribute("error-message", "Passwords are difference");
            // TODO: make redirection
            result = CommandResult.forward(Page.LOGIN.getPath());
        }

        return result;
    }

    private User extractUserFromRequest(HttpServletRequest req) {
        String userIdParam = req.getParameter("id");
        Long userId = userIdParam != null && !userIdParam.isEmpty() ? Long.valueOf(userIdParam) : null;
        String userName = req.getParameter("name");
        String userSurname = req.getParameter("surname");
        String userPhoneNumber = req.getParameter("phone_number");
        String userLogin = req.getParameter("login");
        String userPassword = req.getParameter("password");

        return new User(userId, userName, userSurname, userPhoneNumber, userLogin, userPassword, Role.READER, false);
    }
}
