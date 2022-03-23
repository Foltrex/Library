package com.epam.library.command.implementation;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.epam.library.command.Command;
import com.epam.library.command.CommandResult;
import com.epam.library.command.Page;
import com.epam.library.exception.PageCommandException;
import com.epam.library.exception.ServiceException;
import com.epam.library.service.AdminService;
import com.epam.library.entity.Role;
import com.epam.library.entity.User;

public class ChangeUserBlockingCommand implements Command {

    private final AdminService adminService;

    public ChangeUserBlockingCommand(AdminService adminService) {
        this.adminService = adminService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req) throws ServiceException, PageCommandException {
        Long bannedUserId = Long.valueOf(req.getParameter("userId"));
        Role bannedUserRole = Role.valueOfRoleName(req.getParameter("userRole"));
        Boolean isBanned = Boolean.valueOf(req.getParameter("userBlocking"));

        adminService.changeUserBlocking(bannedUserId, isBanned);

        List<User> users = adminService.getUsers(bannedUserRole);
        req.setAttribute("users", users);

        switch (bannedUserRole) {
            case LIBRARIAN:
                return CommandResult.forward(Page.LIBRARIANS.getName());
            case READER:
                return CommandResult.forward(Page.READERS.getName());
            default:
                throw new PageCommandException("Wrong role to show");
        }
    }
    
}
