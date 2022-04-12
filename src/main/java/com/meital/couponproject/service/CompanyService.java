package com.meital.couponproject.service;

import com.meital.couponproject.entities.Coupon;
import com.meital.couponproject.exceptions.ApplicationException;
import com.meital.couponproject.repositories.CompanyRepository;
import com.meital.couponproject.repositories.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.meital.couponproject.enums.ErrorType.COUPON_ALREADY_EXISTS;
import static com.meital.couponproject.enums.ErrorType.DATA_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CouponRepository couponRepository;
    private final CompanyRepository companyRepository;

    public Coupon create(final Coupon coupon) throws ApplicationException {
        if (couponRepository.existsByTitleIgnoreCase(coupon.getTitle())) {
            throw new ApplicationException(COUPON_ALREADY_EXISTS);
        }

        if (!companyRepository.existsById(coupon.getCompanyId())) {
            throw new ApplicationException(COUPON_ALREADY_EXISTS);
        }

        couponRepository.save(coupon);
        return coupon;
    }

    public Coupon update(final Coupon coupon) throws ApplicationException {
        String titleToUpdate = "couponTitle";
        if (!companyRepository.existsById(coupon.getCompanyId())) {
            throw new ApplicationException(COUPON_ALREADY_EXISTS);
        }
        coupon.setTitle(titleToUpdate);
        couponRepository.save(coupon);
        return coupon;
    }

    public void delete(final long id) throws ApplicationException {
        Optional<Coupon> couponOpt = couponRepository.findById(id);
        if (couponOpt.isEmpty()) {
            throw new ApplicationException(DATA_NOT_FOUND);
        }
        couponRepository.deleteById(id);
    }

    public Optional<Coupon> getCoupon(final long id) throws ApplicationException {
        Optional<Coupon> couponOpt = couponRepository.findById(id);
        if (couponOpt.isEmpty()) {
            throw new ApplicationException(DATA_NOT_FOUND);
        }
        return couponOpt;
    }
}
