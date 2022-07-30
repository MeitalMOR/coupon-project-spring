package com.meital.couponproject.repo;

import com.meital.couponproject.entities.Coupon;
import com.meital.couponproject.enums.CouponCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CouponRepo extends JpaRepository<Coupon, Long> {

    //Get all coupons by company ID
    List<Coupon> getCouponsByCompanyId(Long companyId);

    //Get all coupons by customer ID
    List<Coupon> getCouponsByCustomersId(Long customerId);

    //Get all coupons by customer ID and category
    List<Coupon> getCouponsByCustomersIdAndCategory(Long customerId, CouponCategory couponCategory);

    //Get all coupons by customer ID and max price
    List<Coupon> getCouponsByCustomersIdAndPriceLessThan(Long customerId, Double price);

    //Check if a coupon exists by the coupon title
    boolean existsByTitleIgnoreCase(String title);

    //Get all coupons by company and category
    List<Coupon> findByCompanyIdAndCategory(Long companyId, CouponCategory couponCategory);

    //Get all coupons by company and max price
    List<Coupon> findByCompanyIdAndPriceLessThan(Long companyId, Double price);


}
