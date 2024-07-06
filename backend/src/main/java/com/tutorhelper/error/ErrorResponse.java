package com.tutorhelper.error;

import java.sql.Timestamp;
import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
@AllArgsConstructor
public class ErrorResponse {

    private final String message;
    private final Timestamp timestamp;
    private final int status;

    public static ResponseEntity<ErrorResponse> create(String message, HttpStatus status) {
        return new ResponseEntity<>(new ErrorResponse(
            message,
            Timestamp.from(Instant.now()),
            status.value()
        ), status);
    }
    
}
