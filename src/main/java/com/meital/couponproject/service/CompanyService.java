package com.meital.couponproject.service;

import com.meital.couponproject.entities.Coupon;
import com.meital.couponproject.repositories.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CouponRepository couponRepository;

    public void createCoupon(final Coupon coupon) {couponRepository.save(coupon);}

    public Coupon getCoupon(final long id) {
        return couponRepository.findById(id).orElse(null);
    }

    public void deleteCoupon(final long id) {
        couponRepository.deleteById(id);
    }
}
