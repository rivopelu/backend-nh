package com.lewatihari.services.impl;

import com.lewatihari.entities.City;
import com.lewatihari.entities.District;
import com.lewatihari.entities.Province;
import com.lewatihari.entities.SubDistrict;
import com.lewatihari.entities.repositories.CityRepository;
import com.lewatihari.entities.repositories.DistrictRepository;
import com.lewatihari.entities.repositories.ProvinceRepository;
import com.lewatihari.entities.repositories.SubDistrictRepository;
import com.lewatihari.exceptions.SystemErrorException;
import com.lewatihari.models.response.ResponseNameId;
import com.lewatihari.services.AreaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AreaServiceImpl implements AreaService {
    private final ProvinceRepository provinceRepository;
    private final CityRepository cityRepository;
    private final DistrictRepository districtRepository;
    private final SubDistrictRepository subDistrictRepository;

    @Override
    public List<ResponseNameId> getListAllProvince() {
        try {
            List<Province> provinces = provinceRepository.findAll();
            List<ResponseNameId> responseNameIds = new ArrayList<>();
            for (Province province : provinces) {
                ResponseNameId responseNameId = ResponseNameId.builder()
                        .name(province.getName())
                        .id(province.getId())
                        .build();
                responseNameIds.add(responseNameId);
            }
            return responseNameIds;
        } catch (Exception e) {
            throw new SystemErrorException(e.getMessage());
        }
    }

    @Override
    public List<ResponseNameId> getListCityByProvinceId(BigInteger provinceId) {
        List<City> cities = cityRepository.findAllByProvinceId(provinceId);
        try {
            List<ResponseNameId> responseNameIds = new ArrayList<>();
            for (City city : cities) {
                ResponseNameId responseNameId = ResponseNameId.builder()
                        .name(city.getName())
                        .id(city.getId())
                        .build();
                responseNameIds.add(responseNameId);
            }
            return responseNameIds;
        } catch (Exception e) {
            throw new SystemErrorException(e.getMessage());
        }
    }

    @Override
    public List<ResponseNameId> getListDistrictByCityId(BigInteger cityId) {
        try {
            List<District> districts = districtRepository.findAllByCityId(cityId);
            List<ResponseNameId> responseNameIds = new ArrayList<>();
            for (District district : districts) {
                ResponseNameId responseNameId = ResponseNameId.builder()
                        .name(district.getName())
                        .id(district.getId())
                        .build();
                responseNameIds.add(responseNameId);
            }
            return responseNameIds;
        } catch (Exception e) {
            throw new SystemErrorException(e.getMessage());
        }
    }

    @Override
    public List<ResponseNameId> getListSubDistrictByDistrict(BigInteger districtId) {
        try {
            List<SubDistrict> subDistrictList = subDistrictRepository.findALlByDistrictId(districtId);
            List<ResponseNameId> responseNameIds = new ArrayList<>();
            for (SubDistrict subDistrict : subDistrictList) {
                ResponseNameId responseNameId = ResponseNameId.builder()
                        .name(subDistrict.getName())
                        .id(subDistrict.getId())
                        .build();
                responseNameIds.add(responseNameId);
            }
            return responseNameIds;
        } catch (Exception e) {
            throw new SystemErrorException(e.getMessage());
        }
    }
}
