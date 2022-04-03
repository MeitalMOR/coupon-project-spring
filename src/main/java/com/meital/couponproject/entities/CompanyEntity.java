package com.meital.couponproject.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name = "companies")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class CompanyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private long a_id;

    @Column(name = "name", nullable = false, unique = true)
    private String b_name;

    @Column(name = "email", nullable = false, unique = true)
    private String c_email;

    @Column(name = "password", nullable = false)
    private String d_password;

    @Transient
    private ArrayList<CouponEntity> coupons;

    public CompanyEntity(String b_name, String c_email, String d_password) {
        this.b_name = b_name;
        this.c_email = c_email;
        this.d_password = d_password;
    }

    public CompanyEntity(long a_id, String b_name, String c_email, String d_password) {
        this.a_id = a_id;
        this.b_name = b_name;
        this.c_email = c_email;
        this.d_password = d_password;
    }
}
