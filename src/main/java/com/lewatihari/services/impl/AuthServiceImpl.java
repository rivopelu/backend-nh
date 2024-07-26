package com.lewatihari.services.impl;

import com.lewatihari.entities.Account;
import com.lewatihari.enums.ResponseEnum;
import com.lewatihari.enums.SignUpTypeEnums;
import com.lewatihari.enums.UserRole;
import com.lewatihari.exceptions.BadRequestException;
import com.lewatihari.exceptions.SystemErrorException;
import com.lewatihari.models.request.RequestSignUp;
import com.lewatihari.repositories.AccountRepository;
import com.lewatihari.services.AccountService;
import com.lewatihari.services.AuthService;
import com.lewatihari.utils.EntityUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final AccountRepository accountRepository;
    private final AccountService accountService;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(AccountRepository accountRepository, AccountService accountService, PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.accountService = accountService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public ResponseEnum signUp(RequestSignUp req) {
        String avatar = null;
        boolean existByEmail = accountRepository.existsByEmailAndActiveTrue(req.getEmail());
        if (existByEmail) {
            throw new BadRequestException(ResponseEnum.EMAIL_ALREADY_EXIST);
        }
        avatar = accountService.generateAvatar(req.getName());
        if (req.getAvatar() != null) {
            avatar = req.getAvatar();
        }

        try {
            String password = passwordEncoder.encode(req.getPassword());
            Account account = Account.builder()
                    .name(req.getName())
                    .email(req.getEmail())
                    .password(password)
                    .avatar(avatar)
                    .signUpType(SignUpTypeEnums.EMAIL)
                    .role(UserRole.USER)
                    .build();
            EntityUtils.created(account, "SYSTEM");
            accountRepository.save(account);
            return ResponseEnum.SUCCESS;
        } catch (Exception e) {
            throw new SystemErrorException(e.getMessage());
        }
    }
}
