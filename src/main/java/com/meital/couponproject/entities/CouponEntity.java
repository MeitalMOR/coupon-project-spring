package com.meital.couponproject.entities;

import com.meital.couponproject.Enum.CouponCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "coupons")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class CouponEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long a_id;

    @Column(name = "company_id", nullable = false)
    private Long b_companyId;

    @Enumerated(EnumType.STRING)
    @Column(name = "category", nullable = false)
    private CouponCategory c_category;

    @Column(name = "title", nullable = false, unique = true)
    private String d_title;

    @Column(name = "description")
    private String e_description;

    @Temporal(TemporalType.DATE)
    @Column(name = "start_date", nullable = false)
    private Date f_startDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "end_date", nullable = false)
    private Date g_endDate;

    @Column(name = "amount", nullable = false)
    private Integer h_amount;

    @Column(name = "price", nullable = false)
    private Double i_price;

    @Column(name = "image")
    private String j_image;

    public CouponEntity(Long b_companyId, CouponCategory c_category, String d_title, String e_description,
                        Date f_startDate, Date g_endDate, Integer h_amount, Double i_price, String j_image) {
        this.b_companyId = b_companyId;
        this.c_category = c_category;
        this.d_title = d_title;
        this.e_description = e_description;
        this.f_startDate = f_startDate;
        this.g_endDate = g_endDate;
        this.h_amount = h_amount;
        this.i_price = i_price;
        this.j_image = j_image;
    }
}
