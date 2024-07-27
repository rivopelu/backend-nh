package com.lewatihari.services;

import com.lewatihari.models.response.ResponseNameId;

import java.math.BigInteger;
import java.util.List;

public interface AreaService {

    List<ResponseNameId> getListAllProvince();

    List<ResponseNameId> getListCityByProvinceId(BigInteger provinceId);

    List<ResponseNameId> getListDistrictByCityId(BigInteger cityId);

    List<ResponseNameId> getListSubDistrictByDistrict(BigInteger districtId);
}
