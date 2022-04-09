package com.meital.couponproject.repositories;

import com.meital.couponproject.entities.Coupon;
import com.meital.couponproject.entities.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

    List<Coupon> findCouponsByCustomerId(Long customerId);

    void deletePurchaseByCustomerId(Long customerId);

    void deletePurchaseByCouponId(Long couponId);

    boolean existsByCustomerIdAndCouponId(Long customerId, Long couponId);

}
