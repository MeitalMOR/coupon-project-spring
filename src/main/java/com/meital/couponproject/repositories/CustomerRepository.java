package com.meital.couponproject.repositories;

import com.meital.couponproject.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    boolean existsByEmailAndPassword(String email, String password);

    boolean existsById(Long customerId);

    boolean existsByEmail(String email);

    @Transactional
    @Query(value = "SELECT coupon_id FROM purchases WHERE customer_id=:customerId", nativeQuery = true)
    List<Long> getCustomerCouponsId(@Param("customerId") long customerId);


}
