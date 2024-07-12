package com.tutorhelper.error;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Collections;
import java.util.List;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class ErrorResponse {

    private final String message;
    private final Timestamp timestamp;
    private final int status;
    private final List<String> details;
    private final static List<String> NO_DETAILS_PROVIDED_MESSAGE = Collections.singletonList(
        "No additional details provided");

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
            NO_DETAILS_PROVIDED_MESSAGE
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
