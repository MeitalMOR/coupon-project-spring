package com.meital.couponproject.entities;

import lombok.*;
import javax.persistence.*;

@Entity
@Table(name = "purchases")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Purchase {


    @Id
    @GeneratedValue
    long id;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private Coupon coupon;

    //-----------------------------Constructors---------------------------------------

    public Purchase(Customer customer, Coupon coupon) {
        this.customer = customer;
        this.coupon = coupon;
    }
}

