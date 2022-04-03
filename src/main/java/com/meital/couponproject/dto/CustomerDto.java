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
public class CustomerDto {
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private ArrayList<Coupon> coupons;
}
