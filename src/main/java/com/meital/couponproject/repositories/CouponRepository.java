package com.meital.couponproject.repositories;

import com.meital.couponproject.entities.Coupon;
import com.meital.couponproject.enums.CouponCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Long> {
    boolean existsByTitle(String title);

    boolean existsById(Long id);

    List<Coupon> findByCompanyId (Long companyId);

    List<Coupon> findByCompanyIdAndCategory (Long companyId, CouponCategory couponCategory);
}
