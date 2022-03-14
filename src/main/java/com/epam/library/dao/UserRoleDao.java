package com.epam.library.dao;

import com.epam.library.entity.Role;
import com.epam.library.entity.User;
import com.epam.library.exception.DaoException;

import java.util.List;

public interface UserRoleDao extends EntityDao {
    List<User> findUsersByRole(Role role) throws DaoException;
}
