package com.meital.couponproject.repo;

import com.meital.couponproject.entities.Coupon;
import com.meital.couponproject.enums.CouponCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CouponRepo extends JpaRepository<Coupon, Long> {

    //get all coupons by company id
    List<Coupon> getCouponsByCompanyId(Long companyId);

    //get all coupons by customer id
    List<Coupon> getCouponsByCustomersId(Long customerId);

    //get all coupons by customer id and category
    List<Coupon> getCouponsByCustomersIdAndCategory(Long customerId, CouponCategory couponCategory);

    //get all coupons by customer id and max price
    List<Coupon> getCouponsByCustomersIdAndPriceLessThan(Long customerId,Double price);

    //check if coupon exist by coupon title
    boolean existsByTitleIgnoreCase(String title);

    //get all coupons by company and category
    List<Coupon> findByCompanyIdAndCategory(Long companyId, CouponCategory couponCategory);

    //get all coupons by company and max price
    List<Coupon> findByCompanyIdAndPriceLessThan(Long companyId, Double price);


}
