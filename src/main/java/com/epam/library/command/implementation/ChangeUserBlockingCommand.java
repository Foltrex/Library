package com.epam.library.command.implementation;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.epam.library.command.Command;
import com.epam.library.command.CommandResult;
import com.epam.library.command.Page;
import com.epam.library.exception.PageCommandException;
import com.epam.library.exception.ServiceException;
import com.epam.library.extractor.parameter.name.UserRequestParameterName;
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
        Long bannedUserId = Long.valueOf(req.getParameter(UserRequestParameterName.ID.getName()));
        Role bannedUserRole = Role.valueOfRoleName(req.getParameter(UserRequestParameterName.ROLE.getName()));
        Boolean isBanned = Boolean.valueOf(req.getParameter(UserRequestParameterName.IS_BANNED.getName()));

        adminService.changeUserBlocking(bannedUserId, isBanned);

        List<User> users = adminService.getUsers(bannedUserRole);
        req.setAttribute("users", users);

        switch (bannedUserRole) {
            case LIBRARIAN:
                return CommandResult.forward(Page.LIBRARIANS.getPath());
            case READER:
                return CommandResult.forward(Page.READERS.getPath());
            default:
                throw new PageCommandException("Wrong role to show");
        }
    }
    
}
