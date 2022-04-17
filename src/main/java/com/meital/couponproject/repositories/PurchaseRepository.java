package com.meital.couponproject.repositories;

import com.meital.couponproject.entities.Coupon;
import com.meital.couponproject.entities.Purchase;
import com.meital.couponproject.enums.CouponCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

    List<Coupon> findCouponsByCustomerId(Long customerId);

    List<Coupon> findCouponsByCustomerIdAndCategory(Long customerId, CouponCategory category);

    List<Coupon> findCouponsByCustomerIdAndPriceLessThan(Long customerId, Double price);

    void deletePurchaseByCustomerId(Long customerId);

    void deletePurchaseByCouponId(Long couponId);

    boolean existsByCustomerIdAndCouponId(Long customerId, Long couponId);

}
