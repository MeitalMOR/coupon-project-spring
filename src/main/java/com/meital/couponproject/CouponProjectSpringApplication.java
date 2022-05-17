package com.meital.couponproject;

import com.meital.couponproject.entities.Company;
import com.meital.couponproject.entities.Coupon;
import com.meital.couponproject.entities.Customer;
import com.meital.couponproject.enums.CouponCategory;
import com.meital.couponproject.exceptions.ApplicationException;
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
//
//        Company company = Company.builder()
//                .name("fox")
//                .email("fox@gmail.com")
//                .password("1234")
//                .build();
//
//        Customer customer = Customer.builder().firstName("dani").
//                lastName("dani").
//                email("dani@gmail.com").
//                password("12345")
//                .build();
//
//        Coupon coupon = Coupon.builder()
//                .companyId(1L).category(CouponCategory.ELECTRICITY)
//                .title("tv").description("tv1")
//                .startDate(LocalDate.of(2022, 4, 21))
//                .endDate(LocalDate.of(2022, 5, 21))
//                .amount(2)
//                .price(2500.0)
//                .image("wwww.tv.com")
//                .build();
//
//        Coupon coupon1 = Coupon.builder()
//                .companyId(1L)
//                .category(CouponCategory.FOOD)
//                .title("japanika")
//                .description("sushi plate")
//                .startDate(LocalDate.of(2022, 4, 29))
//                .endDate(LocalDate.of(2022, 4, 30))
//                .amount(25).price(250.0)
//                .image("www.japanika")
//                .build();
//
//        Coupon coupon2 = Coupon.builder()
//                .companyId(1L)
//                .category(CouponCategory.HOME_DECOR)
//                .title("table").description("black table")
//                .startDate(LocalDate.of(2022, 4, 29))
//                .endDate(LocalDate.of(2022, 4, 30))
//                .amount(5).price(150.0)
//                .image("www.home")
//                .build();
//
//        Coupon coupon3 = Coupon.builder()
//                .companyId(1L)
//                .category(CouponCategory.FOOD)
//                .title("aroma")
//                .description("aroma toast")
//                .startDate(LocalDate.of(2022, 4, 29))
//                .endDate(LocalDate.of(2022, 4, 30))
//                .amount(12)
//                .price(25.0)
//                .image("www.aroma")
//                .build();
////        //create company test
//        adminService.createCompany(company);
////        System.out.println(companyRepository.existsByNameIgnoreCase("Fox"));
//
////        //create customer test
//        adminService.addNewCustomer(customer);
////        System.out.println(customerRepository.existsById(1L));
//
//
////        //create coupon test
//        companyService.createCoupon(coupon);
//        companyService.createCoupon(coupon1);
//        companyService.createCoupon(coupon2);
//        companyService.createCoupon(coupon3);
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


        //System.out.println(couponRepository.findByCompanyIdAndPriceLessThan(1L,3000.0));
        //adminService.updateCustomer(Customer.builder().id(1L).firstName("roniii").lastName("roniii").email("ron@gmail.com").password("14789").build());
        //System.out.println(adminService.getCustomer(1L));


//        Optional<Coupon> coupon = companyService.getCoupon(1L);
//        Optional<Coupon> coupon3 = companyService.getCoupon(3L);


//        customerService.purchaseCoupon(1L, coupon.get());
//        customerService.purchaseCoupon(1L, coupon3.get());


        List<Coupon> coupons = customerService.getCustomerCoupons(1L);

        System.out.println(coupons);
        //List<Coupon> coupons = couponRepository.findByCustomers_Id(1L);
        //System.out.println(coupons);
        //adminService.createCompany(company);
        //adminService.addNewCustomer(customer);
        //customerService.purchaseCoupon(1L,coupon2);
        //customerService.purchaseCoupon(1L,coupon3);
        //System.out.println(couponRepository.allCouponsPurchases(1L));

//        System.out.println(customerService.getCustomerCoupons(1L));


        //companyService.createCoupon(coupon1);


    }
}

