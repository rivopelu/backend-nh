package com.lewatihari.services;


import com.lewatihari.enums.ResponseEnum;
import com.lewatihari.models.request.RequestSignIn;
import com.lewatihari.models.request.RequestSignUp;
import com.lewatihari.models.response.ResponseSignIn;

public interface AuthService {
    ResponseEnum signUp(RequestSignUp req);

    ResponseSignIn signIn(RequestSignIn req);

    ResponseSignIn signInAdmin(RequestSignIn req);
}
