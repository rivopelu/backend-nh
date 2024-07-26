package com.lewatihari.controllers.impl;

import com.lewatihari.advices.BaseControllerImpl;
import com.lewatihari.controllers.AuthController;
import com.lewatihari.models.request.RequestSignUp;
import com.lewatihari.models.response.BaseResponse;

@BaseControllerImpl
public class AuthControllerImpl implements AuthController {
    @Override
    public BaseResponse signUp(RequestSignUp req) {
        return null;
    }
}
