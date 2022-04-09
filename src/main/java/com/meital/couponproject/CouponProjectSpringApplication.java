package com.meital.couponproject;
import com.meital.couponproject.entities.Company;
import com.meital.couponproject.repositories.CompanyRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class CouponProjectSpringApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(CouponProjectSpringApplication.class, args);
		CompanyRepository companyRepository = ctx.getBean(CompanyRepository.class);
		//Company c  = Company.builder().name("fox").email("fox@gmail.com").password("1234").build();
		//companyRepository. save(c);
		System.out.println(companyRepository.existsByNameIgnoreCase("Fox"));
	}
}
