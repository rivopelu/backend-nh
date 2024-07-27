package com.lewatihari.controllers.impl;

import com.lewatihari.advices.BaseControllerImpl;
import com.lewatihari.controllers.AreaController;
import com.lewatihari.models.response.BaseResponse;
import com.lewatihari.services.AreaService;
import com.lewatihari.utils.ResponseHelper;
import lombok.RequiredArgsConstructor;

@BaseControllerImpl
@RequiredArgsConstructor

public class AreaControllerImpl implements AreaController {
    private final AreaService areaService;

    @Override
    public BaseResponse getListAllProvince() {
        return ResponseHelper.createBaseResponse(areaService.getListAllProvince());
    }
}
