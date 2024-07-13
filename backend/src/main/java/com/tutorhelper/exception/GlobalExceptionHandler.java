package com.tutorhelper.exception;

import java.util.List;
import java.util.stream.Collectors;

import com.tutorhelper.error.ErrorResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<String> details = ex.getBindingResult().getFieldErrors()
            .stream()
            .map(error -> String.format("%s: %s", error.getField(), error.getDefaultMessage()))
            .collect(Collectors.toList());

        return ErrorResponse.create("Validation Failed", HttpStatus.BAD_REQUEST, details);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponse> handleEntityNotFoundException(EntityNotFoundException ex) {
        return ErrorResponse.create(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AssociationAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<ErrorResponse> handleAssociationAlreadyExistsException(AssociationAlreadyExistsException ex) {
        return ErrorResponse.create(ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(AssociationNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponse> handleAssociationNotFoundException(AssociationNotFoundException ex) {
        return ErrorResponse.create(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
