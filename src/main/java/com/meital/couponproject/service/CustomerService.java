package com.meital.couponproject.service;

import com.meital.couponproject.entities.Coupon;
import com.meital.couponproject.entities.Customer;
import com.meital.couponproject.enums.CouponCategory;
import com.meital.couponproject.exceptions.ApplicationException;
import com.meital.couponproject.repo.CouponRepo;
import com.meital.couponproject.repo.CustomerRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static com.meital.couponproject.enums.ErrorType.*;

@Log4j2
@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CouponRepo couponRepo;
    private final CustomerRepo customerRepo;

    //-------------------------------------------purchase coupon ----------------------------
    @Transactional
    public void purchaseCoupon(Long customerId, Long couponId) throws ApplicationException {

        Optional<Customer> customerOpt = customerRepo.findById(customerId);
        Coupon coupon = couponRepo.getById(couponId);

        //check if customer exist
        if (customerOpt.isEmpty()) {
            throw new ApplicationException(CUSTOMER_DOES_NOT_EXISTS);
        }

        //check if coupon exist
        if (!customerRepo.existsById(customerId)) {
            throw new ApplicationException(COUPON_DOES_NOT_EXIST);
        }

        //check if coupon is out of stock
        if (coupon.getAmount() <= 0) {
            throw new ApplicationException(COUPON_OUT_OF_STOCK);
        }

        //check if coupon is expired
        if (coupon.getEndDate().isBefore(LocalDate.now())) {
            throw new ApplicationException(COUPON_HAS_EXPIRED);
        }

        //update coupon amount
        coupon.setAmount(coupon.getAmount() - 1);

        List<Coupon> coupons = couponRepo.getCouponsByCustomersId(customerId);
        //Add coupon to coupon list
        coupons.add(coupon);
        //set coupons to customer
        customerOpt.get().setCoupons(coupons);
        //purchase coupon
        customerRepo.save(customerOpt.get());

        log.info("\033[0;33m" + "Succeeded purchase coupon" + "\033[0m");
    }

    //-------------------------------------get customer coupons ----------------------------
    @Transactional
    public List<Coupon> getCustomerCoupons(Long customerId) throws ApplicationException {
        //check if customer exist
        if (!customerRepo.existsById(customerId)) {
            throw new ApplicationException(CUSTOMER_DOES_NOT_EXISTS);
        }

        //check if the customer have coupons
        if (customerRepo.getById(customerId).getCoupons().isEmpty()) {
            throw new ApplicationException(COUPON_DOES_NOT_EXIST);
        }

        log.info("\033[0;33m" + "Succeeded get list of customer coupons" + "\033[0m");
        return couponRepo.getCouponsByCustomersId(customerId);
    }

    //-------------------------------------get customer coupons by coupon category ----------------------------
    @Transactional
    public List<Coupon> getCustomerCouponsByCategory(Long customerId, CouponCategory couponCategory) throws ApplicationException {
        //check if customer exist
        if (!customerRepo.existsById(customerId)) {
            throw new ApplicationException(CUSTOMER_DOES_NOT_EXISTS);
        }

        //check if the customer have coupons
        if (customerRepo.getById(customerId).getCoupons().isEmpty()) {
            throw new ApplicationException(COUPON_DOES_NOT_EXIST);
        }

        //get list of coupons by customer and category
        List<Coupon> couponsByCustomersIdAndCouponCategory = couponRepo.getCouponsByCustomersIdAndCategory(customerId, couponCategory);
        log.info("\033[0;33m" + "Succeeded get list of customer coupons by coupon category" + "\033[0m");

        return couponsByCustomersIdAndCouponCategory;
    }

    //-------------------------------------get customer coupons by max price ---------------------------
    @Transactional
    public List<Coupon> getCustomerCouponsByMaxPrice(Long customerId, Double maxPrice) throws ApplicationException {
        //check if customer exist
        if (!customerRepo.existsById(customerId)) {
            throw new ApplicationException(CUSTOMER_DOES_NOT_EXISTS);
        }

        //check if the customer have coupons
        if (customerRepo.getById(customerId).getCoupons().isEmpty()) {
            throw new ApplicationException(COUPON_DOES_NOT_EXIST);
        }

        //get list of coupons by customer and max price
        List<Coupon> couponsByCustomersIdAndMaxPrice = couponRepo.getCouponsByCustomersIdAndPriceLessThan(customerId, maxPrice);
        log.info("\033[0;33m" + "Succeeded get list of customer coupons by max price" + "\033[0m");

        return couponsByCustomersIdAndMaxPrice;
    }

    //------------------------get customer details-------------------------------------
    public Optional<Customer> getCustomerDetails(final long customerId) throws ApplicationException {

        //find customer by id using Optional
        Optional<Customer> customerOpt = customerRepo.findById(customerId);

        if (customerOpt.isEmpty()) {
            throw new ApplicationException(CUSTOMER_DOES_NOT_EXISTS);
        }

        log.info("\033[0;33m" + "Succeeded get customer details" + "\033[0m");
        return customerOpt;
    }


}
