package com.example.register_login_jwt.model.entity;

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
public class Organization {
    private UUID id;
    private String name;
    private String code;
    private String address;
    private String logo;
    private String roleName;
    private Boolean isActive;
    private Timestamp createdAt;
    private UUID createdBy;
    private Timestamp updatedAt;
    private UUID updatedBy;
    private Timestamp deletedAt;
    private UUID deletedBy;
    private List<UserApp> users;
    private Role role;
    private Integer totalUser;
    private String owner;
    private String organizationName;
    private String userImage;
    private String userName;
    private String email;
    private Boolean isMember;
    private String status;
}
