package com.meital.couponproject.repo;

import com.meital.couponproject.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepo extends JpaRepository<Company, Long> {

    boolean existsByEmail(String email);

    boolean existsByName(String name);

}

