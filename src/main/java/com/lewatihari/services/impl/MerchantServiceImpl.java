package com.lewatihari.services.impl;

import com.lewatihari.models.request.RequestNewMerchant;
import com.lewatihari.services.MerchantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MerchantServiceImpl implements MerchantService {
    @Override
    public RequestNewMerchant createNewMerchantByAdmin(RequestNewMerchant req) {
        return req;
    }
}
