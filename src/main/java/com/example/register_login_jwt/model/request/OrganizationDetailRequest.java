package com.example.register_login_jwt.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrganizationDetailRequest {
    private UUID userId;
    private String organizationName;
    private String organizationAddress;
    private String organizationLogo;
}
