package com.lewatihari.models.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonSerialize
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class RequestNewMerchant {
    private String name;
    private String logo;
    private String mainImage;
    private BigInteger provinceId;
    private BigInteger cityId;
    private BigInteger districtId;
    private BigInteger subDistrictId;
    private Double lat;
    private Double lng;
    private String categoryId;
    private List<String> images;
    private List<String> facilityId;
}
