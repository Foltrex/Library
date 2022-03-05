package com.epam.library.entity;

public class User extends Entity {
    public static final String TABLE = "users";

    public static final String NAME = "name";
    public static final String SURNAME = "surname";
    public static final String PHONE_NUMBER = "phone_number";
    public static final String LOGIN = "login";
    public static final String PASSWORD = "password";
    public static final String ROLE = "role";
    public static final String IS_BANNED = "is_banned";

    private String name;
    private String surname;
    private String phoneNumber;
    private String login;
    private String password;
    private Role role;
    private boolean isBanned;


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

    public boolean isBanned() {
        return isBanned;
    }

    public void setBanned(boolean banned) {
        isBanned = banned;
    }

    public static User createUserWithIDAndLogin(Long id, String login) {
        return new User(id, null, null, null,
                        login, null, Role.READER, false);
    }


    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }
}
