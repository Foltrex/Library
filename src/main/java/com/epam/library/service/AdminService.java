package com.epam.library.service;

import com.epam.library.entity.Role;
import com.epam.library.entity.User;
import com.epam.library.exception.ServiceException;

import java.util.List;

public interface AdminService {
    List<User> getUsers(Role role) throws ServiceException;

    void changeUserBlocking(Long id, Boolean isBanned) throws ServiceException;
}
