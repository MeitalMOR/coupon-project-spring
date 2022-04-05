package com.meital.couponproject.exceptions;

import com.meital.couponproject.enums.ErrorType;
import lombok.Getter;

public class ApplicationException extends Exception {
    @Getter

    private final ErrorType errorType;

    // This is used when the exception is intentionally thrown
    public ApplicationException(ErrorType errorType, String message) {
        super(message);
        this.errorType = errorType;
    }

    public ApplicationException(ErrorType errorType, String message, Exception e) {
        super(message, e);
        this.errorType = errorType;
    }
}
