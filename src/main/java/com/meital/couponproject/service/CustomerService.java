package com.meital.couponproject.service;

import com.meital.couponproject.entities.Coupon;
import com.meital.couponproject.entities.Customer;
import com.meital.couponproject.enums.CouponCategory;
import com.meital.couponproject.exceptions.ApplicationException;
import com.meital.couponproject.repositories.CouponRepository;
import com.meital.couponproject.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static com.meital.couponproject.enums.ErrorType.*;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CouponRepository couponRepository;
    private final CustomerRepository customerRepository;


    //------------------------------------purchase coupon---------------------------------

    public Coupon purchaseCoupon(Long customerId, final Coupon coupon) throws ApplicationException {

        Optional<Customer> customer = customerRepository.findById(customerId);

        if (customer.isEmpty()) {
            throw new ApplicationException(DATA_NOT_FOUND);
        }

        //if coupon doesn't exist throw exception
        if (!couponRepository.existsById(coupon.getId())) {
            throw new ApplicationException(DATA_NOT_FOUND);
        }

        //check if coupon is out of stock
        if (coupon.getAmount() == 0) {
            throw new ApplicationException(COUPON_OUT_OF_STOCK);
        }

        //check if coupon is expired
        if (coupon.getEndDate().isBefore(LocalDate.now())) {
            throw new ApplicationException(COUPON_HAS_EXPIRED);
        }

        //update coupon amount
        coupon.setAmount(coupon.getAmount() - 1);

        //update the coupon at database
        couponRepository.saveAndFlush(coupon);

        //add coupon to customer list of coupons
        customer.get().getCoupons().add(coupon);

        //update the customer at database
        customerRepository.saveAndFlush(customer.get());


        System.out.println("Purchase succeeded");

        return coupon;
    }

    //---------------------------------get all coupons by customer id----------------------

    public List<Coupon> getCustomerCoupons(Long customerId) throws ApplicationException {

        Optional<Customer> customer = customerRepository.findById(customerId);
        if (customer.isEmpty()) {
            throw new ApplicationException(DATA_NOT_FOUND);
        }
        return customer.get().getCoupons();
    }


    //    public List<Coupon> getCustomerCouponsByCategory(Long customerId, CouponCategory category) throws ApplicationException {
//
//        if (!customerRepository.existsById(customerId)) {
//            throw new ApplicationException(DATA_NOT_FOUND);
//        }
//
//        List<Coupon> customerCouponsByCategory = purchaseRepository.findCouponsByCustomerIdAndCategory(customerId, category);
//
//        if (customerCouponsByCategory.isEmpty()) {
//            throw new ApplicationException(DATA_NOT_FOUND);
//        }
//
//        return customerCouponsByCategory;
//    }
//
    public List<Coupon> getCustomerCouponsByMaxPrice(Long customerId, Double price) throws ApplicationException {

        if (!customerRepository.existsById(customerId)) {
            throw new ApplicationException(DATA_NOT_FOUND);
        }

        List<Coupon> customerCouponsByMaxPrice = couponRepository.findByCustomerIdAndPriceLessThan(customerId, price);

        if (customerCouponsByMaxPrice.isEmpty()) {
            throw new ApplicationException(DATA_NOT_FOUND);
        }

        return customerCouponsByMaxPrice;
    }

    public Optional<Customer> getCustomerDetails(final Long customerID) throws ApplicationException {

        Optional<Customer> customerOpt = customerRepository.findById(customerID);

        if (customerOpt.isEmpty()) {
            throw new ApplicationException(DATA_NOT_FOUND);
        }

        return customerOpt;
    }
//

}
