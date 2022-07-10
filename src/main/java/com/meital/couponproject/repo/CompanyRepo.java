package com.meital.couponproject.repo;

import com.meital.couponproject.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepo extends JpaRepository<Company, Long> {

    //check if company exist by email
    boolean existsByEmail(String email);

    //check if company exist by name
    boolean existsByName(String name);

}

