package com.epam.library.exception;

public class PageCommandException extends Exception {
    public PageCommandException() {
        super();
    }

    public PageCommandException(String message) {
        super(message);
    }

    public PageCommandException(String message, Throwable cause) {
        super(message, cause);
    }

    public PageCommandException(Throwable cause) {
        super(cause);
    }

    protected PageCommandException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
