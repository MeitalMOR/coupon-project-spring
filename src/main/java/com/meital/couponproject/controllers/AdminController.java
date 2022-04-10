
package com.meital.couponproject.controllers;

import com.meital.couponproject.entities.Company;
import com.meital.couponproject.entities.Customer;
import com.meital.couponproject.exceptions.ApplicationException;
import com.meital.couponproject.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class AdminController {

    private AdminService adminService;

    //Company controller.

    public Company createCompany(final Company company) throws ApplicationException {
        return adminService.createCompany(company);
    }

    public void updateCompany(final long id) throws ApplicationException {
        adminService.updateCompany(id);
    }

    public void deleteCompany(final long id) throws ApplicationException {
        adminService.deleteCompany(id);
    }

    public Optional<Company> getCompany(final long id) throws ApplicationException {
        return adminService.getCompany(id);
    }

    public List<Company> getAllCompanies() throws ApplicationException {
        return adminService.getAllCompanies();
    }


    //Customer controller.

    public void createCustomer(final Customer customer) throws ApplicationException {
        adminService.createCustomer(customer);
    }

    public void updateCustomer(final long id) throws ApplicationException {
        adminService.updateCustomer(id);
    }

    public void deleteCustomer(final long id) throws ApplicationException {
        adminService.deleteCustomer(id);
    }

    public Optional<Customer> getCustomer(final long id) throws ApplicationException {
        return adminService.getCustomer(id);
    }

    public List<Customer> getAllCustomers() throws ApplicationException {
        return adminService.getAllCustomers();
    }
}

