package com.meital.couponproject.threads;


import com.meital.couponproject.constants.Constants;
import com.meital.couponproject.entities.Coupon;
import com.meital.couponproject.exceptions.ApplicationException;
import com.meital.couponproject.repo.CouponRepo;
import com.meital.couponproject.service.CompanyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDate;
import java.util.List;

@Configuration
@EnableScheduling
@RequiredArgsConstructor
@Log4j2
public class CouponDailyJobTask {

    private final CouponRepo couponRepo;
    private final CompanyService companyService;

    LocalDate date = LocalDate.now();

    @Scheduled(fixedDelay = Constants.SINGLE_DAY_IN_MILLI_SECONDS)
    public void deleteExpiredCoupons() throws ApplicationException {

        log.info("\033[0;33m" + "Task Coupon Daily Job" + "started to run" + "\033[0m");
        List<Coupon> coupons = couponRepo.findAll();

        for (Coupon c : coupons) {
            //search in coupon table, coupons that expired
            if (c.getEndDate().isBefore(date)) {

                //delete coupon
                companyService.deleteCoupon(c.getId());
                log.info("\033[0;33m" + "Coupon " + c.getId() + "deleted" + "\033[0m");
            }
        }
    }
}