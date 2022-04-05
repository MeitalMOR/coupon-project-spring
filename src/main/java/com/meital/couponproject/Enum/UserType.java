package com.meital.couponproject.Enum;

public enum UserType {
    CUSTOMER("Customer"),
    COMPANY("Company");

    String userTypeName;

    UserType(String userTypeName) {
        this.userTypeName = userTypeName;
    }
}
