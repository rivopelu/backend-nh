package com.lewatihari.controllers;

import com.lewatihari.advices.BaseController;
import com.lewatihari.models.response.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

@BaseController("area")
public interface AreaController {



    @GetMapping("v1/list/province")
    BaseResponse getListAllProvince();

}
