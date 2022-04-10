package com.meital.couponproject.service;

import com.meital.couponproject.entities.Company;
import com.meital.couponproject.entities.Customer;
import com.meital.couponproject.exceptions.ApplicationException;
import com.meital.couponproject.repositories.CompanyRepository;
import com.meital.couponproject.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        if (company == null) {
            throw new ApplicationException(DATA_NOT_FOUND);
        }
        company.setEmail(emailToUpdate);
        companyRepository.save(company);
    }


    public void deleteCompany(final long id) throws ApplicationException {
        if (!companyRepository.existsById(id)) {
            throw new ApplicationException(DATA_NOT_FOUND);
        }
        companyRepository.deleteById(id);
    }

    public Optional<Company> getCompany(final long id) throws ApplicationException {
        Optional<Company> companyOpt = companyRepository.findById(id);
        if (companyOpt.isEmpty()) {
            throw new ApplicationException(DATA_NOT_FOUND);
        }
        return companyOpt;
    }

    public List<Company> getAllCompanies() throws ApplicationException {
        List<Company> companies = companyRepository.findAll();
        if (companies.isEmpty()) {
            throw new ApplicationException(DATA_NOT_FOUND);
        }
        return companies;
    }

    //customer service
    public void createCustomer(final Customer customer) throws ApplicationException {
        if (customerRepository.existsByEmail(customer.getEmail())) {
            throw new ApplicationException(EMAIL_ALREADY_EXISTS);
        }
        customerRepository.save(customer);
    }

    public void updateCustomer(final long id) throws ApplicationException {
        String emailToUpdate = "customer@gmail.com";
        Customer customer = customerRepository.findById(id).orElse(null);
        if (customer == null) {
            throw new ApplicationException(DATA_NOT_FOUND);
        }
        customer.setEmail(emailToUpdate);
        customerRepository.save(customer);
    }

    public void deleteCustomer(final long id) throws ApplicationException {
        if (!customerRepository.existsById(id)) {
            throw new ApplicationException(DATA_NOT_FOUND);
        }
        customerRepository.deleteById(id);
    }

    public Optional<Customer> getCustomer(final long id) throws ApplicationException {
        Optional<Customer> customerOpt = customerRepository.findById(id);
        if (customerOpt.isEmpty()) {
            throw new ApplicationException(DATA_NOT_FOUND);
        }
        return customerOpt;
    }

    public List<Customer> getAllCustomers() throws ApplicationException {
        List<Customer> customers = customerRepository.findAll();
        if (customers.isEmpty()) {
            throw new ApplicationException(DATA_NOT_FOUND);
        }
        return customers;
    }
}

