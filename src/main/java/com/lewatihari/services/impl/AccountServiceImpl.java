package com.lewatihari.services.impl;

import com.lewatihari.entities.Account;
import com.lewatihari.entities.BaseEntity;
import com.lewatihari.entities.repositories.AccountRepository;
import com.lewatihari.services.AccountService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public String generateAvatar(String name) {
        return "https://ui-avatars.com/api/?background=random&name=" + name;
    }

    @Override
    public Optional<Account> getCurrentAccount() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            UserDetails connectedUser = (UserDetails) authentication.getPrincipal();
            return accountRepository.findByEmailAndActiveTrue(connectedUser.getUsername());
        }
        return Optional.empty();
    }

    @Override
    public Optional<Account> getCurrentAccount(String id) {
        return accountRepository.findById(id);
    }

    @Override
    public String getCurrentAccountId() {
        Optional<Account> findAccount = getCurrentAccount();
        return findAccount.map(BaseEntity::getId).orElse(null);
    }

}
