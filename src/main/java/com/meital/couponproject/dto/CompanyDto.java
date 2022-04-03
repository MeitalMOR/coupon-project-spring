package com.meital.couponproject.dto;

import com.meital.couponproject.entities.Coupon;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDto {

    private long id;

    private String name;

    private String email;

    private String password;

    private ArrayList<Coupon> coupons;

    public CompanyDto(String name, String email, String password, ArrayList<Coupon> coupons) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.coupons = coupons;
    }
}
