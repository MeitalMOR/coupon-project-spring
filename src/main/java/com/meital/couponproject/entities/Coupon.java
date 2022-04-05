package com.meital.couponproject.entities;

import com.meital.couponproject.enums.CouponCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "coupons")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class Coupon {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @ManyToOne
    @Column(name = "company_id", nullable = false)
    private Company companyId;

    @Enumerated(EnumType.STRING)
    @Column(name = "category", nullable = false)
    private CouponCategory category;

    @Column(name = "title", nullable = false, unique = true)
    private String title;

    @Column(name = "description")
    private String description;

    @Temporal(TemporalType.DATE)
    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @Column(name = "amount", nullable = false)
    private Integer amount;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "image")
    private String image;

    public Coupon(Company companyId, CouponCategory category, String title,
                  String description, LocalDate startDate, LocalDate endDate,
                  Integer amount, Double i_price, String j_image) {
        this.companyId = companyId;
        this.category = category;
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.amount = amount;
        this.price = i_price;
        this.image = j_image;
    }
}

