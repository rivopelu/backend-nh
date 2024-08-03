package com.lewatihari.controllers;

import com.lewatihari.advices.BaseController;
import com.lewatihari.models.request.RequestNewMerchant;
import com.lewatihari.models.response.BaseResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@BaseController
public interface MerchantController {

    @PostMapping("/admin/v1/merchant/new")
    BaseResponse createNewMerchantByAdmin(@RequestBody RequestNewMerchant req);
}
