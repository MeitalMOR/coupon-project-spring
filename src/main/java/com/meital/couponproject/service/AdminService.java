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

    //company
    //--------------------------------create new company --------------------------
    public Company createCompany(final Company company) throws ApplicationException {

        //check if company exists by name
        if (companyRepository.existsByNameIgnoreCase(company.getName())) {
            throw new ApplicationException(COMPANY_NAME_ALREADY_EXISTS);
        }

        //check if company exists by email
        if (companyRepository.existsByEmail(company.getEmail())) {
            throw new ApplicationException(EMAIL_ALREADY_EXISTS);
        }

        //create new company
        companyRepository.save(company);
        return company;
    }

    //--------------------------------update company -------------------------------
    public Company updateCompany(final Company company) throws ApplicationException {

        //check if company exists by id
        if (!companyRepository.existsById(company.getId())) {
            throw new ApplicationException(DATA_NOT_FOUND);
        }

        //update company
        companyRepository.saveAndFlush(company);
        return company;
    }

    //----------------------------------delete company -------------------------------
    public void deleteCompany(final long companyId) throws ApplicationException {

        //find company by id using Optional
        Optional<Company> companyOpt = companyRepository.findById(companyId);

        if (companyOpt.isEmpty()) {
            throw new ApplicationException(DATA_NOT_FOUND);
        }

        //delete company
        companyRepository.deleteById(companyId);

        System.out.println("Company" + companyId + " deleted successfully");
    }

    //------------------------------get all companies ----------------------------------
    public List<Company> getAllCompanies() throws ApplicationException {

        //find all companies
        List<Company> companies = companyRepository.findAll();

        if (companies.isEmpty()) {
            throw new ApplicationException(DATA_NOT_FOUND);
        }

        return companies;
    }

    //---------------------------------get company ---------------------------------
    public Optional<Company> getCompany(final long companyId) throws ApplicationException {

        //find company by id using Optional
        Optional<Company> companyOpt = companyRepository.findById(companyId);

        if (companyOpt.isEmpty()) {
            throw new ApplicationException(DATA_NOT_FOUND);
        }

        return companyOpt;
    }

    //customer
    //------------------------------------create new company -----------------------------
    public void createCustomer(final Customer customer) throws ApplicationException {

        //check if customer exist by email
        if (customerRepository.existsByEmail(customer.getEmail())) {
            throw new ApplicationException(EMAIL_ALREADY_EXISTS);
        }

        //create new customer
        customerRepository.save(customer);
    }

    //--------------------------------update customer -----------------------------------
    public Customer updateCustomer(final Customer customer) throws ApplicationException {

        //check if customer exists by id
        if (!customerRepository.existsById(customer.getId())) {
            throw new ApplicationException(DATA_NOT_FOUND);
        }

        //update customer
        customerRepository.saveAndFlush(customer);
        return customer;
    }

    //---------------------------------delete customer --------------------------------------
    public void deleteCustomer(final long customerId) throws ApplicationException {

        //find customer by id using Optional
        Optional<Customer> customerOpt = customerRepository.findById(customerId);

        if (customerOpt.isEmpty()) {
            throw new ApplicationException(DATA_NOT_FOUND);
        }

        //delete customer
        companyRepository.deleteById(customerId);

        System.out.println("Customer" + customerId + " deleted successfully");
    }

    //--------------------------------get all customers -------------------------------
    public List<Customer> getAllCustomers() throws ApplicationException {

        //find all customers
        List<Customer> customers = customerRepository.findAll();

        if (customers.isEmpty()) {
            throw new ApplicationException(DATA_NOT_FOUND);
        }

        return customers;
    }

    //------------------------------get one customer by ID ----------------------------
    public Optional<Customer> getCustomer(final long customerId) throws ApplicationException {

        //find customer by id using Optional
        Optional<Customer> customerOpt = customerRepository.findById(customerId);

        if (customerOpt.isEmpty()) {
            throw new ApplicationException(DATA_NOT_FOUND);
        }

        return customerOpt;
    }

}

