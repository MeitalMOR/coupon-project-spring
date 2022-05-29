package com.meital.couponproject.tests;


import com.meital.couponproject.entities.Company;
import com.meital.couponproject.entities.Coupon;
import com.meital.couponproject.enums.CouponCategory;
import com.meital.couponproject.exceptions.ApplicationException;
import com.meital.couponproject.repo.CompanyRepo;
import com.meital.couponproject.repo.CouponRepo;
import com.meital.couponproject.service.CompanyService;
import com.meital.couponproject.tests.config.CompanyConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Log4j2
@Component
@RequiredArgsConstructor
@Order(2)
public class CompanyServiceTests implements CommandLineRunner {

    private final CompanyService companyService;
    private final CompanyRepo companyRepo;
    private final CouponRepo couponRepo;


    @Override
    public void run(String... args) throws Exception {

        log.info("\033[0;32m" + "Start Testing Company service..........." + "\033[0m");
        testCreateCoupon();
        testUpdateCoupon();
        testDeleteCoupon();
        testGetCouponDetails();
        testGetAllCompanyCoupons();
        testGetAllCompanyCouponsByCategory();

        testGetAllCompanyCouponsByMaxPrice();


    }

    //--------------------------------create new coupon test
    public void testCreateCoupon() throws ApplicationException {

        Company company1 = companyRepo.save(Company.builder()
                .name(CompanyConfig.company3Name)
                .email(CompanyConfig.company3Email)
                .password(CompanyConfig.company3Password).build());

        companyService.createCoupon(
                Coupon.builder().company(company1).category(CouponCategory.SPA_AND_BEAUTY)
                        .title("coupon3").description("coupon3")
                        .startDate(LocalDate.of(2022, 9, 2))
                        .endDate(LocalDate.of(2022, 9, 20))
                        .amount(22)
                        .price(396.0)
                        .image("www.spa.com").build());

        if (couponRepo.existsById(3L)) {
            log.info("\033[0;32m" + "Test 1 - create new coupon - succeeded" + "\033[0m");
        }
    }

    //--------------------------------update coupon test
    public void testUpdateCoupon() throws ApplicationException {
        Coupon couponToUpdate = Coupon.builder().id(3L).company(companyRepo.getById(4L)).category(CouponCategory.SPA_AND_BEAUTY)
                .title("coupon3").description("coupon3updated")
                .startDate(LocalDate.of(2022, 9, 2))
                .endDate(LocalDate.of(2022, 9, 20))
                .amount(27)
                .price(396.0)
                .image("www.spa.com").build();

        companyService.updateCoupon(couponToUpdate);

        Optional<Coupon> c = couponRepo.findById(3L);
        if (c.get().getAmount() == 27) {
            log.info("\033[0;32m" + "Test 2 - update coupon - succeeded" + "\033[0m");
        }
    }

    //--------------------------------delete coupon test
    @Transactional
    private void testDeleteCoupon() throws ApplicationException {

        companyService.deleteCoupon(3L);

        if (!couponRepo.existsById(3L)) {
            log.info("\033[0;32m" + "Test 3 - delete coupon - succeeded" + "\033[0m");
        }
    }

    //--------------------------------Test get coupon details
    @Transactional
    private void testGetCouponDetails() throws ApplicationException {

        Coupon coupon = couponRepo.save(
                Coupon.builder().company(companyRepo.getById(2L)).category(CouponCategory.THINGS_TO_DO_CHILDREN)
                        .title("coupon5").description("coupon5")
                        .startDate(LocalDate.of(2022, 7, 10))
                        .endDate(LocalDate.of(2022, 7, 30))
                        .amount(65)
                        .price(33.0)
                        .image("www.children.com").build());

        if (couponRepo.existsById(4L)) {
            log.info("\033[0;32m" + "Test 4 - get coupon details - succeeded" + "\033[0m");
        }
    }

    //--------------------------------Test get company coupon list
    @Transactional
    private void testGetAllCompanyCoupons() throws ApplicationException {

        List<Coupon> companyCoupons = companyService.getCompanyCoupons(2L);

        if (companyCoupons.size() == 2) {
            log.info("\033[0;32m" + "Test 5 - get coupon list by company - succeeded" + "\033[0m");
        }
    }

    //--------------------------------Test get company coupon list for category
    @Transactional
    private void testGetAllCompanyCouponsByCategory() throws ApplicationException {

        List<Coupon> companyCouponsByCategory = companyService.getCompanyCouponsByCategory(2L, CouponCategory.THINGS_TO_DO_CHILDREN);

        if (companyCouponsByCategory.size() == 1) {
            log.info("\033[0;32m" + "Test 6 - get coupon list by company and category- succeeded" + "\033[0m");
        }
    }

    //--------------------------------Test get company coupon list by max price
    @Transactional
    private void testGetAllCompanyCouponsByMaxPrice() throws ApplicationException {

        List<Coupon> companyCouponsByMaxPrice = companyService.getCompanyCouponsByMaxPrice(2L, 400.0);

        if (companyCouponsByMaxPrice.size() == 2) {
            log.info("\033[0;32m" + "Test 7 - get coupon list by company id  and max price - succeeded" + "\033[0m");
        }
    }

}
