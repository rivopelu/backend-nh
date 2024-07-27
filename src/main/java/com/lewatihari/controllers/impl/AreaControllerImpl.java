package com.lewatihari.controllers.impl;

import com.lewatihari.advices.BaseControllerImpl;
import com.lewatihari.controllers.AreaController;
import com.lewatihari.models.response.BaseResponse;
import com.lewatihari.services.AreaService;
import com.lewatihari.utils.ResponseHelper;
import lombok.RequiredArgsConstructor;

import java.math.BigInteger;

@BaseControllerImpl
@RequiredArgsConstructor

public class AreaControllerImpl implements AreaController {
    private final AreaService areaService;
    private final AreaService area;

    @Override
    public BaseResponse getListAllProvince() {
        return ResponseHelper.createBaseResponse(areaService.getListAllProvince());
    }

    @Override
    public BaseResponse getListCityByProvinceId(BigInteger provinceId) {
        return ResponseHelper.createBaseResponse(areaService.getListCityByProvinceId(provinceId));
    }

    @Override
    public BaseResponse getListDistrictByCityId(BigInteger cityId) {
        return ResponseHelper.createBaseResponse(areaService.getListDistrictByCityId(cityId));
    }

    @Override
    public BaseResponse getListSubDistrictByDistrictId(BigInteger districtId) {
        return ResponseHelper.createBaseResponse(areaService.getListSubDistrictByDistrict(districtId));
    }
}
