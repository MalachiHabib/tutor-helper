package com.tutorhelper.controller;

import com.tutorhelper.docs.UserAssociationApiDocs;
import com.tutorhelper.service.UserAssociationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/user-associations")
public class UserAssociationController implements UserAssociationApiDocs {

    private final UserAssociationService userAssociationService;

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> associateStudentAndTutor(
        @RequestParam Long studentId,
        @RequestParam Long tutorId
    ) {
        userAssociationService.associateStudentAndTutor(studentId, tutorId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> disassociateStudentAndTutor(
        @RequestParam Long studentId,
        @RequestParam Long tutorId
    ) {
        userAssociationService.disassociateStudentAndTutor(studentId, tutorId);
        return ResponseEntity.noContent().build();
    }
}
