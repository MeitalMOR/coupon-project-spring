package com.meital.couponproject.repositories;

import com.meital.couponproject.entities.Coupon;
import com.meital.couponproject.enums.CouponCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Long> {

    boolean existsByTitleIgnoreCase(String title);

    boolean existsById(Long id);

    List<Coupon> findByCompanyId(Long companyId);

    List<Coupon> findByCompanyIdAndCategory(Long companyId, CouponCategory couponCategory);

    List<Coupon> findByCompanyIdAndPriceLessThan(Long companyId, Double price);

    //Purchase functions

    //@Query(value = "SELECT * FROM customer_vs_coupons WHERE customer_id=:customerId", nativeQuery = true)
    //List<Long> allCouponsPurchases(@Param("customerId") Long customerId);
//
//    List<Coupon> findCouponsByCustomerIdAndCategory(Long customerId, CouponCategory category);
//
//    List<Coupon> findCouponsByCustomerIdAndPriceLessThan(Long customerId, Double price);
}
