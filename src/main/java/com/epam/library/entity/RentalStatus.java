package com.epam.library.entity;

import java.io.Serializable;

public enum RentalStatus implements Serializable {
    WAITING_FOR_ISSUANCE("waiting for issuance"),
    ISSUED_TO_THE_READING_ROOM("issued to the reading room"),
    ISSUED_FOR_SUBSCRIPTION("issued for subscription"),
    RETURNED("returned");

    private final String status;

    private static final String SPACE = " ";
    private static final String UNDERSCORE = "_";

    RentalStatus(String status) {
        this.status = status;
    }

    public static RentalStatus valueOfStatus(String status) {
        status = status.toUpperCase().replaceAll(SPACE, UNDERSCORE);
        return RentalStatus.valueOf(status);
    }

    public String getStatusName() {
        return status;
    }
}
