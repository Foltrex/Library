package com.epam.library.entity;

public enum RentalStatus {
    WAITING_FOR_ISSUANCE("waiting for issuance"),
    ISSUED_TO_THE_READING_ROOM("issued to the reading room"),
    ISSUED_FOR_SUBSCRIPTION("issued for subscription"),
    RETURNED("returned");

    private final String statusName;

    private static final String SPACE = " ";
    private static final String UNDERSCORE = "_";
    private static final String DOT = ".";

    RentalStatus(String statusName) {
        this.statusName = statusName;
    }

    public static RentalStatus valueOfStatus(String status) {
        status = status.toUpperCase().replaceAll(SPACE, UNDERSCORE);
        return RentalStatus.valueOf(status);
    }

    public String getStatusName() {
        return statusName;
    }

    public String getLocalizedStatusName() {
        String roleFormattedString = statusName.toLowerCase().replaceAll(SPACE, DOT);
        return String.format("rental.status.%s", roleFormattedString);
    }
}
