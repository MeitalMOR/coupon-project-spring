package com.meital.couponproject.repo;

import com.meital.couponproject.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepo extends JpaRepository<Company, Long> {

    //Check if the company exists by email
    boolean existsByEmail(String email);

    //Check if the company exists by name
    boolean existsByName(String name);

}

