package com.meital.couponproject.service;

import com.meital.couponproject.entities.Company;
import com.meital.couponproject.entities.Customer;
import com.meital.couponproject.exceptions.ApplicationException;
import com.meital.couponproject.repo.CompanyRepo;
import com.meital.couponproject.repo.CustomerRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.meital.couponproject.enums.ErrorType.*;

@Log4j2
@Service
@RequiredArgsConstructor
public class AdminService {

    private final CompanyRepo companyRepo;
    private final CustomerRepo customerRepo;

    //Company --------
    //--------------------------------create new company --------------------------
    public Company createCompany(final Company company) throws ApplicationException {

        //check if company exists by name
        if (companyRepo.existsByName(company.getName())) {
            throw new ApplicationException(COMPANY_NAME_ALREADY_EXISTS);
        }

        //check if company exists by email
        if (companyRepo.existsByEmail(company.getEmail())) {
            throw new ApplicationException(EMAIL_ALREADY_EXISTS);
        }

        //create new company
        companyRepo.save(company);
        log.info("\033[0;33m" + "Company created successfully" + "\033[0m");

        return company;
    }

    //--------------------------------update company -------------------------------
    public Company updateCompany(final Company company) throws ApplicationException {

        //check if company exists by id
        if (!companyRepo.existsById(company.getId())) {
            throw new ApplicationException(COMPANY_DOES_NOT_EXISTS);
        }

        //update company
        companyRepo.saveAndFlush(company);
        log.info("\033[0;33m" + "Company updated successfully" + "\033[0m");

        return company;
    }

    //----------------------------------delete company -------------------------------
    public void deleteCompany(final long companyId) throws ApplicationException {

        //find company by id using Optional
        Optional<Company> companyOpt = companyRepo.findById(companyId);

        //check if company exists
        if (companyOpt.isEmpty()) {
            throw new ApplicationException(COMPANY_DOES_NOT_EXISTS);
        }

        //delete company
        companyRepo.deleteById(companyId);
        log.info("\033[0;33m" + "Company deleted successfully" + "\033[0m");
    }

    //------------------------------get all companies ----------------------------------
    public List<Company> getAllCompanies() throws ApplicationException {

        //find all companies
        List<Company> companies = companyRepo.findAll();

        if (companies.isEmpty()) {
            throw new ApplicationException(COMPANY_DOES_NOT_EXISTS);
        }

        log.info("\033[0;33m" + "Succeeded get all companies list" + "\033[0m");
        return companies;
    }

    //---------------------------------get company ---------------------------------
    public Optional<Company> getCompany(final long companyId) throws ApplicationException {

        //find company by id using Optional
        Optional<Company> companyOpt = companyRepo.findById(companyId);

        if (companyOpt.isEmpty()) {
            throw new ApplicationException(COMPANY_DOES_NOT_EXISTS);
        }

        log.info("\033[0;33m" + "Succeeded get company details" + "\033[0m");
        return companyOpt;
    }

    //Customer --------
    //------------------------------------create new customer -----------------------------
    public Customer createCustomer(final Customer customer) throws ApplicationException {

        //check if customer exist by email
        if (customerRepo.existsByEmail(customer.getEmail())) {
            throw new ApplicationException(EMAIL_ALREADY_EXISTS);
        }

        //create new customer
        customerRepo.save(customer);
        log.info("\033[0;33m" + "Customer created successfully" + "\033[0m");

        return customer;
    }

    //--------------------------------update customer -----------------------------------
    public Customer updateCustomer(final Customer customer) throws ApplicationException {

        //check if customer exists by id
        if (!customerRepo.existsById(customer.getId())) {
            throw new ApplicationException(CUSTOMER_DOES_NOT_EXISTS);
        }

        //update customer
        customerRepo.saveAndFlush(customer);
        log.info("\033[0;33m" + "Customer updated successfully" + "\033[0m");

        return customer;
    }

    //---------------------------------delete customer --------------------------------------
    public void deleteCustomer(final long customerId) throws ApplicationException {

        //find customer by id using Optional
        Optional<Customer> customerOpt = customerRepo.findById(customerId);

        if (customerOpt.isEmpty()) {
            throw new ApplicationException(CUSTOMER_DOES_NOT_EXISTS);
        }

        //delete customer
        customerRepo.deleteById(customerId);
        log.info("\033[0;33m" + "Customer deleted successfully" + "\033[0m");

    }

    //--------------------------------get all customers -------------------------------
    public List<Customer> getAllCustomers() throws ApplicationException {

        //find all customers
        List<Customer> customers = customerRepo.findAll();

        if (customers.isEmpty()) {
            throw new ApplicationException(CUSTOMER_DOES_NOT_EXISTS);
        }

        log.info("\033[0;33m" + "Succeeded get all customers list" + "\033[0m");
        return customers;
    }

    //------------------------------get customer by ID ----------------------------
    public Optional<Customer> getCustomer(final long customerId) throws ApplicationException {

        //find customer by id using Optional
        Optional<Customer> customerOpt = customerRepo.findById(customerId);

        if (customerOpt.isEmpty()) {
            throw new ApplicationException(CUSTOMER_DOES_NOT_EXISTS);
        }

        log.info("\033[0;33m" + "Succeeded get customer details" + "\033[0m");
        return customerOpt;
    }


}
