package com.lewatihari.services;

import com.lewatihari.enums.ResponseEnum;
import com.lewatihari.models.request.RequestName;
import com.lewatihari.models.response.ResponseNameId;

import java.util.List;

public interface MasterDataService {
    ResponseEnum createNewCategory(RequestName requestName);

    List<ResponseNameId> getListCategory();
}
