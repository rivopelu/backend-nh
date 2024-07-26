package com.lewatihari.controllers;

import com.lewatihari.advices.BaseController;
import com.lewatihari.models.request.RequestSignIn;
import com.lewatihari.models.request.RequestSignUp;
import com.lewatihari.models.response.BaseResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@BaseController()
public interface AuthController {

    @PostMapping("/auth/v1/sign-up")
    BaseResponse signUp(@RequestBody RequestSignUp req);
    @PostMapping("/auth/v1/user/sign-in")
    BaseResponse signInUser(@RequestBody RequestSignIn req);
}
