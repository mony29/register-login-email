package com.example.register_login_jwt.service;

import com.example.register_login_jwt.model.entity.EmailVerification;

public interface EmailVerificationService {
    EmailVerification getEmailVerificationByCode(String code);
}
