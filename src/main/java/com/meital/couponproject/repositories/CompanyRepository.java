package com.meital.couponproject.repositories;

import com.meital.couponproject.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

    boolean existsByEmail(String email);

    boolean existsById(Long id);

    boolean existsByNameIgnoreCase(String name);

}

