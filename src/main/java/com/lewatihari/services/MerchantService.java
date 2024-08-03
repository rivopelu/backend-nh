package com.lewatihari.services;

import com.lewatihari.models.request.RequestNewMerchant;

public interface MerchantService {
    RequestNewMerchant createNewMerchantByAdmin(RequestNewMerchant req);
}
