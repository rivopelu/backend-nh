package com.lewatihari.services;

import com.lewatihari.entities.Account;

import java.util.Optional;

public interface AccountService {
    String generateAvatar(String name);
    Optional<Account> getCurrentAccount();
    Optional<Account> getCurrentAccount(String id);

    String getCurrentAccountId();
}
