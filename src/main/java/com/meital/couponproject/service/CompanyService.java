package com.meital.couponproject.service;

import com.meital.couponproject.entities.Company;
import com.meital.couponproject.entities.Coupon;
import com.meital.couponproject.enums.CouponCategory;
import com.meital.couponproject.exceptions.ApplicationException;
import com.meital.couponproject.repo.CompanyRepo;
import com.meital.couponproject.repo.CouponRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.meital.couponproject.enums.ErrorType.*;

@Log4j2
@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CouponRepo couponRepo;
    private final CompanyRepo companyRepo;

    //--------------------------add new coupon to company------------------------------------
    public Coupon createCoupon(final Coupon coupon) throws ApplicationException {

        //check if coupon exists by title
        if (couponRepo.existsByTitleIgnoreCase(coupon.getTitle())) {
            throw new ApplicationException(COUPON_ALREADY_EXISTS);
        }

        //check if the company in coupon exist
        if (!companyRepo.existsById(coupon.getCompany().getId())) {
            throw new ApplicationException(COMPANY_DOES_NOT_EXISTS);
        }

        //create the coupon
        couponRepo.save(coupon);
        log.info("\033[0;33m" + "Coupon" + coupon.getId() + " created successfully" + "\033[0m");

        return coupon;
    }

    //----------------------------------update coupon----------------------------------------
    public Coupon updateCoupon(final Coupon coupon) throws ApplicationException {

        //check if coupon exists by id
        if (!couponRepo.existsById(coupon.getId())) {
            throw new ApplicationException(COUPON_DOES_NOT_EXIST);
        }
        //update coupon
        couponRepo.saveAndFlush(coupon);

        log.info("\033[0;33m" + "Coupon" + coupon.getId() + " updated successfully" + "\033[0m");

        return coupon;
    }

    //-------------------------------delete coupon----------------------------------------
    public void deleteCoupon(final long couponId) throws ApplicationException {

        //find coupon by id using Optional
        Optional<Coupon> couponOpt = couponRepo.findById(couponId);

        if (couponOpt.isEmpty()) {
            throw new ApplicationException(COUPON_DOES_NOT_EXIST);
        }

        //delete coupon
        couponRepo.deleteById(couponId);
        log.info("\033[0;33m" + "Coupon" + couponId + " deleted successfully" + "\033[0m");
    }

    //-----------------------------get coupon ---------------------------
    public Optional<Coupon> getCoupon(final long couponId) throws ApplicationException {

        //find coupon by id using Optional
        Optional<Coupon> couponOpt = couponRepo.findById(couponId);

        if (couponOpt.isEmpty()) {
            throw new ApplicationException(COUPON_DOES_NOT_EXIST);
        }
        log.info("\033[0;33m" + "Succeeded get coupon" + "\033[0m");

        return couponOpt;
    }

    //-----------------------------get all company coupons ---------------------------
    public List<Coupon> getCompanyCoupons(final long companyId) throws ApplicationException {

        //check if the company exist
        if (!companyRepo.existsById(companyId)) {
            throw new ApplicationException(COMPANY_DOES_NOT_EXISTS);
        }

        //find coupon list by company id, using couponRepo method
        List<Coupon> coupons = couponRepo.getCouponsByCompanyId(companyId);

        //if the company has no coupons throw exception
        if (coupons.isEmpty()) {
            throw new ApplicationException(COUPON_DOES_NOT_EXIST);
        }

        log.info("\033[0;33m" + "Succeeded get coupon list by company id" + "\033[0m");
        return coupons;
    }

    //-----------------------------get all coupons for specific category -------------------
    public List<Coupon> getCompanyCouponsByCategory(final long companyId, final CouponCategory category) throws ApplicationException {

        //check if the company exist
        if (!companyRepo.existsById(companyId)) {
            throw new ApplicationException(COMPANY_DOES_NOT_EXISTS);
        }

        //find the coupon list by company id and category, using couponRepo method
        List<Coupon> Coupons = couponRepo.findByCompanyIdAndCategory(companyId, category);

        //if the company has no coupons throw exception
        if (Coupons.isEmpty()) {
            throw new ApplicationException(COUPON_DOES_NOT_EXIST);
        }

        log.info("\033[0;33m" + "Succeeded get coupon list by company id and coupon category" + "\033[0m");
        return Coupons;
    }

    //-------------------get all coupons with lower price from maximum price --------------------
    public List<Coupon> getCompanyCouponsByMaxPrice(final long companyId, final double maxPrice) throws ApplicationException {

        //check if the company exist
        if (!companyRepo.existsById(companyId)) {
            throw new ApplicationException(COMPANY_DOES_NOT_EXISTS);
        }

        //find the coupon list by company id and max price, using couponRepo method
        List<Coupon> lowerThanMaxPriceCoupons = couponRepo.findByCompanyIdAndPriceLessThan(companyId, maxPrice);

        //if the company has no coupons throw exception
        if (lowerThanMaxPriceCoupons.isEmpty()) {
            throw new ApplicationException(COUPON_DOES_NOT_EXIST);
        }

        log.info("\033[0;33m" + "Succeeded get coupon list by company id and max price" + "\033[0m");
        return lowerThanMaxPriceCoupons;
    }

    //-------------------------get company details ----------------------------------
    public Optional<Company> getCompanyDetails(final long companyId) throws ApplicationException {

        //find company by id using Optional
        Optional<Company> companyOpt = companyRepo.findById(companyId);

        if (companyOpt.isEmpty()) {
            throw new ApplicationException(COMPANY_DOES_NOT_EXISTS);
        }

        log.info("\033[0;33m" + "Succeeded get company details" + "\033[0m");
        return companyOpt;
    }

}


