package com.tutorhelper.controller;

import com.tutorhelper.service.UserAssociationService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/user-associations")
public class UserAssociationController {

    private final UserAssociationService userAssociationService;

    @PostMapping
    public ResponseEntity<Void> associateStudentAndTutor(
        @RequestParam @NonNull Long studentId,
        @RequestParam @NonNull Long tutorId
    ) {
        userAssociationService.associateStudentAndTutor(studentId, tutorId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping
    public ResponseEntity<Void> disassociateStudentAndTutor(
        @RequestParam @NonNull Long studentId,
        @RequestParam @NonNull Long tutorId
    ) {
        userAssociationService.disassociateStudentAndTutor(studentId, tutorId);
        return ResponseEntity.noContent().build();
    }
}
