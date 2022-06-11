package com.meital.couponproject.tests;


import com.meital.couponproject.entities.Coupon;
import com.meital.couponproject.enums.ErrorType;
import com.meital.couponproject.exceptions.ApplicationException;
import com.meital.couponproject.repo.*;
import com.meital.couponproject.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static com.meital.couponproject.enums.CouponCategory.*;
import static com.meital.couponproject.tests.config.CouponConfig.*;


@Log4j2
@Component
@RequiredArgsConstructor
@Order(2)
public class CompanyServiceTests {

    private final CompanyService companyService;
    private final CompanyRepo companyRepo;
    private final CouponRepo couponRepo;

    //--------------------------------create new coupon test
    public void testCreateCoupons() throws ApplicationException {

        companyService.createCoupon(
                Coupon.builder().company(companyRepo.getById(1L)).category(SPA_AND_BEAUTY)
                        .title("coupon1").description("coupon1")
                        .startDate(LocalDate.of(2022, 9, 2))
                        .endDate(LocalDate.of(2022, 9, 20))
                        .amount(22)
                        .price(396.0)
                        .image("www.spa.com").build());

        companyService.createCoupon(
                Coupon.builder().company(companyRepo.getById(3L)).category(FOOD)
                        .title("coupon2").description("coupon2")
                        .startDate(LocalDate.of(2022, 7, 10))
                        .endDate(LocalDate.of(2022, 7, 30))
                        .amount(12)
                        .price(102.5)
                        .image("www.c.com").build());

        companyService.createCoupon(
                Coupon.builder().company(companyRepo.getById(1L)).category(FOOD)
                        .title("coupon3").description("coupon3")
                        .startDate(LocalDate.of(2022, 10, 10))
                        .endDate(LocalDate.of(2022, 12, 30))
                        .amount(121)
                        .price(100.5)
                        .image("www.c.com").build());

        companyService.createCoupon(
                Coupon.builder().company(companyRepo.getById(2L)).category(HOME_DECOR)
                        .title("coupon4").description("coupon4")
                        .startDate(LocalDate.of(2022, 11, 10))
                        .endDate(LocalDate.of(2022, 12, 31))
                        .amount(11)
                        .price(150.8)
                        .image("www.c.com").build());

        companyService.createCoupon(
                Coupon.builder().company(companyRepo.getById(2L)).category(ELECTRICITY)
                        .title("coupon5").description("coupon5")
                        .startDate(LocalDate.of(2022, 6, 10))
                        .endDate(LocalDate.of(2022, 11, 20))
                        .amount(30)
                        .price(170.8)
                        .image("www.c.com").build());


        if (couponRepo.existsById(1L) && couponRepo.existsById(2L) && couponRepo.existsById(3L) &&
                couponRepo.existsById(4L) && couponRepo.existsById(5L)) {
            log.info("\033[0;32m" + "Test - create new coupons - succeeded" + "\033[0m");
        }
    }

    //--------------------------------update coupon test
    public void testUpdateCoupon() throws ApplicationException {

        Optional<Coupon> coupon = companyService.getCoupon(2L);

        coupon.get().setCategory(couponCategoryToUpdate);
        coupon.get().setStartDate(startDateToUpdate);
        coupon.get().setEndDate(endDateToUpdate);
        coupon.get().setAmount(amountToUpdate);
        coupon.get().setPrice(priceToUpdate);

        companyService.updateCoupon(coupon.get());

        List<Coupon> coupons = couponRepo.findByCompanyIdAndCategory(3L, VACATION);

        if (!coupons.isEmpty()) {
            log.info("\033[0;32m" + "Test - update coupon - succeeded" + "\033[0m");
        }
    }

    //--------------------------------delete coupon test
    @Transactional
    public void testDeleteCoupon() throws ApplicationException {

        companyService.deleteCoupon(5L);

        if (!couponRepo.existsById(5L)) {
            log.info("\033[0;32m" + "Test - delete coupon - succeeded" + "\033[0m");
        }
    }

    //--------------------------------Test get coupon details
    @Transactional
    public void testGetCouponDetails() throws ApplicationException {

        Optional<Coupon> coupon = companyService.getCoupon(4L);

        if (coupon.isPresent()) {
            log.info("\033[0;32m" + "Test - get coupon details - succeeded" + "\033[0m");
        } else {
            throw new ApplicationException(ErrorType.COUPON_OUT_OF_STOCK);
        }
    }

    //--------------------------------Test get company coupon list
    @Transactional
    public void testGetAllCompanyCoupons() throws ApplicationException {

        List<Coupon> companyCoupons = companyService.getCompanyCoupons(1L);

        if (companyCoupons.size() == 2) {
            log.info("\033[0;32m" + "Test - get coupon list by company - succeeded" + "\033[0m");
        }
    }

    //--------------------------------Test get company coupon list for category
    @Transactional
    public void testGetAllCompanyCouponsByCategory() throws ApplicationException {

        List<Coupon> companyCouponsByCategory = companyService.getCompanyCouponsByCategory(1L, SPA_AND_BEAUTY);

        if (companyCouponsByCategory.size() == 1) {
            log.info("\033[0;32m" + "Test - get coupon list by company and category- succeeded" + "\033[0m");
        }
    }

    //--------------------------------Test get company coupon list by max price
    @Transactional
    public void testGetAllCompanyCouponsByMaxPrice() throws ApplicationException {

        List<Coupon> companyCouponsByMaxPrice = companyService.getCompanyCouponsByMaxPrice(1L, 400.0);

        if (companyCouponsByMaxPrice.size() == 2) {
            log.info("\033[0;32m" + "Test - get coupon list by company id  and max price - succeeded" + "\033[0m");
        }
    }
}
