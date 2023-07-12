package com.example.register_login_jwt.model.dto;

import com.example.register_login_jwt.model.entity.Role;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrganizationDTO {
    private UUID id;
    private String name;
    private String code;
    private String address;
    private String logo;
    private String roleName;
    private Boolean isActive;
    private Boolean isMember;
    private String status;
    private Timestamp createdAt;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Role role;
    private List<UserAppDTO> users;
    private String userName;
    private String userImage;
    private Integer totalUser;
}
