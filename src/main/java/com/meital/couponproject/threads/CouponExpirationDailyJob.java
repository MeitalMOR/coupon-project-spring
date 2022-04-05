package com.meital.couponproject.threads;

import com.meital.couponproject.enums.ErrorType;
import com.meital.couponproject.entities.Coupon;
import com.meital.couponproject.exceptions.ApplicationException;
import lombok.SneakyThrows;

import java.time.LocalDate;
import java.util.ArrayList;

import static com.meital.couponproject.constants.Constants.SINGLE_DAY_IN_MILLI_SECONDS;

public class CouponExpirationDailyJob implements Runnable {

    private final CompanyFacade companyFacade = CompanyFacade.instance;

    // 86,400 is the amount of seconds in a single day, multiplied by 1000 because the system works in milliseconds
    long day = SINGLE_DAY_IN_MILLI_SECONDS;

    //The task is daily job that removes expired coupons from the database.
    @SneakyThrows
    @Override
    public void run() {
        try {
            while (true) {
                LocalDate today = new LocalDate();
                ArrayList<Coupon> coupons = companyFacade.readAll();
                for (Coupon coupon : coupons) {
                    if (today.g >= coupon.getEndDate().getTime()) {
                        companyFacade.delete(coupon.getId());
                        System.out.println("Coupon number " + coupon.getId() + " has expired.");
                    }
                }
                Thread.sleep(day);
            }
        } catch (Exception e) {
            throw new ApplicationException(ErrorType.DB_ERROR, "Failed to remove expired coupons");
        }
    }
}
