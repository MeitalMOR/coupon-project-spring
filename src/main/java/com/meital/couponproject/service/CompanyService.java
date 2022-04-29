package com.meital.couponproject.service;

import com.meital.couponproject.entities.Company;
import com.meital.couponproject.entities.Coupon;
import com.meital.couponproject.enums.CouponCategory;
import com.meital.couponproject.exceptions.ApplicationException;
import com.meital.couponproject.repositories.CompanyRepository;
import com.meital.couponproject.repositories.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.meital.couponproject.enums.ErrorType.*;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CouponRepository couponRepository;
    private final CompanyRepository companyRepository;


    //--------------------------add new coupon to company------------------------------------
    public void createCoupon(final Coupon coupon) throws ApplicationException {

        //check if coupon exists by title
        if (couponRepository.existsByTitleIgnoreCase(coupon.getTitle())) {
            throw new ApplicationException(COUPON_ALREADY_EXISTS);
        }

        //check if the company in coupon exist
        if (!companyRepository.existsById(coupon.getCompanyId())) {
            throw new ApplicationException(COUPON_ALREADY_EXISTS);
        }

        //create the coupon
        couponRepository.save(coupon);
    }

    //----------------------------------update coupon----------------------------------------
    public Coupon updateCoupon(final Coupon coupon) throws ApplicationException {

        //check if coupon exists by id
        if (!couponRepository.existsById(coupon.getId())) {
            throw new ApplicationException(DATA_NOT_FOUND);
        }

        //update coupon
        couponRepository.saveAndFlush(coupon);
        return coupon;
    }

    //-------------------------------delete coupon----------------------------------------
    public void deleteCoupon(final long couponId) throws ApplicationException {

        //find coupon by id using Optional
        Optional<Coupon> couponOpt = couponRepository.findById(couponId);

        if (couponOpt.isEmpty()) {
            throw new ApplicationException(DATA_NOT_FOUND);
        }

        //delete coupon
        couponRepository.deleteById(couponId);

        System.out.println("Coupon" + couponId + " deleted successfully");
    }

    //-----------------------------get all  company coupons ---------------------------
    public List<Coupon> getCompanyCoupons(final long companyId) throws ApplicationException {

        //check if the company exist
        if (!companyRepository.existsById(companyId)) {
            throw new ApplicationException(COMPANY_IS_NOT_EXISTS);
        }

        //find the coupon list by company id, using couponRepo method
        List<Coupon> coupons = couponRepository.findByCompanyId(companyId);

        //if the company has no coupons throw exception
        if (coupons.isEmpty()) {
            throw new ApplicationException(DATA_NOT_FOUND);
        }

        return coupons;
    }

    //-----------------------------get all coupon for specific category -------------------
    public List<Coupon> getCompanyCouponsByCategory(final long companyId, final CouponCategory category) throws ApplicationException {

        //check if the company exist
        if (!companyRepository.existsById(companyId)) {
            throw new ApplicationException(COMPANY_IS_NOT_EXISTS);
        }

        //find the coupon list by company id and category, using couponRepo method
        List<Coupon> Coupons = couponRepository.findByCompanyIdAndCategory(companyId, category);

        //if the company has no coupons throw exception
        if (Coupons.isEmpty()) {
            throw new ApplicationException(DATA_NOT_FOUND);
        }

        return Coupons;
    }

    //-------------------get all coupon with lower price from maximum price --------------------
    public List<Coupon> getCompanyCouponsByMaxPrice(final long companyId, final double maxPrice) throws ApplicationException {

        //check if the company exist
        if (!companyRepository.existsById(companyId)) {
            throw new ApplicationException(COMPANY_IS_NOT_EXISTS);
        }

        //find the coupon list by company id and max price, using couponRepo method
        List<Coupon> lowerThanMaxPriceCoupons = couponRepository.findByCompanyIdAndPriceLessThan(companyId, maxPrice);

        //if the company has no coupons throw exception
        if (lowerThanMaxPriceCoupons.isEmpty()) {
            throw new ApplicationException(DATA_NOT_FOUND);
        }

        return lowerThanMaxPriceCoupons;
    }


    //-------------------------get all company details ----------------------------------
    public Optional<Company> getCompanyDetails(final long companyId) throws ApplicationException {

        //find company by id using Optional
        Optional<Company> companyOpt = companyRepository.findById(companyId);

        if (companyOpt.isEmpty()) {
            throw new ApplicationException(COMPANY_IS_NOT_EXISTS);
        }

        return companyOpt;
    }
}
