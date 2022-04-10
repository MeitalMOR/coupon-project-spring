package com.meital.couponproject;

import com.meital.couponproject.entities.Company;
import com.meital.couponproject.entities.Coupon;
import com.meital.couponproject.entities.Customer;
import com.meital.couponproject.enums.CouponCategory;
import com.meital.couponproject.repositories.CompanyRepository;
import com.meital.couponproject.repositories.CouponRepository;
import com.meital.couponproject.repositories.CustomerRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.LocalDate;

@SpringBootApplication
public class CouponProjectSpringApplication {

    public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(CouponProjectSpringApplication.class, args);
		CompanyRepository companyRepository = ctx.getBean(CompanyRepository.class);
		Company c  = Company.builder().name("fox").email("fox@gmail.com").password("1234").build();
		companyRepository. save(c);
		System.out.println(companyRepository.existsByNameIgnoreCase("Fox"));

		CustomerRepository customerRepository = ctx.getBean(CustomerRepository.class);
		customerRepository.save(Customer.builder().firstName("dani").
				lastName("dani").
				email("dani@gmail.com").
				password("12345").build());
		System.out.println(customerRepository.existsById(1L));

		CouponRepository couponRepository = ctx.getBean(CouponRepository.class);
		Coupon coupon = Coupon.builder()
				.companyId(1L).category(CouponCategory.ELECTRICITY)
				.title("tv").description("tv1")
				.startDate(LocalDate.of(2022,4,21))
				.endDate(LocalDate.of(2022,4,21))
				.amount(2)
				.price(2500.0)
				.image("wwww.tv.com").build();
		couponRepository.save(coupon);
		System.out.println(couponRepository.existsById(1L));
		couponRepository.findByCompanyId(1L);
		couponRepository.findByCompanyIdAndCategory(1L,CouponCategory.ELECTRICITY);

    }
}

