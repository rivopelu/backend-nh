package com.lewatihari.services;

import com.lewatihari.enums.ResponseEnum;
import com.lewatihari.models.request.RequestName;

public interface MasterDataService {
    ResponseEnum createNewCategory(RequestName requestName);
}
