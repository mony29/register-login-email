package com.example.register_login_jwt.service;

import com.example.register_login_jwt.model.dto.UserAppDTO;
import com.example.register_login_jwt.model.request.UserAppRequest;
import com.example.register_login_jwt.model.response.AuthResponse;
import jakarta.mail.MessagingException;

import java.util.List;
import java.util.UUID;

public interface UserAppService {
    UserAppDTO register(UserAppRequest userAppRequest) throws MessagingException;
    UserAppDTO getUserById(UUID userId);

}
