package com.epam.library.logic.security;

import com.epam.library.models.Role;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.security.Principal;

// TODO: make comments
public class UserRoleRequestWrapper extends HttpServletRequestWrapper {

    private HttpServletRequest currentRequest;
    private String login;
    private String role;

    public UserRoleRequestWrapper(HttpServletRequest request, String login, String role) {
        super(request);
        this.currentRequest = request;
        this.login = login;
        this.role = role;
    }

    @Override
    public boolean isUserInRole(String role) {
        return currentRequest.isUserInRole(role);
    }

    @Override
    public Principal getUserPrincipal() {
        if (login == null) {
            return currentRequest.getUserPrincipal();
        }

        return new Principal() {
            @Override
            public String getName() {
                return login;
            }
        };
    }
}
