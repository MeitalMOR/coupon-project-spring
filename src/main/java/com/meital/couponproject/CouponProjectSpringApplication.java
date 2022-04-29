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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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

        Coupon coupon = Coupon.builder().id(1L)
                .companyId(1L).category(CouponCategory.ELECTRICITY)
                .title("tv").description("tv1")
                .startDate(LocalDate.of(2022, 4, 21))
                .endDate(LocalDate.of(2022, 4, 21))
                .amount(2)
                .price(2500.0)
                .image("wwww.tv.com")
                .build();

        CompanyRepository companyRepository = ctx.getBean(CompanyRepository.class);
        CustomerService customerService = ctx.getBean(CustomerService.class);
        AdminService adminService = ctx.getBean(AdminService.class);
//        //create company test


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
        //couponRepository.save(coupon);
        //System.out.println(couponRepository.findByCompanyIdAndPriceLessThan(1L,3000.0));
        //adminService.updateCustomer(Customer.builder().id(1L).firstName("roniii").lastName("roniii").email("ron@gmail.com").password("14789").build());
        //System.out.println(adminService.getCustomer(1L));
        Coupon coupon1 = Coupon.builder().id(1L).companyId(1L).category(CouponCategory.FOOD).title("japanika").description("sushi plate")
                .startDate(LocalDate.of(2022,4,29)).endDate(LocalDate.of(2022,4,30)).amount(25).price(250.0).image("www.japanika").build();

        Coupon coupon2 = Coupon.builder().id(2L).companyId(1L).category(CouponCategory.HOME_DECOR).title("table").description("black table")
                .startDate(LocalDate.of(2022,4,29)).endDate(LocalDate.of(2022,4,30)).amount(5).price(150.0).image("www.home").build();

        Coupon coupon3 = Coupon.builder().id(3L).companyId(1L).category(CouponCategory.FOOD).title("aroma").description("aroma toast")
                .startDate(LocalDate.of(2022,4,29)).endDate(LocalDate.of(2022,4,30)).amount(12).price(25.0).image("www.aroma").build();

        //couponRepository.save(coupon1);
        //couponRepository.save(coupon2);
        //couponRepository.save(coupon3);
        //customerService.purchaseCoupon(1L,coupon2);
        //customerService.purchaseCoupon(1L,coupon3);


        //List<Coupon> coupons = couponRepository.findByCustomers_Id(1L);
        //System.out.println(coupons);
        //adminService.createCompany(company);
        //adminService.addNewCustomer(customer);
        //customerService.purchaseCoupon(1L,coupon2);
        //customerService.purchaseCoupon(1L,coupon3);
        //System.out.println(couponRepository.allCouponsPurchases(1L));

        System.out.println(customerService.getCustomerCoupons(1L));







        //companyService.createCoupon(coupon1);




    }
}

