package com.meital.couponproject.Enum;


import lombok.Getter;

public enum ErrorType {
    INVALID_USER(601, "Invalid user error"),
    ILLEGAL_USER_INPUT(602, "Illegal user input"),
    USER_ERROR(602, "User error"),
    WRONG_INPUT(602, "Wrong input"),
    EMAIL_ALREADY_EXISTS(603, "Email already exists"),
    COMPANY_NAME_ALREADY_EXISTS(603, "Company name already exists"),
    OUT_OF_STOCK_OR_EXPIRED(605, "Out of stock or expired"),
    CANNOT_PARSE_DATE(602, "Cannot parse date"),
    START_DATE_BIGGER_THAN_END_DATE(605, "Start date bigger than end date"),
    DB_ERROR(606, "Database error"),
    DATA_NOT_FOUND(607, "Data not found"),
    HACKING_ATTEMPT(800, "Hacking attempt detected");

    @Getter
    private final int internalErrorCode;
    @Getter
    private final String internalMessage;


    //private Constructor
    ErrorType(int internalErrorCode, String internalMessage) {
        this.internalErrorCode = internalErrorCode;
        this.internalMessage = internalMessage;
    }
}
