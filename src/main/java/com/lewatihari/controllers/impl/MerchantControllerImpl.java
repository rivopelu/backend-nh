package com.lewatihari.controllers.impl;

import com.lewatihari.advices.BaseControllerImpl;
import com.lewatihari.controllers.MerchantController;
import com.lewatihari.models.request.RequestNewMerchant;
import com.lewatihari.models.response.BaseResponse;
import com.lewatihari.services.MerchantService;
import com.lewatihari.utils.ResponseHelper;
import lombok.RequiredArgsConstructor;

@BaseControllerImpl
@RequiredArgsConstructor
public class MerchantControllerImpl implements MerchantController {

    private final MerchantService merchantService;

    @Override
    public BaseResponse createNewMerchantByAdmin(RequestNewMerchant req) {
        return ResponseHelper.createBaseResponse(merchantService.createNewMerchantByAdmin(req));
    }

    @Override
    public BaseResponse getDetailMerchantBySlug(String slug) {
        return ResponseHelper.createBaseResponse(merchantService.getDetailMerchantBySlug(slug));
    }
}
