package com.epam.library.command.impl;

import com.epam.library.command.Command;
import com.epam.library.command.CommandName;
import com.epam.library.command.CommandResult;
import com.epam.library.entity.*;
import com.epam.library.exception.PageCommandException;
import com.epam.library.exception.ServiceException;
import com.epam.library.extractor.RequestExtractor;
import com.epam.library.extractor.impl.UserRentalRequestExtractor;
import com.epam.library.extractor.parameter.name.UserRequestParameterName;
import com.epam.library.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

/** Register new user in DB */
public class SaveUserCommand implements Command {
    private static final String MAIN_PAGE_COMMAND = CommandName.SHOW_BOOKS.getServletCommand("controller");

    private final RequestExtractor<User> requestExtractor = new UserRentalRequestExtractor();

    private final UserService userService;

    public SaveUserCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req) throws ServiceException, PageCommandException {
        User user = requestExtractor.extract(req);

        userService.signUp(user);

        Optional<User> optionalUser = userService.login(user.getLogin(), user.getPassword());
        User registeredUser = optionalUser.orElseThrow(ServiceException::new);

        HttpSession httpSession = req.getSession();
        httpSession.setAttribute(UserRequestParameterName.ID.getName(), registeredUser.getId());
        httpSession.setAttribute(UserRequestParameterName.ROLE.getName(), registeredUser.getRole());

        return CommandResult.redirect(req.getContextPath() + MAIN_PAGE_COMMAND);
    }
}
