package com.example.register_login_jwt.service;

import com.example.register_login_jwt.model.dto.OrganizationDTO;
import com.example.register_login_jwt.model.entity.Organization;
import com.example.register_login_jwt.model.request.OrganizationRequest;

import java.util.List;
import java.util.UUID;

public interface OrganizationService {
    UUID createOrganization(OrganizationRequest organizationRequest);
    OrganizationDTO getOrganizationById(UUID organizationId);
    List<OrganizationDTO> getAllOrganization();
}
