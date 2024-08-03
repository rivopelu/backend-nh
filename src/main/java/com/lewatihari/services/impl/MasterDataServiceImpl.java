package com.lewatihari.services.impl;

import com.lewatihari.entities.Category;
import com.lewatihari.entities.Facility;
import com.lewatihari.entities.repositories.CategoryRepository;
import com.lewatihari.entities.repositories.FacilityRepository;
import com.lewatihari.enums.ResponseEnum;
import com.lewatihari.exceptions.BadRequestException;
import com.lewatihari.exceptions.SystemErrorException;
import com.lewatihari.models.request.RequestName;
import com.lewatihari.models.response.ResponseMasterData;
import com.lewatihari.models.response.ResponseNameId;
import com.lewatihari.services.MasterDataService;
import com.lewatihari.utils.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MasterDataServiceImpl implements MasterDataService {
    private final CategoryRepository categoryRepository;
    private final FacilityRepository facilityRepository;

    @Override
    public ResponseEnum createNewCategory(RequestName requestName) {
        String slug = StringUtils.generateSlug(requestName.getName());
        boolean checkExists = categoryRepository.existsBySlug(slug);
        try {
            if (checkExists) {
                throw new BadRequestException(ResponseEnum.CATEGORY_ALREADY_EXIST);
            }
            Category category = Category.builder().name(requestName.getName()).slug(slug).build();
            categoryRepository.save(category);
            return ResponseEnum.SUCCESS;
        } catch (Exception e) {
            throw new SystemErrorException(e.getMessage());
        }
    }

    @Override
    public List<ResponseNameId> getListCategory() {
        try {
            List<Category> categories = categoryRepository.findAll();
            return categories.stream().map(category -> ResponseNameId.builder().id(category.getId()).name(category.getName()).build()).collect(Collectors.toList());
        } catch (Exception e) {
            throw new SystemErrorException(e.getMessage());
        }
    }

    @Override
    public List<ResponseMasterData> getListFacility() {
        try {
            List<Facility> facilities = facilityRepository.findAll();
            List<ResponseMasterData> responses = new ArrayList<>();
            for (Facility facility : facilities) {
                ResponseMasterData responseMasterData = ResponseMasterData.builder()
                        .name(facility.getName())
                        .id(facility.getId())
                        .slug(facility.getSlug())
                        .build();
                responses.add(responseMasterData);
            }
            return responses;
        } catch (Exception e) {
            throw new SystemErrorException(e.getMessage());
        }
    }
}
