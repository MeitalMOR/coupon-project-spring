package com.meital.couponproject.exceptions;

import com.meital.couponproject.enums.ErrorType;
import lombok.Getter;

public class ApplicationException extends Exception {
    @Getter

    private final ErrorType errorType;

    // This is used when the exception is intentionally thrown
    public ApplicationException(ErrorType errorType, String message) {
        super(errorType + " | " + message);
        this.errorType = errorType;
    }
}
