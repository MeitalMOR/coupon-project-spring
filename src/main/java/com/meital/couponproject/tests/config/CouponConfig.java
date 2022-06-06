package com.meital.couponproject.tests.config;

import com.meital.couponproject.enums.CouponCategory;

import java.time.LocalDate;

import static com.meital.couponproject.enums.CouponCategory.VACATION;

public class CouponConfig {

    public static CouponCategory couponCategoryToUpdate = VACATION;

    public static LocalDate startDateToUpdate = LocalDate.of(2022, 10, 26);

    public static LocalDate endDateToUpdate = LocalDate.of(2023, 11, 27);

    public static Integer amountToUpdate = 60;

    public static Double priceToUpdate = 250.0;
}

