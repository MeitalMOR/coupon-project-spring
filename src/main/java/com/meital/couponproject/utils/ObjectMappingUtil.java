package com.meital.couponproject.utils;

import com.meital.couponproject.dto.CustomerDto;
import com.meital.couponproject.dto.CompanyDto;
import com.meital.couponproject.dto.CouponDto;
import com.meital.couponproject.entities.Company;
import com.meital.couponproject.entities.Coupon;
import com.meital.couponproject.entities.Customer;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ObjectMappingUtil {

    public static Coupon couponDtoToEntity(final CouponDto dto) {
        return new Coupon(
                dto.getCompanyId(),
                dto.getCategory(),
                dto.getTitle(),
                dto.getDescription(),
                dto.getStartDate(),
                dto.getEndDate(),
                dto.getAmount(),
                dto.getPrice(),
                dto.getImage()
        );
    }

    public static Company companyDtoToEntity(final CompanyDto dto) {
        return new Company(
                dto.getName(),
                dto.getEmail(),
                dto.getPassword(),
                dto.getCoupons()
        );
    }

    public static Customer customerDtoToEntity(final CustomerDto dto) {
        return new Customer(
                dto.getFirstName(),
                dto.getLastName(),
                dto.getEmail(),
                dto.getPassword(),
                dto.getCoupons()
        );
    }
}
