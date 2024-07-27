package com.lewatihari.controllers;

import com.lewatihari.advices.BaseController;
import com.lewatihari.models.response.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigInteger;

@BaseController("area")
public interface AreaController {


    @GetMapping("v1/list/province")
    BaseResponse getListAllProvince();

    @GetMapping("v1/list/city/{provinceId}")
    BaseResponse getListCityByProvinceId(@PathVariable("provinceId") BigInteger provinceId);

    @GetMapping("v1/list/district/{cityId}")
    BaseResponse getListDistrictByCityId(@PathVariable("cityId") BigInteger cityId);

    @GetMapping("v1/list/sub-district/{districtId}")
    BaseResponse getListSubDistrictByDistrictId(@PathVariable("districtId") BigInteger districtId);

}
