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

import java.time.LocalDate;
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


}