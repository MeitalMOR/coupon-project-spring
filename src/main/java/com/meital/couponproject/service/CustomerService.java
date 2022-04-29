package com.meital.couponproject.service;

import com.meital.couponproject.entities.Coupon;
import com.meital.couponproject.entities.Customer;
import com.meital.couponproject.exceptions.ApplicationException;
import com.meital.couponproject.repositories.CouponRepository;
import com.meital.couponproject.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.meital.couponproject.enums.ErrorType.*;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CouponRepository couponRepository;
    private final CustomerRepository customerRepository;


    //------------------------------------purchase coupon---------------------------------
    @Transactional
    public Coupon purchaseCoupon(Long customerId, final Coupon coupon) throws ApplicationException {

        Customer customer = customerRepository.getById(customerId);

        //if coupon doesn't exist, and throw exception
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

        //update coupon amount
        coupon.setAmount(coupon.getAmount() - 1);

        //add coupon to customer list of coupons
        List<Coupon> couponList = customer.getCoupons();
        couponList.add(coupon);

        customer.setCoupons(couponList);

        //update the coupon at database
        couponRepository.saveAndFlush(coupon);

        System.out.println("Purchase succeeded");

        return coupon;
    }

    //---------------------------------get all coupons by customer id----------------------
    @Transactional
    public List<Long> getCustomerCoupons(Long customerId) throws ApplicationException {

        if (!customerRepository.existsById(customerId)) {
            throw new ApplicationException(DATA_NOT_FOUND);
        }

        List<Long> couponsIdPurchasedByCustomer = customerRepository.getCustomerCouponsId(customerId);

        if (couponsIdPurchasedByCustomer.isEmpty()) {
            throw new ApplicationException(DATA_NOT_FOUND);
        }

        return couponsIdPurchasedByCustomer;
        //מחזיר רק זיהוי של הקופון ולא רשימת קופונים, נותן שגיאה כשמנסים להדפיס רשימת קופונים
    }

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
