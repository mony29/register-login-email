package com.example.register_login_jwt.service;

import com.example.register_login_jwt.model.entity.EmailVerification;

import java.util.UUID;

public interface EmailVerificationService {
    EmailVerification getEmailVerificationByCode(String code);
    EmailVerification getEmailVerificationByUserId(UUID userId);
}