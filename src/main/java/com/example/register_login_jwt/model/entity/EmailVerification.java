package com.example.register_login_jwt.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmailVerification {
    private UUID id;
    private UUID userId;
    private String code;
    private Boolean isVerified;
    private Timestamp expireAt;
    private Timestamp createdAt;
}
