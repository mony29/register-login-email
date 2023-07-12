package com.example.register_login_jwt.model.dto;

import com.example.register_login_jwt.model.entity.Organization;
import com.example.register_login_jwt.model.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.annotations.Mapper;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrganizationDetailDTO {
    private UUID userId;
    private Boolean isActive;
    private Timestamp createdAt;
    private UUID createdBy;
    private Organization organization;
    private Role role;
}