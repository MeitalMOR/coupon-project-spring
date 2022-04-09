package com.meital.couponproject.service;

import com.meital.couponproject.entities.Coupon;
import com.meital.couponproject.repositories.CompanyRepository;
import com.meital.couponproject.repositories.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CouponRepository couponRepository;
    private final CompanyRepository companyRepository;

    public Coupon createCoupon(final Coupon coupon) {
        if (couponRepository.existsByTitleIgnoreCase(coupon.getTitle())) {
            throw new RuntimeException("aaa");
        }

        if (!companyRepository.existsById(coupon.getCompanyId())) {
            throw new RuntimeException("aaa");
        }

        couponRepository.save(coupon);
        return coupon;
    }

    public Coupon getCoupon(final long id) {
        return couponRepository.findById(id).orElse(null);
    }

    public void deleteCoupon(final long id) {
        couponRepository.deleteById(id);
    }
}
