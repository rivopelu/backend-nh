package com.lewatihari.controllers.impl;

import com.lewatihari.advices.BaseControllerImpl;
import com.lewatihari.controllers.MasterDataController;
import com.lewatihari.models.request.RequestName;
import com.lewatihari.models.response.BaseResponse;
import com.lewatihari.services.MasterDataService;
import com.lewatihari.utils.ResponseHelper;
import lombok.RequiredArgsConstructor;

@BaseControllerImpl
@RequiredArgsConstructor
public class MasterDataControllerImpl implements MasterDataController {
    private final MasterDataService masterDataService;

    @Override
    public BaseResponse createNewCategory(RequestName requestName) {
        return ResponseHelper.createBaseResponse(masterDataService.createNewCategory(requestName));
    }

    @Override
    public BaseResponse getListCategory() {
        return ResponseHelper.createBaseResponse(masterDataService.getListCategory());
    }
}
