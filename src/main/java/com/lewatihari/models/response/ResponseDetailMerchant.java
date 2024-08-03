package com.lewatihari.models.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lewatihari.enums.MerchantStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonSerialize
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ResponseDetailMerchant {
    private String id;
    private String name;
    private String slug;
    private String fullAddress;
    private String logo;
    private String mainImage;
    private List<ResponseMasterData> facilities;
    private List<String> images;
    private List<ResponseNameId> area;
    private Double lat;
    private Double lng;
    private MerchantStatusEnum status;

}
