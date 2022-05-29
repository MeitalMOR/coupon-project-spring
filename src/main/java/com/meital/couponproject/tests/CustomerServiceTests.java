package com.meital.couponproject.tests;

import com.meital.couponproject.entities.Coupon;
import com.meital.couponproject.entities.Customer;
import com.meital.couponproject.exceptions.ApplicationException;
import com.meital.couponproject.service.AdminService;
import com.meital.couponproject.service.CompanyService;
import com.meital.couponproject.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static com.meital.couponproject.enums.CouponCategory.*;

@Log4j2
@Component
@RequiredArgsConstructor
@Order(3)
public class CustomerServiceTests {

    private final CustomerService customerService;
    private final AdminService adminService;
    private final CompanyService companyService;

    //--------------------------------create new coupon test
    @Transactional
    public void testPurchaseCoupon() throws ApplicationException {

        Optional<Customer> customerOpt = adminService.getCustomer(1L);
        Optional<Coupon> couponOpt = companyService.getCoupon(1L);
        if (customerOpt.isPresent() && couponOpt.isPresent()) {
            customerService.purchaseCoupon(customerOpt.get().getId(), couponOpt.get().getId());
        }
        List<Coupon> customerCoupons = customerService.getCustomerCoupons(customerOpt.get().getId());
        if (!customerCoupons.isEmpty()) {
            log.info("\033[0;32m" + "Test 1 - purchase coupon - succeeded" + "\033[0m");

        }
    }

    //-------------------------------------get customer coupons ----------------------------
    @Transactional
    public void getCustomerCoupons() throws ApplicationException {
        Optional<Customer> customerOpt = adminService.getCustomer(1L);
        if (customerOpt.isPresent()) {
            List<Coupon> customerCoupons = customerService.getCustomerCoupons(customerOpt.get().getId());
            if (!customerCoupons.isEmpty()) {
                log.info("\033[0;32m" + "Test 2 - get customer coupons - succeeded" + "\033[0m");
            }
        }
    }

    //-------------------------------------get customer coupons by coupon category ----------------------------
    @Transactional
    public void getCustomerCouponsByCategory() throws ApplicationException {
        Optional<Customer> customerOpt = adminService.getCustomer(1L);
        if (customerOpt.isPresent()) {
            List<Coupon> customerCoupons = customerService.getCustomerCouponsByCategory
                    (customerOpt.get().getId(), SPA_AND_BEAUTY);
            if (!customerCoupons.isEmpty()) {
                log.info("\033[0;32m" + "Test 3 - get customer coupons by category - succeeded" + "\033[0m");
            }
        }
    }

    //-------------------------------------get customer coupons by max price ---------------------------
    @Transactional
    public void getCustomerCouponsByMaxPrice() throws ApplicationException {
        double maxPrice = 254;
        Optional<Customer> customerOpt = adminService.getCustomer(1L);
        if (customerOpt.isPresent()) {
            List<Coupon> customerCoupons = customerService.getCustomerCouponsByMaxPrice
                    (customerOpt.get().getId(), maxPrice);
            if (!customerCoupons.isEmpty()) {
                log.info("\033[0;32m" + "Test 4 - get customer coupons by max price - succeeded" + "\033[0m");
            }
        }
    }
}
