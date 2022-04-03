package com.meital.couponproject.entities;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name = "companies")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Transient
    private ArrayList<Coupon> coupons;

    public Company(String name, String email, String password, ArrayList<Coupon> coupons) {

    }
}
