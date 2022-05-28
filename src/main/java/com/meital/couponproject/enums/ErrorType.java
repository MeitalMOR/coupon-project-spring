package com.meital.couponproject.enums;


import lombok.Getter;

public enum ErrorType {
    INVALID_USER(601, "Invalid user error"),
    ILLEGAL_USER_INPUT(602, "Illegal user input"),
    USER_ERROR(602, "User error"),
    WRONG_INPUT(602, "Wrong input"),
    CANNOT_PARSE_DATE(602, "Cannot parse date"),
    EMAIL_ALREADY_EXISTS(603, "Email already exists"),
    COMPANY_NAME_ALREADY_EXISTS(603, "Company name already exists"),
    COUPON_ALREADY_EXISTS(603, "Coupon already exists"),
    COUPON_OUT_OF_STOCK(605, "Coupon out of stock"),
    COUPON_DOES_NOT_EXIST(605, "Coupon doesn't exist"),
    COUPON_HAS_EXPIRED(605, "Coupon has expired"),
    START_DATE_BIGGER_THAN_END_DATE(605, "Start date bigger than end date"),
    DB_ERROR(606, "Database error"),
    COMPANY_DOES_NOT_EXISTS(607, "Company doesn't exists"),
    CUSTOMER_DOES_NOT_EXISTS(607, "Customer doesn't exists"),
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
