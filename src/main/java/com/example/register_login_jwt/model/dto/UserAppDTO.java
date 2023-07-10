package com.example.register_login_jwt.model.dto;

import lombok.*;

import java.sql.Timestamp;
import java.util.UUID;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserAppDTO {
    private UUID id;
    private String email;
    private String name;
    private String gender;
    private String phone;
    private String address;
    private String profile;
    private Boolean isEnabled;
    private Boolean isAccountNonLocked;
    private Boolean isAccountNonExpired;
    private Timestamp createdAt;
}
