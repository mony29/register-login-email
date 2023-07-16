package com.example.register_login_jwt.controller;

import com.example.register_login_jwt.model.request.UserAppRequest;
import com.example.register_login_jwt.model.request.AuthRequest;
import com.example.register_login_jwt.model.response.BodyResponse;
import com.example.register_login_jwt.service.AuthService;
import com.example.register_login_jwt.service.EmailVerificationService;
import com.example.register_login_jwt.service.serviceImp.UserAppServiceImp;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class AuthController {
    private final UserAppServiceImp userAppServiceImp;
    private final EmailVerificationService emailVerificationService;
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserAppRequest userAppRequest) throws MessagingException {
        return BodyResponse.getBodyResponse(userAppServiceImp.register(userAppRequest));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable("userId") UUID userId) {
        return BodyResponse.getBodyResponse(userAppServiceImp.getUserById(userId));
    }

    @GetMapping("/verify")
    public ResponseEntity<?> verifyEmail(@RequestParam("code") String code) {
        return BodyResponse.getBodyResponse(emailVerificationService.getEmailVerificationByCode(code));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
        return BodyResponse.getBodyResponse(authService.authenticate(authRequest));
    }
}
