package com.meital.couponproject.repo;

import com.meital.couponproject.entities.Coupon;
import com.meital.couponproject.enums.CouponCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CouponRepo extends JpaRepository<Coupon, Long> {

    List<Coupon> getCouponsByCompanyId(Long companyId);

    List<Coupon> getCouponsByCustomersId(Long customerId);

    List<Coupon> getCouponsByCustomersIdAndCategory(Long customerId, CouponCategory couponCategory);

    List<Coupon> getCouponsByCustomersIdAndPriceLessThan(Long customerId,Double price);

    boolean existsByTitleIgnoreCase(String title);

    List<Coupon> findByCompanyIdAndCategory(Long companyId, CouponCategory couponCategory);

    List<Coupon> findByCompanyIdAndPriceLessThan(Long companyId, Double price);


}
