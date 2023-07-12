package com.example.register_login_jwt.service;

import com.example.register_login_jwt.config.JwtTokenUtil;
import com.example.register_login_jwt.exception.*;
import com.example.register_login_jwt.model.entity.UserApp;
import com.example.register_login_jwt.model.request.AuthRequest;
import com.example.register_login_jwt.repository.UserAppRepository;
import com.example.register_login_jwt.response.AuthResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final JwtTokenUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final UserAppRepository userAppRepository;

    public AuthResponse authenticate(AuthRequest authRequest) {

        UserApp userApp = userAppRepository.getUserByEmail(authRequest.getEmail());

        if (userApp == null) {
            throw new NotFoundExceptionHandler("User not found");
        } else if (authRequest.getEmail().isEmpty()) {
            throw new FieldEmptyExceptionHandler("Email field is empty");
        } else if (authRequest.getEmail().isBlank()) {
            throw new FieldBlankExceptionHandler("Email field is blank");
        } else if (!authRequest.getEmail().matches("^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-]+)(\\.[a-zA-Z]{2,5}){1,2}$")) {
            throw new NotValidValueExceptionHandler("Email not valid");
        } else if (!userApp.getIsEnabled()) {
            throw new IsEnableExceptionHandler();
        } else if (!userApp.getIsAccountNonLocked()) {
            throw new IsAccountNonLockedExceptionHandler();
        } else if (!userApp.getIsAccountNonExpired()) {
            throw new IsAccountNonExpiredExceptionHandler();
        }

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authRequest.getEmail(),
                            authRequest.getPassword()
                    )
            );

            var user = userAppRepository.getUserByEmail(authRequest.getEmail());
            var jwtToken = jwtUtil.generateToken(user);

            return AuthResponse.builder()
                    .id(user.getId())
                    .email(user.getEmail())
                    .token(jwtToken)
                    .build();
        } catch (Exception e) {
            System.out.println(e);
            throw new PasswordNotMatchExceptionHandler("Password not match");
        }
    }
}
