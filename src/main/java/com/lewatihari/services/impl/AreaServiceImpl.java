package com.lewatihari.services.impl;

import com.lewatihari.entities.Province;
import com.lewatihari.entities.repositories.ProvinceRepository;
import com.lewatihari.exceptions.SystemErrorException;
import com.lewatihari.models.response.ResponseNameId;
import com.lewatihari.services.AreaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AreaServiceImpl implements AreaService {
    private final ProvinceRepository provinceRepository;

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
}
