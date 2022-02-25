package com.epam.library.entity;

public class User extends Entity {
    public static final String TABLE = "users";

    public static final String NAME = "name";
    public static final String SURNAME = "surname";
    public static final String PHONE_NUMBER = "phone_number";
    public static final String LOGIN = "login";
    public static final String PASSWORD = "password";
    public static final String ROLE = "role";

    private String name;
    private String surname;
    private String phoneNumber;
    private String login;
    private String password;
    private Role role;


    public User(Long id, String name, String surname, String phoneNumber, String login, String password, Role role) {
        super(id);
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.login = login;
        this.password = password;
        this.role = role;
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
