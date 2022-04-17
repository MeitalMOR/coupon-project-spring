package com.meital.couponproject.service;

import com.meital.couponproject.entities.Company;
import com.meital.couponproject.entities.Coupon;
import com.meital.couponproject.enums.CouponCategory;
import com.meital.couponproject.exceptions.ApplicationException;
import com.meital.couponproject.repositories.CompanyRepository;
import com.meital.couponproject.repositories.CouponRepository;
import com.meital.couponproject.repositories.PurchaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.meital.couponproject.enums.ErrorType.COUPON_ALREADY_EXISTS;
import static com.meital.couponproject.enums.ErrorType.DATA_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CouponRepository couponRepository;
    private final CompanyRepository companyRepository;
    private final PurchaseRepository purchaseRepository;

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

        if (!couponRepository.existsById(coupon.getId())) {
            throw new ApplicationException(DATA_NOT_FOUND);
        }

        if (couponRepository.existsByTitleIgnoreCase(coupon.getTitle())) {
            throw new ApplicationException(COUPON_ALREADY_EXISTS);
        }

        couponRepository.saveAndFlush(coupon);
        return coupon;
    }

    public void delete(final long couponId) throws ApplicationException {
        Optional<Coupon> couponOpt = couponRepository.findById(couponId);

        if (couponOpt.isEmpty()) {
            throw new ApplicationException(DATA_NOT_FOUND);
        }

        purchaseRepository.deletePurchaseByCouponId(couponId);
        couponRepository.deleteById(couponId);

        System.out.println("Coupon" + couponId + " deleted successfully");
    }

    public List<Coupon> getCompanyCoupons(final long companyId) throws ApplicationException {

        if (!companyRepository.existsById(companyId)) {
            throw new ApplicationException(DATA_NOT_FOUND);
        }

        List<Coupon> coupons = couponRepository.findByCompanyId(companyId);

        if (coupons.isEmpty()) {
            throw new ApplicationException(DATA_NOT_FOUND);
        }

        return coupons;
    }

    public List<Coupon> getCompanyCouponsByCategory(final long companyId, final CouponCategory category) throws ApplicationException {

        if (!companyRepository.existsById(companyId)) {
            throw new ApplicationException(DATA_NOT_FOUND);
        }

        List<Coupon> categoryCoupons = couponRepository.findByCompanyIdAndCategory(companyId, category);

        if (categoryCoupons.isEmpty()) {
            throw new ApplicationException(DATA_NOT_FOUND);
        }

        return categoryCoupons;
    }

    public List<Coupon> getCompanyCouponsByMaxPrice(final long companyId, final Double maxPrice) throws ApplicationException {

        if (!companyRepository.existsById(companyId)) {
            throw new ApplicationException(DATA_NOT_FOUND);
        }

        List<Coupon> lowerThanMaxPriceCoupons = couponRepository.findByCompanyIdAndPriceLessThan(companyId, maxPrice);

        if (lowerThanMaxPriceCoupons.isEmpty()) {
            throw new ApplicationException(DATA_NOT_FOUND);
        }

        return lowerThanMaxPriceCoupons;
    }


    public Optional<Company> getCompanyDetails(final long companyId) throws ApplicationException {

        Optional<Company> companyOpt = companyRepository.findById(companyId);

        if (companyOpt.isEmpty()) {
            throw new ApplicationException(DATA_NOT_FOUND);
        }

        return companyOpt;
    }
}
