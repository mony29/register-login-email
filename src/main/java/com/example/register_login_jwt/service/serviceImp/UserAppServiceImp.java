package com.example.register_login_jwt.service.serviceImp;

import com.example.register_login_jwt.exception.*;
import com.example.register_login_jwt.model.dto.UserAppDTO;
import com.example.register_login_jwt.model.entity.UserApp;
import com.example.register_login_jwt.model.mapper.UserAppMapper;
import com.example.register_login_jwt.model.request.UserAppRequest;
import com.example.register_login_jwt.repository.EmailVerificationRepository;
import com.example.register_login_jwt.repository.UserAppRepository;
import com.example.register_login_jwt.service.MailSenderService;
import com.example.register_login_jwt.service.UserAppService;
import lombok.RequiredArgsConstructor;
import jakarta.mail.MessagingException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserAppServiceImp implements UserAppService, UserDetailsService {
    private final UserAppRepository userAppRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private UserApp userApp = new UserApp();
    private UserAppDTO userAppDTO = new UserAppDTO();
    private final UserAppMapper userAppMapper;
    private final MailSenderService mailSenderService;
    private final EmailVerificationRepository emailVerificationRepository;

    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        UserApp userApp = userAppRepository.getUserByEmail(userEmail);
        if (userApp == null) {
            throw new NotFoundExceptionHandler("User not found");
        } else if (userEmail.isEmpty()) {
            throw new FieldEmptyExceptionHandler("Email field is empty");
        } else if (userEmail.isBlank()) {
            throw new FieldBlankExceptionHandler("Email field is blank");
        } else if (!userEmail.matches("^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-]+)(\\.[a-zA-Z]{2,5}){1,2}$")) {
            throw new NotValidValueExceptionHandler("Email not valid");
        }

        return userApp;
    }

    public UserAppDTO register(UserAppRequest userAppRequest) throws MessagingException {
        String email = userAppRepository.getEmail(userAppRequest.getEmail());

        if (userAppRequest.getEmail().equalsIgnoreCase(email)) {
            throw new UserDuplicateExceptionHandler("User already registered");
        } else {
            validateUserAppRequest(userAppRequest);
        }

        userAppRequest.setPassword(passwordEncoder.encode(userAppRequest.getPassword()));
        userApp = userAppRepository.insertUser(userAppRequest);
        userAppDTO = userAppMapper.INSTANCE.toUserAppDto(userApp);

        String code = UUID.randomUUID().toString();
        emailVerificationRepository.insertEmailVerification(userApp.getId(), code);
        mailSenderService.sendMail(userAppRequest, code);

        return userAppDTO;
    }

    private void validateUserAppRequest(UserAppRequest userAppRequest) {
        if (userAppRequest.getEmail().isEmpty()) {
            throw new FieldEmptyExceptionHandler("Email field is empty");
        } else if (userAppRequest.getEmail().isBlank()) {
            throw new FieldBlankExceptionHandler("Email field is blank");
        } else if (userAppRequest.getPassword().isEmpty()) {
            throw new FieldEmptyExceptionHandler("Password field is empty");
        } else if (userAppRequest.getPassword().isBlank()) {
            throw new FieldBlankExceptionHandler("Password field is blank");
        } else if (!userAppRequest.getEmail().matches("^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-]+)(\\.[a-zA-Z]{2,5}){1,2}$")) {
            throw new NotValidValueExceptionHandler("Email not valid");
        }
    }

    public UserAppDTO getUserById(UUID userId) {
        userApp = userAppRepository.getUserById(userId);
        if (userApp == null) {
            throw new NotFoundExceptionHandler("User not found");
        }
        return UserAppMapper.INSTANCE.toUserAppDto(userApp);
    }
}
