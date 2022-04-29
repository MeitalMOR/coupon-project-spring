package com.meital.couponproject.enums;

public enum UserType {
    CUSTOMER("Customer"),
    COMPANY("Company"),
    ADMIN("Admin");

    String userTypeName;

    UserType(String userTypeName) {
        this.userTypeName = userTypeName;
    }
}
