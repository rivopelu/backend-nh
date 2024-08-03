package com.lewatihari.entities;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigInteger;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "merchant")
public class Merchant extends BaseEntity {
    @Column(name = "name")
    private String name;
    @Column(name = "slug")
    private String slug;
    @Column(name = "logo")
    private String logo;
    @Column(name = "main_image")
    private String MainImage;
    @Column(name = "province_id")
    private BigInteger provinceId;
    @Column(name = "city_id")
    private BigInteger cityId;
    @Column(name = "district_id")
    private BigInteger districtId;
    @Column(name = "sub_district_id")
    private BigInteger subDistrictId;
    @Column(name = "lat")
    private Double lat;
    @Column(name = "lng")
    private Double lng;


    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

}
