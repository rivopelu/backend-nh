package com.lewatihari.controllers.impl;

import com.lewatihari.advices.BaseControllerImpl;
import com.lewatihari.controllers.AuthController;
import com.lewatihari.models.request.RequestSignIn;
import com.lewatihari.models.request.RequestSignUp;
import com.lewatihari.models.response.BaseResponse;
import com.lewatihari.services.AuthService;
import com.lewatihari.utils.ResponseHelper;
import lombok.AllArgsConstructor;

@BaseControllerImpl
@AllArgsConstructor
public class AuthControllerImpl implements AuthController {
    private final AuthService authService;

    @Override
    public BaseResponse signUp(RequestSignUp req) {
        return ResponseHelper.createBaseResponse(authService.signUp(req));
    }

    @Override
    public BaseResponse signInUser(RequestSignIn req) {
        return ResponseHelper.createBaseResponse(authService.signIn(req));
    }

    @Override
    public BaseResponse signInAdmin(RequestSignIn req) {
        return ResponseHelper.createBaseResponse(authService.signInAdmin(req));
    }
}
