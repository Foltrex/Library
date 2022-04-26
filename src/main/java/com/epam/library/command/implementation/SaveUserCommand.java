package com.epam.library.command.implementation;

import com.epam.library.command.Command;
import com.epam.library.command.CommandName;
import com.epam.library.command.CommandResult;
import com.epam.library.entity.*;
import com.epam.library.exception.PageCommandException;
import com.epam.library.exception.ServiceException;
import com.epam.library.extractor.RequestExtractor;
import com.epam.library.extractor.implementation.UserRentalRequestExtractor;
import com.epam.library.extractor.parameter.name.UserRequestParameterName;
import com.epam.library.service.UserService;
import com.epam.library.validator.impl.InputDateValidatorImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class SaveUserCommand implements Command {
    private static final String MAIN_PAGE_COMMAND = CommandName.SHOW_BOOKS.getServletCommand("controller");

    private final RequestExtractor<User> requestExtractor = new UserRentalRequestExtractor();

    private final UserService userService;

    private final InputDateValidatorImpl validator;

    public SaveUserCommand(UserService userService, InputDateValidatorImpl validator) {
        this.userService = userService;
        this.validator = validator;
    }

    @Override
    public CommandResult execute(HttpServletRequest req) throws ServiceException, PageCommandException {
        User user = requestExtractor.extract(req);

        userService.signUp(user);

        Optional<User> optionalUser = userService.login(user.getLogin(), user.getPassword());
        User registeredUser = optionalUser.get();

        HttpSession httpSession = req.getSession();
        httpSession.setAttribute(UserRequestParameterName.ID.getName(), registeredUser.getId());
        httpSession.setAttribute(UserRequestParameterName.ROLE.getName(), registeredUser.getRole());

        CommandResult result = CommandResult.redirect(req.getContextPath() + MAIN_PAGE_COMMAND);

        return result;
    }
}
