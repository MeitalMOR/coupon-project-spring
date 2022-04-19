package com.meital.couponproject.service;

import com.meital.couponproject.entities.Coupon;
import com.meital.couponproject.entities.Customer;
import com.meital.couponproject.exceptions.ApplicationException;
import com.meital.couponproject.repositories.CouponRepository;
import com.meital.couponproject.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import static com.meital.couponproject.enums.ErrorType.*;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CouponRepository couponRepository;
    private final CustomerRepository customerRepository;


    public Coupon purchaseCoupon(Long customerId, final Coupon coupon) throws ApplicationException {
        Customer customer = customerRepository.getById(customerId);
        //check if coupon doesn't exist, and throw exception
        if (!couponRepository.existsById(coupon.getId())) {
            throw new ApplicationException(DATA_NOT_FOUND);
        }

        //check if coupon is out of stock
        if (couponRepository.findById(coupon.getId()).get().getAmount() == 0) {
            throw new ApplicationException(COUPON_OUT_OF_STOCK);
        }

        if (coupon.getEndDate().isBefore(LocalDate.now())) {
            throw new ApplicationException(COUPON_HAS_EXPIRED);
        }
        coupon.setAmount(coupon.getAmount() - 1);
        customer.getCoupons().add(coupon);
        couponRepository.saveAndFlush(coupon);
        System.out.println("Purchase succeeded");
        return coupon;
    }
//
//    public List<Coupon> getCustomerCoupons(Long customerID) throws ApplicationException {
//
//        if (!customerRepository.existsById(customerID)) {
//            throw new ApplicationException(DATA_NOT_FOUND);
//        }
//
//        List<Coupon> customerCoupons = purchaseRepository.findCouponsByCustomerId(customerID);
//
//        if (customerCoupons.isEmpty()) {
//            throw new ApplicationException(DATA_NOT_FOUND);
//        }
//
//        return customerCoupons;
//    }
//
//    public List<Coupon> getCustomerCouponsByCategory(Long customerID, CouponCategory category) throws ApplicationException {
//
//        if (!customerRepository.existsById(customerID)) {
//            throw new ApplicationException(DATA_NOT_FOUND);
//        }
//
//        List<Coupon> customerCouponsByCategory = purchaseRepository.findCouponsByCustomerIdAndCategory(customerID, category);
//
//        if (customerCouponsByCategory.isEmpty()) {
//            throw new ApplicationException(DATA_NOT_FOUND);
//        }
//
//        return customerCouponsByCategory;
//    }
//
//    public List<Coupon> getCustomerCouponsByMaxPrice(Long customerID, Double price) throws ApplicationException {
//
//        if (!customerRepository.existsById(customerID)) {
//            throw new ApplicationException(DATA_NOT_FOUND);
//        }
//
//        List<Coupon> customerCouponsByMaxPrice = purchaseRepository.findCouponsByCustomerIdAndPriceLessThan(customerID, price);
//
//        if (customerCouponsByMaxPrice.isEmpty()) {
//            throw new ApplicationException(DATA_NOT_FOUND);
//        }
//
//        return customerCouponsByMaxPrice;
//    }
//
//    public Optional<Customer> getCustomerDetails(final long customerID) throws ApplicationException {
//
//        Optional<Customer> customerOpt = customerRepository.findById(customerID);
//
//        if (customerOpt.isEmpty()) {
//            throw new ApplicationException(DATA_NOT_FOUND);
//        }
//
//        return customerOpt;
//    }
//

}
