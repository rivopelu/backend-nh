package com.lewatihari.services.impl;

import com.lewatihari.enums.ResponseEnum;
import com.lewatihari.models.request.RequestSignUp;
import com.lewatihari.services.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    @Override
    public ResponseEnum signUp(RequestSignUp req) {
        return null;
    }
}
