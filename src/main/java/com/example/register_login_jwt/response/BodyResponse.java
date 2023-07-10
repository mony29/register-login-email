package com.example.register_login_jwt.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BodyResponse<T> {
    private Boolean success;
    private Timestamp date;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T payload;

    public static <T> ResponseEntity<?> getBodyResponse(T payload) {
        BodyResponse<T> response = BodyResponse.<T>builder()
                .payload(payload)
                .success(true)
                .date(new Timestamp(System.currentTimeMillis()))
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
