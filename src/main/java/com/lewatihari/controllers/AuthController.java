package com.lewatihari.controllers;

import com.lewatihari.advices.BaseController;
import com.lewatihari.models.request.RequestSignUp;
import com.lewatihari.models.response.BaseResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@BaseController("/auth")
public interface AuthController {

    @PostMapping("v1/sign-up")
    BaseResponse signUp(@RequestBody RequestSignUp req);
}
