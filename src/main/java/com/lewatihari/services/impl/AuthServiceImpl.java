package com.lewatihari.services.impl;

import com.lewatihari.entities.Account;
import com.lewatihari.enums.ResponseEnum;
import com.lewatihari.enums.SignUpTypeEnums;
import com.lewatihari.enums.UserRole;
import com.lewatihari.exceptions.BadRequestException;
import com.lewatihari.exceptions.SystemErrorException;
import com.lewatihari.models.request.RequestSignIn;
import com.lewatihari.models.request.RequestSignUp;
import com.lewatihari.models.response.ResponseSignIn;
import com.lewatihari.entities.repositories.AccountRepository;
import com.lewatihari.services.AccountService;
import com.lewatihari.services.AuthService;
import com.lewatihari.services.JwtService;
import com.lewatihari.utils.EntityUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final AccountRepository accountRepository;
    private final AccountService accountService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthServiceImpl(AccountRepository accountRepository, AccountService accountService, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.accountRepository = accountRepository;
        this.accountService = accountService;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public ResponseEnum signUp(RequestSignUp req) {
        String avatar;
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

    @Override
    public ResponseSignIn signIn(RequestSignIn req) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(req.getEmail(), req.getPassword()));
        try {
            if (!authentication.isAuthenticated()) {
                throw new BadRequestException(ResponseEnum.SIGN_IN_FAILED);
            }
            var user = accountRepository.findByEmailAndActiveTrue(req.getEmail()).orElseThrow();
            return buildSignIn(user);
        } catch (Exception e) {
            throw new SystemErrorException(e.getMessage());
        }
    }

    @Override
    public ResponseSignIn signInAdmin(RequestSignIn req) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(req.getEmail(), req.getPassword()));
        try {
            if (!authentication.isAuthenticated()) {
                throw new BadRequestException(ResponseEnum.SIGN_IN_FAILED);
            }

            var user = accountRepository.findByEmailAndActiveTrue(req.getEmail()).orElseThrow();
            if (user.getRole() != UserRole.ADMIN) {
                throw new BadRequestException(ResponseEnum.SIGN_IN_FAILED);
            }
            return buildSignIn(user);
        } catch (Exception e) {
            throw new SystemErrorException(e.getMessage());
        }
    }

    private ResponseSignIn buildSignIn(Account account) {
        var jwtToken = jwtService.generateToken(account);
        return ResponseSignIn.builder().accessToken(jwtToken).build();
    }
}
