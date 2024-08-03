package com.lewatihari.services.impl;

import com.lewatihari.entities.Category;
import com.lewatihari.entities.Merchant;
import com.lewatihari.entities.repositories.CategoryRepository;
import com.lewatihari.entities.repositories.MerchantImageRepository;
import com.lewatihari.entities.repositories.MerchantRepository;
import com.lewatihari.enums.ResponseEnum;
import com.lewatihari.exceptions.BadRequestException;
import com.lewatihari.exceptions.SystemErrorException;
import com.lewatihari.models.request.RequestNewMerchant;
import com.lewatihari.services.AccountService;
import com.lewatihari.services.MasterDataService;
import com.lewatihari.services.MerchantService;
import com.lewatihari.utils.EntityUtils;
import com.lewatihari.utils.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MerchantServiceImpl implements MerchantService {
    private final MerchantRepository merchantRepository;
    private final MerchantImageRepository merchantImageRepository;
    private final CategoryRepository categoryRepository;
    private final MasterDataService masterDataService;
    private final AccountService accountService;

    @Override
    public RequestNewMerchant createNewMerchantByAdmin(RequestNewMerchant req) {
        String slug = StringUtils.generateSlug(req.getName());
        boolean checkMerchantExisted = merchantRepository.existsBySlugAndActiveTrue(slug);
        if (checkMerchantExisted) {
            throw new BadRequestException(ResponseEnum.MERCHANT_ALREADY_EXIST);
        }
        Category category = masterDataService.getCategoryById(req.getCategoryId());
        Merchant merchant = Merchant.builder()
                .name(req.getName())
                .slug(slug)
                .logo(req.getLogo())
                .mainImage(req.getMainImage())
                .provinceId(req.getProvinceId())
                .cityId(req.getCityId())
                .districtId(req.getDistrictId())
                .subDistrictId(req.getSubDistrictId())
                .lat(req.getLat())
                .lng(req.getLng())
                .category(category)
                .build();
        EntityUtils.created(merchant, accountService.getCurrentAccountId());
        Merchant saveMerchant = merchantRepository.save(merchant);
        masterDataService.saveMerchantFacilities(req.getFacilityId(), saveMerchant);
        masterDataService.saveMultipleMerchantImages(req.getImages(), saveMerchant);
        try {
            return null;
        } catch (Exception e) {
            throw new SystemErrorException(e.getMessage());
        }
    }
}
