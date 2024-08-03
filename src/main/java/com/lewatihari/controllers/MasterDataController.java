package com.lewatihari.controllers;

import com.lewatihari.advices.BaseController;
import com.lewatihari.annotations.AdminAccess;
import com.lewatihari.models.request.RequestName;
import com.lewatihari.models.response.BaseResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@BaseController
public interface MasterDataController {

    @AdminAccess
    @PostMapping("admin/master-data/v1/category/new")
    BaseResponse createNewCategory(@RequestBody RequestName requestName);

    @GetMapping("/public/master-data/v1/category/list")
    BaseResponse getListCategory();

    @GetMapping("/public/master-data/v1/facility/list")
    BaseResponse getListFacility();
}
