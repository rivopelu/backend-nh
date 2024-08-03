package com.lewatihari.services.impl;

import com.lewatihari.entities.*;
import com.lewatihari.entities.repositories.*;
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
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MasterDataServiceImpl implements MasterDataService {
    private final CategoryRepository categoryRepository;
    private final FacilityRepository facilityRepository;
    private final MerchantImageRepository merchantImageRepository;
    private final MerchantFacilitiesRepository merchantFacilitiesRepository;
    private final MerchantRepository merchantRepository;


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

    @Override
    public Category getCategoryById(String categoryId) {
        Optional<Category> category = categoryRepository.findById(categoryId);
        if (category.isEmpty()) {
            throw new BadRequestException(ResponseEnum.CATEGORY_NOT_FOUND);
        }
        try {
            return category.get();
        } catch (Exception e) {
            throw new SystemErrorException(e.getMessage());
        }
    }

    @Override
    public void saveMultipleMerchantImages(List<String> imagesUrl, Merchant merchant) {
        try {
            List<MerchantImage> merchantImages = new ArrayList<>();
            for (String url : imagesUrl) {
                MerchantImage merchantImage = MerchantImage.builder()
                        .url(url)
                        .merchant(merchant)
                        .build();
                merchantImages.add(merchantImageRepository.save(merchantImage));
            }
            merchantImageRepository.saveAll(merchantImages);
        } catch (Exception e) {
            merchantRepository.delete(merchant);
            throw new SystemErrorException(e.getMessage());
        }
    }

    @Override
    public void saveMerchantFacilities(List<String> facilityId, Merchant merchant) {
        List<MerchantFacilities> merchantFacilities = new ArrayList<>();
        try {
            for (String id : facilityId) {
                Optional<Facility> facility = facilityRepository.findById(id);
                if (facility.isEmpty()) {
                    merchantRepository.delete(merchant);
                    throw new BadRequestException(ResponseEnum.FACILITY_NOT_FOUND);
                }
                MerchantFacilities merchantFacility = MerchantFacilities.builder()
                        .facility(facility.get())
                        .merchant(merchant)
                        .build();
                merchantFacilities.add(merchantFacility);

            }
            merchantFacilitiesRepository.saveAll(merchantFacilities);
        } catch (Exception e) {
            throw new SystemErrorException(e.getMessage());
        }
    }
}
