package com.epam.library.extractor.implementation;

import com.epam.library.entity.Role;
import com.epam.library.entity.User;
import com.epam.library.extractor.RequestExtractor;
import com.epam.library.extractor.parameter.name.UserRequestParameterName;

import javax.servlet.http.HttpServletRequest;

public class UserRentalRequestExtractor implements RequestExtractor<User> {
    @Override
    public User extract(HttpServletRequest request) {
        String userIdString = request.getParameter(UserRequestParameterName.ID.getName());
        Long userId = (userIdString != null) ? Long.valueOf(userIdString) : null;
        String userName = request.getParameter(UserRequestParameterName.NAME.getName());
        String userSurname = request.getParameter(UserRequestParameterName.SURNAME.getName());
        String userPhoneNumber = request.getParameter(UserRequestParameterName.PHONE_NUMBER.getName());
        String userLogin = request.getParameter(UserRequestParameterName.LOGIN.getName());
        String userPassword = request.getParameter(UserRequestParameterName.PASSWORD.getName());

        String userRoleString = request.getParameter(UserRequestParameterName.ROLE.getName());
        Role userRole = Role.valueOfRoleName(userRoleString);

        String userIsBannedString = request.getParameter(UserRequestParameterName.IS_BANNED.getName());
        boolean userIsBanned = Boolean.parseBoolean(userIsBannedString);

        return new User(userId, userName, userSurname, userPhoneNumber, userLogin, userPassword, userRole, userIsBanned);
    }
}
