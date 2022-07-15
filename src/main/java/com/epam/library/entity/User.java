package com.epam.library.entity;

import lombok.Builder;
import lombok.Value;

import java.io.Serializable;
import java.util.Objects;

@Value
@Builder
public class User implements Identifable, Serializable {
    public static final String TABLE = "users";

    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String SURNAME = "surname";
    public static final String PHONE_NUMBER = "phone_number";
    public static final String LOGIN = "login";
    public static final String PASSWORD = "password";
    public static final String ROLE = "role";
    public static final String IS_BANNED = "is_banned";

    Long id;
    String name;
    String surname;
    String phoneNumber;
    String login;
    String password;
    Role role;
    boolean isBanned;

    public static User createUserWithOnlyId(Long id) {
        return new User(id, null, null, null,
                    null, null, Role.READER, false);
    }


    public static User createUserWithIDAndLogin(Long id, String login) {
        return new User(id, null, null, null,
                login, null, Role.READER, false);
    }

}
