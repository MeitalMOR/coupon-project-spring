package com.meital.couponproject.tests;

import com.meital.couponproject.entities.*;
import com.meital.couponproject.exceptions.ApplicationException;
import com.meital.couponproject.repo.*;
import com.meital.couponproject.service.*;
import com.meital.couponproject.tests.config.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static com.meital.couponproject.enums.ErrorType.*;
import static com.meital.couponproject.tests.config.CompanyConfig.*;
import static com.meital.couponproject.tests.config.CustomerConfig.*;


@Log4j2
@Component
@RequiredArgsConstructor
@Order(1)
public class AdminServiceTests {


    private final AdminService adminService;
    private final CompanyRepo companyRepo;
    private final CustomerRepo customerRepo;


    //--------------------------------create new company test
    @Transactional
    public void testCreateCompanies() throws ApplicationException {

        adminService.createCompany(Company.builder()
                .name(CompanyConfig.company1Name)
                .email(CompanyConfig.company1Email)
                .password(CompanyConfig.company1Password).build());

        adminService.createCompany(Company.builder()
                .name(CompanyConfig.company2Name)
                .email(CompanyConfig.company2Email)
                .password(CompanyConfig.company2Password).build());

        adminService.createCompany(Company.builder()
                .name(CompanyConfig.company3Name)
                .email(CompanyConfig.company3Email)
                .password(CompanyConfig.company3Password).build());

        if (companyRepo.existsById(1L) && companyRepo.existsById(2L) && companyRepo.existsById(3L)) {
            log.info("\033[0;32m" + "Test - create new companies - succeeded" + "\033[0m");
        }
    }

    //--------------------------------update company test
    @Transactional
    public void updateCompanyTest() throws ApplicationException {

        Optional<Company> company = adminService.getCompany(2L);
        if (company.isPresent()) {

            company.get().setEmail(companyToUpdateEmail);
            company.get().setPassword(companyToUpdatePassword);

            adminService.updateCompany(company.get());

            if (companyRepo.existsByEmail(companyToUpdateEmail)) {
                log.info("\033[0;32m" + "Test - update company - succeeded" + "\033[0m");
            }
        } else {
            throw new ApplicationException(COMPANY_DOES_NOT_EXISTS);
        }
    }

    //--------------------------------delete company test
    @Transactional
    public void deleteCompanyTest() throws ApplicationException {

        adminService.deleteCompany(1L);

        if (!companyRepo.existsById(1L)) {
            log.info("\033[0;32m" + "Test - delete company - succeeded" + "\033[0m");
        }
    }

    //--------------------------------list of all companies test
    @Transactional
    public void testGetAllCompanies() throws ApplicationException {

        List<Company> companies = adminService.getAllCompanies();

        if (companies.size() == 3) {
            log.info("\033[0;32m" + "Test - list of all companies - succeeded" + "\033[0m");
        }
    }

    //--------------------------------Test get company details
    @Transactional
    public void testGetCompanyDetails() throws ApplicationException {

        Optional<Company> companyOpt = adminService.getCompany(2L);

        if (companyOpt.isPresent()) {
            log.info("\033[0;32m" + "Test - get company details - succeeded" + "\033[0m");
        }
    }

    //--------------------------------create new customer test
    @Transactional
    public void testCreateCustomers() throws ApplicationException {

        adminService.createCustomer(Customer.builder()
                .firstName(CustomerConfig.customer1FirstName)
                .lastName(CustomerConfig.customer1LastName)
                .email(CustomerConfig.customer1Email)
                .password(CustomerConfig.customer1Password).build());

        adminService.createCustomer(Customer.builder()
                .firstName(CustomerConfig.customer2FirstName)
                .lastName(CustomerConfig.customer2LastName)
                .email(CustomerConfig.customer2Email)
                .password(CustomerConfig.customer2Password).build());

        adminService.createCustomer(Customer.builder()
                .firstName(CustomerConfig.customer3FirstName)
                .lastName(CustomerConfig.customer3LastName)
                .email(CustomerConfig.customer3Email)
                .password(CustomerConfig.customer3Password).build());

        if (customerRepo.existsById(1L) && customerRepo.existsById(2L) && customerRepo.existsById(3L)) {
            log.info("\033[0;32m" + "Test - create new customers - succeeded" + "\033[0m");
        }
    }

    //--------------------------------update customer test
    @Transactional
    public void testUpdateCustomer() throws ApplicationException {

        Optional<Customer> customer = adminService.getCustomer(2L);

        if (customer.isPresent()) {

            customer.get().setFirstName(customerToUpdateFirstName);
            customer.get().setEmail(customerToUpdateEmail);
            customer.get().setPassword(customerToUpdatePassword);

            adminService.updateCustomer(customer.get());

            if (customerRepo.existsByEmail(customerToUpdateEmail)) {
                log.info("\033[0;32m" + "Test - update customer - succeeded" + "\033[0m");
            }
        } else {
            throw new ApplicationException(CUSTOMER_DOES_NOT_EXISTS);
        }
    }

    //--------------------------------delete customer test
    @Transactional
    public void testDeleteCustomer() throws ApplicationException {

        adminService.deleteCustomer(1L);

        if (!customerRepo.existsById(1L)) {
            log.info("\033[0;32m" + "Test - delete customer - succeeded" + "\033[0m");
        }
    }

    //--------------------------------list of all customers test
    @Transactional
    public void testGetAllCustomers() throws ApplicationException {

        List<Customer> customers = adminService.getAllCustomers();
        if (customers.size() == 3) {
            log.info("\033[0;32m" + "Test - list of all customers - succeeded" + "\033[0m");
        }
    }

    //--------------------------------Test get company details
    @Transactional
    public void testGetCustomerDetails() throws ApplicationException {

        Optional<Customer> customerOpt = adminService.getCustomer(2L);

        if (customerOpt.isPresent()) {
            log.info("\033[0;32m" + "Test - get customer details - succeeded" + "\033[0m");
        }
    }
}
