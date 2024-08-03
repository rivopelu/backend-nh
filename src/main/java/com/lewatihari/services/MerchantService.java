package com.lewatihari.services;

import com.lewatihari.models.request.RequestNewMerchant;
import com.lewatihari.models.response.ResponseDetailMerchant;

public interface MerchantService {
    RequestNewMerchant createNewMerchantByAdmin(RequestNewMerchant req);

    ResponseDetailMerchant getDetailMerchantBySlug(String slug);
}
