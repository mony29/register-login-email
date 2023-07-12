package com.example.register_login_jwt.controller;

import com.example.register_login_jwt.exception.UnauthorizedExceptionHandler;
import com.example.register_login_jwt.model.entity.UserApp;
import com.example.register_login_jwt.model.request.OrganizationRequest;
import com.example.register_login_jwt.response.BodyResponse;
import com.example.register_login_jwt.service.OrganizationService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/organizations")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class OrganizationController {
    private final OrganizationService organizationService;
    @PostMapping
    public ResponseEntity<?> createOrganization(@RequestBody OrganizationRequest organizationRequest) {
        UUID getId = organizationService.createOrganization(organizationRequest);
        return BodyResponse.getBodyResponse(organizationService.getOrganizationById(getId));
    }
}
