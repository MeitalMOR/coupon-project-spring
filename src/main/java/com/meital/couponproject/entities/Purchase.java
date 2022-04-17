package com.meital.couponproject.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "purchases")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private Coupon coupon;


}

