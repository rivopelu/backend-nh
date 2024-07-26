package com.lewatihari.services.impl;

import com.lewatihari.services.AccountService;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {
    @Override
    public String generateAvatar(String name) {
        return "https://ui-avatars.com/api/?background=random&name=" + name;
    }
}
