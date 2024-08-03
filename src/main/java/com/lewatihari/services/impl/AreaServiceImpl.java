package com.lewatihari.services.impl;

import com.lewatihari.entities.*;
import com.lewatihari.entities.repositories.CityRepository;
import com.lewatihari.entities.repositories.DistrictRepository;
import com.lewatihari.entities.repositories.ProvinceRepository;
import com.lewatihari.entities.repositories.SubDistrictRepository;
import com.lewatihari.exceptions.NotFoundException;
import com.lewatihari.exceptions.SystemErrorException;
import com.lewatihari.models.response.ResponseNameId;
import com.lewatihari.services.AreaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Override
    public List<ResponseNameId> getAreaMerchant(Merchant merchant) {
        Optional<Province> province = provinceRepository.findById(merchant.getProvinceId());
        if (province.isEmpty()) {
            throw new NotFoundException("Province not found");
        }
        Optional<City> city = cityRepository.findById(merchant.getCityId());
        if (city.isEmpty()) {
            throw new NotFoundException("City not found");
        }
        Optional<District> district = districtRepository.findById(merchant.getDistrictId());
        if (district.isEmpty()) {
            throw new NotFoundException("District not found");
        }
        Optional<SubDistrict> subDistrict = subDistrictRepository.findById(merchant.getSubDistrictId());
        if (subDistrict.isEmpty()) {
            throw new NotFoundException("District not found");
        }
        try {
            List<ResponseNameId> responseNameIds = new ArrayList<>();
            responseNameIds.add(ResponseNameId.builder().name(province.get().getName()).id(province.get().getId()).build());
            responseNameIds.add(ResponseNameId.builder().name(city.get().getName()).id(city.get().getId()).build());
            responseNameIds.add(ResponseNameId.builder().name(district.get().getName()).id(district.get().getId()).build());
            responseNameIds.add(ResponseNameId.builder().name(subDistrict.get().getName()).id(subDistrict.get().getId()).build());
            return responseNameIds;
        } catch (Exception e) {
            throw new SystemErrorException(e.getMessage());
        }
    }

    @Override
    public String getFullAddressMerchant(Merchant merchant) {
        try {
            Optional<Province> province = provinceRepository.findById(merchant.getProvinceId());
            if (province.isEmpty()) {
                throw new NotFoundException("Province not found");
            }
            Optional<City> city = cityRepository.findById(merchant.getCityId());
            if (city.isEmpty()) {
                throw new NotFoundException("City not found");
            }
            Optional<District> district = districtRepository.findById(merchant.getDistrictId());
            if (district.isEmpty()) {
                throw new NotFoundException("District not found");
            }
            Optional<SubDistrict> subDistrict = subDistrictRepository.findById(merchant.getSubDistrictId());
            if (subDistrict.isEmpty()) {
                throw new NotFoundException("District not found");
            }
            return subDistrict.get().getName() + ", " +
                    district.get().getName() + ", " +
                    city.get().getName() + ", " +
                    province.get().getName();
        } catch (NotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new SystemErrorException(e.getMessage());
        }
    }
}
