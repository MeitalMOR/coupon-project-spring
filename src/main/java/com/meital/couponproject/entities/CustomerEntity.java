package com.meital.couponproject.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name = "customers")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class CustomerEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long a_id;

    @Column(name = "first_name", nullable = false)
    private String b_firstName;

    @Column(name = "last_name", nullable = false)
    private String c_lastName;

    @Column(name = "email", nullable = false, unique = true)
    private String d_email;

    @Column(name = "password", nullable = false)
    private String e_password;

    @Transient
    private ArrayList<CouponEntity> coupons;

    public CustomerEntity(Long a_id, String b_firstName, String c_lastName, String d_email, String e_password) {
        this.a_id = a_id;
        this.b_firstName = b_firstName;
        this.c_lastName = c_lastName;
        this.d_email = d_email;
        this.e_password = e_password;
    }

    public CustomerEntity(String b_firstName, String c_lastName, String d_email, String e_password) {
        this.b_firstName = b_firstName;
        this.c_lastName = c_lastName;
        this.d_email = d_email;
        this.e_password = e_password;
    }
}
