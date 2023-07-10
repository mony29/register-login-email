package com.example.register_login_jwt.model.request;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserAppRequest {
    private String name;
    private String email;
    private String password;
}
