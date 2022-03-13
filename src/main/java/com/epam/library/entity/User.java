package com.epam.library.entity;

import java.util.Objects;

public class User extends Entity {
    public static final String TABLE = "users";

    public static final String NAME = "name";
    public static final String SURNAME = "surname";
    public static final String PHONE_NUMBER = "phone_number";
    public static final String LOGIN = "login";
    public static final String PASSWORD = "password";
    public static final String ROLE = "role";
    public static final String IS_BANNED = "is_banned";

    private final String name;
    private final String surname;
    private final String phoneNumber;
    private final String login;
    private final String password;
    private final Role role;
    private final boolean isBanned;


    public User(Long id, String name, String surname, String phoneNumber,
                String login, String password, Role role, boolean isBanned) {
        super(id);
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.login = login;
        this.password = password;
        this.role = role;
        this.isBanned = isBanned;
    }

    public static User createUserWithOnlyId(Long id) {
        return new User(id, null, null, null,
                    null, null, Role.READER, false);
    }


    public static User createUserWithIDAndLogin(Long id, String login) {
        return new User(id, null, null, null,
                login, null, Role.READER, false);
    }


    public boolean isBanned() {
        return isBanned;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof User)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        User user = (User) o;
        return isBanned == user.isBanned &&
                Objects.equals(name, user.name) &&
                Objects.equals(surname, user.surname) &&
                Objects.equals(phoneNumber, user.phoneNumber) &&
                Objects.equals(login, user.login) &&
                Objects.equals(password, user.password) &&
                role == user.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, surname, phoneNumber, login, password, role, isBanned);
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", isBanned=" + isBanned +
                '}';
    }
}
