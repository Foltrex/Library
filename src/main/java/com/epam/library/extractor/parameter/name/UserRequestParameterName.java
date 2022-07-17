package com.epam.library.extractor.parameter.name;

/** Request parameters associated with the {@link com.epam.library.entity.User} */
public enum UserRequestParameterName {
    ID("userId"),
    NAME("userName"),
    SURNAME("userSurname"),
    PHONE_NUMBER("userPhoneNumber"),
    LOGIN("userLogin"),
    PASSWORD("userPassword"),
    ROLE("userRole"),
    IS_BANNED("userIsBanned");

    private final String name;

    UserRequestParameterName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
