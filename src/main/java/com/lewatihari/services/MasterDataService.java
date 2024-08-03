package com.lewatihari.services;

import com.lewatihari.entities.Category;
import com.lewatihari.entities.Merchant;
import com.lewatihari.enums.ResponseEnum;
import com.lewatihari.models.request.RequestName;
import com.lewatihari.models.response.ResponseMasterData;
import com.lewatihari.models.response.ResponseNameId;

import java.util.List;

public interface MasterDataService {
    ResponseEnum createNewCategory(RequestName requestName);

    List<ResponseNameId> getListCategory();

    List<ResponseMasterData> getListFacility();

    Category getCategoryById(String categoryId);

    void saveMultipleMerchantImages(List<String> imagesUrl, Merchant merchant);

    void saveMerchantFacilities(List<String> facilityId, Merchant merchant);

    List<ResponseMasterData> getMerchantFacilities(Merchant merchant);
    List<String> getImagesMerchant(Merchant merchant);
}
