package com.meital.couponproject.service;

import com.meital.couponproject.entities.Company;
import com.meital.couponproject.entities.Customer;
import com.meital.couponproject.exceptions.ApplicationException;
import com.meital.couponproject.repositories.CompanyRepository;
import com.meital.couponproject.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.meital.couponproject.enums.ErrorType.*;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final CompanyRepository companyRepository;
    private final CustomerRepository customerRepository;

    //company service
    public void createCompany(final Company company) throws ApplicationException {
        if (companyRepository.existsByNameIgnoreCase(company.getName())) {
            throw new ApplicationException(COMPANY_NAME_ALREADY_EXISTS);
        }
        if (companyRepository.existsByEmail(company.getEmail())) {
            throw new ApplicationException(EMAIL_ALREADY_EXISTS);
        }
        companyRepository.save(company);
    }

    public void updateCompany(final long id) throws ApplicationException {
        String emailToUpdate = "company@gmail.com";
        Company company = companyRepository.findById(id).orElse(null);
        if (company != null) {
            company.setEmail(emailToUpdate);
            companyRepository.save(company);
        }
        throw new ApplicationException(DATA_NOT_FOUND);
    }


    public void deleteCompany(final long id) throws ApplicationException {
        if (!companyRepository.existsById(id)) {
            throw new ApplicationException(DATA_NOT_FOUND);
        }
        companyRepository.deleteById(id);
    }

    public Company getCompany(final long id) throws ApplicationException {
        Company company = companyRepository.findById(id).orElse(null);
        if (company == null) {
            throw new ApplicationException(DATA_NOT_FOUND);
        }
        return company;
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

