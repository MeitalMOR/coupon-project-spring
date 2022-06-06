package com.meital.couponproject.tests;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@RequiredArgsConstructor
@Order(1)
public class ApplicationTests implements CommandLineRunner {
    private final AdminServiceTests adminTests;
    private final CompanyServiceTests companyTests;
    private final CustomerServiceTests customerTests;

    @Override
    public void run(String... args) throws Exception {


        log.info("\033[0;34m" + "Start creates Testing..........." + "\033[0m");

        //--------------------------------Company Create Test
        adminTests.testCreateCompanies();

        //--------------------------------Customer Create Test
        adminTests.testCreateCustomers();

        //--------------------------------Coupon Create Test
        companyTests.testCreateCoupons();

        log.info("\033[0;34m" + "Start purchase Testing..........." + "\033[0m");

        //--------------------------------Purchase Coupon Test
        customerTests.testPurchaseCoupon();


        log.info("\033[0;32m" + "Start Getters Testing..........." + "\033[0m");

        //--------------------------------Companies Getters Tests
        adminTests.testGetCompanyDetails();
        adminTests.testGetAllCompanies();

//        //--------------------------------Customers Getters Tests
        adminTests.testGetCustomerDetails();
        customerTests.getCustomerCoupons();
        customerTests.getCustomerCouponsByCategory();
        customerTests.getCustomerCouponsByMaxPrice();
        adminTests.testGetAllCustomers();

        //--------------------------------Coupons Getters Tests
        companyTests.testGetCouponDetails();
        companyTests.testGetAllCompanyCouponsByCategory();
        companyTests.testGetAllCompanyCouponsByMaxPrice();
        companyTests.testGetAllCompanyCoupons();


        log.info("\033[0;32m" + "Start updates Testing..........." + "\033[0m");

        //--------------------------------Company Update Tests
        adminTests.updateCompanyTest();

        //--------------------------------Customer Update Tests
        adminTests.testUpdateCustomer();

        //--------------------------------Coupon Update Tests
        companyTests.testUpdateCoupon();


        log.info("\033[0;32m" + "Start deletes Testing..........." + "\033[0m");

        //--------------------------------Coupon Delete Tests
        companyTests.testDeleteCoupon();

        //--------------------------------Customer Delete Tests
        adminTests.testDeleteCustomer();

        //--------------------------------Company Delete Tests
        adminTests.deleteCompanyTest();

    }
}
