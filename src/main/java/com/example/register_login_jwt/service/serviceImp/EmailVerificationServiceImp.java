package com.example.register_login_jwt.service.serviceImp;

import com.example.register_login_jwt.model.entity.EmailVerification;
import com.example.register_login_jwt.repository.EmailVerificationRepository;
import com.example.register_login_jwt.repository.UserAppRepository;
import com.example.register_login_jwt.service.EmailVerificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailVerificationServiceImp implements EmailVerificationService {
    private final EmailVerificationRepository emailVerificationRepository;
    private final UserAppRepository userAppRepository;
    @Override
    public EmailVerification getEmailVerificationByCode(String code) {
        EmailVerification getEmailVerification = emailVerificationRepository.getEmailVerificationByCode(code);
        if (!getEmailVerification.getIsVerified()) {
            emailVerificationRepository.updateIsVerified(code);
            userAppRepository.updateUserSetting(getEmailVerification.getUserId());
        }
        return emailVerificationRepository.getEmailVerificationByCode(code);
    }
}
