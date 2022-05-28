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

    }

}

