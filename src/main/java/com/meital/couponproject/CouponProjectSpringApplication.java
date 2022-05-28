package com.meital.couponproject;

import com.meital.couponproject.entities.Company;
import com.meital.couponproject.entities.Coupon;
import com.meital.couponproject.entities.Customer;
import com.meital.couponproject.enums.CouponCategory;
import com.meital.couponproject.exceptions.ApplicationException;
import com.meital.couponproject.repo.CompanyRepo;
import com.meital.couponproject.service.AdminService;
import com.meital.couponproject.service.CompanyService;
import com.meital.couponproject.service.CustomerService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class CouponProjectSpringApplication {

    public static void main(String[] args) throws ApplicationException {
        ApplicationContext ctx = SpringApplication.run(CouponProjectSpringApplication.class, args);
        CustomerService customerService = ctx.getBean(CustomerService.class);
        AdminService adminService = ctx.getBean(AdminService.class);
        CompanyService companyService = ctx.getBean(CompanyService.class);
        CompanyRepo companyRepo = ctx.getBean(CompanyRepo.class);

        //create companies
        //adminService.createCompany(Company.builder().name("company1").email("company1@gmail.com").password("12345").build());
        //adminService.createCompany(Company.builder().name("company2").email("company2@gmail.com").password("11111").build());

        //create coupons
        Coupon coupon1 = Coupon.builder().company(companyRepo.getById(1L)).category(CouponCategory.FOOD)
                .title("coupon1").description("coupon1")
                .startDate(LocalDate.of(2022, 7, 10))
                .endDate(LocalDate.of(2022, 7, 30))
                .amount(12)
                .price(102.5)
                .image("www.c.com").build();

        Coupon coupon2 = Coupon.builder().company(companyRepo.getById(2L)).category(CouponCategory.ELECTRICITY)
                .title("coupon2").description("coupon2")
                .startDate(LocalDate.of(2022, 8, 1))
                .endDate(LocalDate.of(2022, 8, 12))
                .amount(20)
                .price(1200.0)
                .image("www.111.com").build();

        //companyService.createCoupon(coupon1);
        //companyService.createCoupon(coupon2);

        Customer customer1 = Customer.builder().firstName("customer1")
                .lastName("customer1")
                .email("customer1@gmail.com")
                .password("12345").build();

        Customer customer2 = Customer.builder().firstName("customer2")
                .lastName("customer2")
                .email("customer2@gmail.com")
                .password("22222").build();

        //adminService.createCustomer(customer1);
        //adminService.createCustomer(customer2);

        customerService.purchaseCoupon(1L,1L);


    }

}

