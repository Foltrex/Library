package com.epam.library.validator;

public class UserValidator {

    public boolean isPasswordCoincidental(String firstEnteredPassword, String secondEnteredPassword) {
        return firstEnteredPassword.equals(secondEnteredPassword);
    }
}
