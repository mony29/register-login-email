package com.example.register_login_jwt;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@SecurityScheme(
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer"
)
@OpenAPIDefinition(
        info = @Info(title = "Register & Login with JWT", version = "v1", description = "")
)
@CrossOrigin()
@SpringBootApplication
public class RegisterLoginJwtApplication {
    public static void main(String[] args) {
        SpringApplication.run(RegisterLoginJwtApplication.class, args);
    }
}
