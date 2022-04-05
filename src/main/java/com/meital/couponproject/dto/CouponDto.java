package com.meital.couponproject.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.meital.couponproject.enums.CouponCategory;
import com.meital.couponproject.entities.Company;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CouponDto {

    private Long id;

    private Company companyId;

    private CouponCategory category;

    private String title;

    private String description;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate startDate;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate endDate;

    private Integer amount;

    private Double price;

    private String image;
}
