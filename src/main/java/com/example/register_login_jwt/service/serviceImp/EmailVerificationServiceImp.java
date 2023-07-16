package com.example.register_login_jwt.service.serviceImp;

import com.example.register_login_jwt.model.entity.EmailVerification;
import com.example.register_login_jwt.repository.EmailVerificationRepository;
import com.example.register_login_jwt.repository.UserAppRepository;
import com.example.register_login_jwt.service.EmailVerificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmailVerificationServiceImp implements EmailVerificationService {
    private final EmailVerificationRepository emailVerificationRepository;
    private final UserAppRepository userAppRepository;

    @Override
    public EmailVerification getEmailVerificationByUserId(UUID userId) {
        return emailVerificationRepository.getEmailVerificationByUserId(userId);
    }
    @Override
    public EmailVerification getEmailVerificationByCode(String code) {
        EmailVerification getEmailVerification =  emailVerificationRepository.getEmailVerificationByCode(code);

        System.out.println("Get Email Verification : " + getEmailVerification);

        if(!getEmailVerification.getIsVerified()) {
            emailVerificationRepository.updateIsVerified(code);
            userAppRepository.updateUserSetting(getEmailVerification.getUserId());
        }

        System.out.println("Hiiiii : " + emailVerificationRepository);
        return emailVerificationRepository.getEmailVerificationByCode(code);
    }

}
