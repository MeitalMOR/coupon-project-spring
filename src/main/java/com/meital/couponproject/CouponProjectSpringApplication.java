package com.meital.couponproject;

import com.meital.couponproject.entities.Company;
import com.meital.couponproject.entities.Coupon;
import com.meital.couponproject.entities.Customer;
import com.meital.couponproject.enums.CouponCategory;
import com.meital.couponproject.exceptions.ApplicationException;
import com.meital.couponproject.repositories.CompanyRepository;
import com.meital.couponproject.repositories.CouponRepository;
import com.meital.couponproject.repositories.CustomerRepository;
import com.meital.couponproject.service.AdminService;
import com.meital.couponproject.service.CompanyService;
import com.meital.couponproject.service.CustomerService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class CouponProjectSpringApplication {

    public static void main(String[] args) throws ApplicationException {
        ApplicationContext ctx = SpringApplication.run(CouponProjectSpringApplication.class, args);

        Company company = Company.builder()
                .name("fox")
                .email("fox@gmail.com")
                .password("1234")
                .build();

        Customer customer = Customer.builder().firstName("dani").
                lastName("dani").
                email("dani@gmail.com").
                password("12345")
                .build();

        Coupon coupon = Coupon.builder()
                .companyId(1L).category(CouponCategory.ELECTRICITY)
                .title("tv").description("tv1")
                .startDate(LocalDate.of(2022, 4, 21))
                .endDate(LocalDate.of(2022, 4, 21))
                .amount(2)
                .price(2500.0)
                .image("wwww.tv.com")
                .build();

        CompanyRepository companyRepository = ctx.getBean(CompanyRepository.class);
        AdminService adminService = ctx.getBean(AdminService.class);
//        //create company test
//        adminService.createCompany(company);
//        System.out.println(companyRepository.existsByNameIgnoreCase("Fox"));


        CustomerRepository customerRepository = ctx.getBean(CustomerRepository.class);
//        //create customer test
//        adminService.createCustomer(customer);
//        System.out.println(customerRepository.existsById(1L));

        CouponRepository couponRepository = ctx.getBean(CouponRepository.class);
        CompanyService companyService = ctx.getBean(CompanyService.class);
//        //create coupon test
//        companyService.create(coupon);
//        System.out.println(couponRepository.existsById(1L));

        //test the methods

        //company service methods

//        coupon.setId(1L);
//        coupon.setCategory(CouponCategory.ELECTRICITY);
//        companyService.update(coupon);

//         companyService.delete(1L);

//        companyService.getCompanyCoupons(1L);


//        companyService.getCompanyCouponsByCategory(1L,CouponCategory.ELECTRICITY);
//
//      companyService.getCompanyCouponsByMaxPrice(1L,4000);
//
//        System.out.println(companyService.getCompanyDetails(1L));


//        CustomerService customerService = ctx.getBean(CustomerService.class);
    }
}

