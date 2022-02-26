package com.epam.library.command.implementation;

import javax.servlet.http.HttpServletRequest;


import com.epam.library.command.Command;
import com.epam.library.command.CommandResult;
import com.epam.library.entity.Role;
import com.epam.library.entity.User;
import com.epam.library.exception.PageCommandException;
import com.epam.library.exception.ServiceException;
import com.epam.library.service.AdminService;

import java.util.List;

public class ShowUsersCommand implements Command {

    private static final String READERS_PAGE = "/pages/readers.jsp";
    private static final String LIBRARIANS_PAGE = "/pages/librarians.jsp";

    private final AdminService adminService;
    private final Role showingUsersRole;

    public ShowUsersCommand(AdminService adminService, Role role) {
        this.adminService = adminService;
        this.showingUsersRole = role;
    }

    @Override
    public CommandResult execute(HttpServletRequest req) throws ServiceException, PageCommandException {
        List<User> users = adminService.getUsers(showingUsersRole);
        req.setAttribute("users", users.toArray());

        switch (showingUsersRole) {
            case LIBRARIAN:
                return CommandResult.forward(LIBRARIANS_PAGE);
            case READER:
                return CommandResult.forward(READERS_PAGE);
            default:
                throw new PageCommandException("Wrong role to show");
        }
    }
}