package com.meital.couponproject.repo;

import com.meital.couponproject.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long> {

    //Check if the customer exists by name
    boolean existsByEmail(String email);

    //Check if the customer exists using the email address and password
    boolean existsByEmailAndPassword(String email, String password);

    //find customer by email
    Customer findByEmail(String email);


}
