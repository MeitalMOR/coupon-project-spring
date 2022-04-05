package com.meital.couponproject.service;

import com.meital.couponproject.entities.Company;
import com.meital.couponproject.entities.Customer;
import com.meital.couponproject.repositories.CompanyRepository;
import com.meital.couponproject.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class  AdminService {
    private final CompanyRepository companyRepository;
    private final CustomerRepository customerRepository;

    //company service
    public void createCompany(final Company company) {
        companyRepository.save(company);
    }

    public Company getCompany(final long id) {
        return companyRepository.findById(id).orElse(null);
    }

    public void deleteCompany(final long id) {
        companyRepository.deleteById(id);
    }

    //customer service
    public void createCustomer(final Customer customer) {
        customerRepository.save(customer);
    }

    public Customer getCustomer(final long id) {
        return customerRepository.findById(id).orElse(null);
    }

    public void deleteCustomer(final long id) {
        customerRepository.deleteById(id);
    }
}

