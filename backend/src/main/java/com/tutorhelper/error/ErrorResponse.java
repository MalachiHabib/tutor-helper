package com.tutorhelper.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Getter
public class ErrorResponse {

    private final String message;
    private final Timestamp timestamp;
    private final int status;
    private final List<String> details;

    private ErrorResponse(String message, Timestamp timestamp, int status, List<String> details) {
        this.message = message;
        this.timestamp = timestamp;
        this.status = status;
        this.details = details;
    }

    public static ResponseEntity<ErrorResponse> create(String message, HttpStatus status) {
        return new ResponseEntity<>(new ErrorResponse(
          message,
          Timestamp.from(Instant.now()),
          status.value(),
          null
        ), status);
    }

    public static ResponseEntity<ErrorResponse> create(String message, HttpStatus status, List<String> details) {
        return new ResponseEntity<>(new ErrorResponse(
          message,
          Timestamp.from(Instant.now()),
          status.value(),
          details
        ), status);
    }
}
