package com.meital.couponproject.tests;


import com.meital.couponproject.entities.Company;
import com.meital.couponproject.entities.Coupon;
import com.meital.couponproject.entities.Customer;
import com.meital.couponproject.enums.CouponCategory;
import com.meital.couponproject.exceptions.ApplicationException;
import com.meital.couponproject.repo.CompanyRepo;
import com.meital.couponproject.repo.CouponRepo;
import com.meital.couponproject.repo.CustomerRepo;
import com.meital.couponproject.service.AdminService;
import com.meital.couponproject.service.CustomerService;
import com.meital.couponproject.tests.config.CompanyConfig;
import com.meital.couponproject.tests.config.CustomerConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Log4j2
@Component
@RequiredArgsConstructor
@Order(1)
public class AdminServiceTests {


    private final AdminService adminService;
    private final CustomerService customerService;
    private final CompanyRepo companyRepo;
    private final CustomerRepo customerRepo;
    private final CouponRepo couponRepo;


    //--------------------------------create new company test
    @Transactional
    public void testCreateCompany() throws ApplicationException {

        Company company = adminService.createCompany(Company.builder()
                .name(CompanyConfig.company1Name)
                .email(CompanyConfig.company1Email)
                .password(CompanyConfig.company1Password).build());

        if (company.getId() == 1L) {
            log.info("\033[0;34m" + "Test 1 - create new company - succeeded" + "\033[0m");
        }
    }

    //--------------------------------update company test
    @Transactional
    public void updateCompanyTest() throws ApplicationException {

        Company companyToUpdate = adminService.updateCompany(Company.builder()
                .id(1L)
                .name(CompanyConfig.company1Name)
                .email("company1company@gmail.com")
                .password(CompanyConfig.company1Password)
                .build());

        String email = companyRepo.findById(1L).get().getEmail();

        if (companyToUpdate.getEmail().equals(email)) {
            log.info("\033[0;34m" + "Test 2 - update company - succeeded" + "\033[0m");
        }
    }

    //--------------------------------delete company test
    @Transactional
    public void deleteCompanyTest() throws ApplicationException {
        Customer customer = customerRepo.save(
                Customer.builder()
                        .firstName(CustomerConfig.customer1FirstName)
                        .lastName(CustomerConfig.customer1LastName)
                        .email(CustomerConfig.customer1Email)
                        .password(CustomerConfig.customer1Password).build());

        Coupon coupon = couponRepo.save(
                Coupon.builder().company(companyRepo.getById(1L)).category(CouponCategory.FOOD)
                        .title("coupon1").description("coupon1")
                        .startDate(LocalDate.of(2022, 7, 10))
                        .endDate(LocalDate.of(2022, 7, 30))
                        .amount(12)
                        .price(102.5)
                        .image("www.c.com").build());

        customerService.purchaseCoupon(1L, 1L);
        adminService.deleteCompany(1L);

        if (!companyRepo.existsById(1L) && !companyRepo.existsById(1L)) {
            log.info("\033[0;34m" + "Test 3 - delete company - succeeded" + "\033[0m");
        }
    }

    //--------------------------------list of all companies test
    @Transactional
    public void testGetAllCompanies() throws ApplicationException {
        Company company = adminService.createCompany(Company.builder()
                .name(CompanyConfig.company1Name)
                .email(CompanyConfig.company1Email)
                .password(CompanyConfig.company1Password).build());

        Company company1 = adminService.createCompany(Company.builder()
                .name(CompanyConfig.company2Name)
                .email(CompanyConfig.company2Email)
                .password(CompanyConfig.company2Password).build());

        List<Company> companies = adminService.getAllCompanies();
        if (companies.contains(company) && companies.contains(company1)) {
            log.info("\033[0;34m" + "Test 4 - list of all companies - succeeded" + "\033[0m");
        }
    }

    //--------------------------------Test get company details
    @Transactional
    public void testGetCompanyDetails() throws ApplicationException {

        Coupon coupon = couponRepo.save(
                Coupon.builder().company(companyRepo.getById(2L)).category(CouponCategory.FOOD)
                        .title("coupon1").description("coupon1")
                        .startDate(LocalDate.of(2022, 7, 10))
                        .endDate(LocalDate.of(2022, 7, 30))
                        .amount(12)
                        .price(102.5)
                        .image("www.c.com").build());


        Optional<Company> companyOpt = adminService.getCompany(2L);

        if (companyOpt.isPresent()) {
            log.info("\033[0;34m" + "Test 5 - get company details - succeeded" + "\033[0m");
        }
    }

    //--------------------------------create new customer test
    @Transactional
    public void testCreateCustomer() throws ApplicationException {

        Customer customer = adminService.createCustomer(
                Customer.builder()
                        .firstName(CustomerConfig.customer2FirstName)
                        .lastName(CustomerConfig.customer2LastName)
                        .email(CustomerConfig.customer2Email)
                        .password(CustomerConfig.customer2Password).build());

        if (customer.getId() == 1L) {
            log.info("\033[0;34m" + "Test 6 - create new customer - succeeded" + "\033[0m");
        }
    }

    //--------------------------------update customer test
    @Transactional
    public void testUpdateCustomer() throws ApplicationException {

        Customer customerToUpdate = adminService.updateCustomer(
                Customer.builder()
                        .id(2L)
                        .firstName(CustomerConfig.customer2FirstName)
                        .lastName(CustomerConfig.customer2LastName)
                        .email("customer2customer@gmail.com")
                        .password(CustomerConfig.customer2Password).build());

        String email = customerRepo.findById(2L).get().getEmail();

        if (customerToUpdate.getEmail().equals(email)) {
            log.info("\033[0;34m" + "Test 7 - update customer - succeeded" + "\033[0m");
        }
    }

    //--------------------------------delete customer test
    @Transactional
    public void testDeleteCustomer() throws ApplicationException {

        customerService.purchaseCoupon(1L, 2L);
        adminService.deleteCustomer(1L);

        if (!customerRepo.existsById(1L)) {
            log.info("\033[0;34m" + "Test 8 - delete customer - succeeded" + "\033[0m");
        }
    }

    //--------------------------------list of all customers test
    @Transactional
    public void testGetAllCustomers() throws ApplicationException {
        Customer customer = adminService.createCustomer(
                Customer.builder()
                        .firstName(CustomerConfig.customer3FirstName)
                        .lastName(CustomerConfig.customer3LastName)
                        .email(CustomerConfig.customer3Email)
                        .password(CustomerConfig.customer3Password).build());

        List<Customer> customers = adminService.getAllCustomers();
        if (customers.size() == 2) {
            log.info("\033[0;34m" + "Test 9 - list of all customers - succeeded" + "\033[0m");
        }
    }

    //--------------------------------Test get company details
    @Transactional
    public void testGetCustomerDetails() throws ApplicationException {

        Optional<Customer> customerOpt = adminService.getCustomer(2L);

        if (customerOpt.isPresent()) {
            log.info("\033[0;34m" + "Test 10 - get customer details - succeeded" + "\033[0m");
        }
    }
}
