package com.lewatihari.services;


import com.lewatihari.enums.ResponseEnum;
import com.lewatihari.models.request.RequestSignUp;

public interface AuthService {
    ResponseEnum signUp(RequestSignUp req);
}
