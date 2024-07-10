package com.tutorhelper.controller;

import com.tutorhelper.service.UserAssociationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user-association")
@AllArgsConstructor
public class UserAssociationController {

    private final UserAssociationService userAssociationService;

    @PostMapping("/associate")
    public ResponseEntity<Void> associateStudentAndTutor(
        @RequestParam Long studentId, @RequestParam Long tutorId
    ) {
        userAssociationService.associateStudentAndTutor(studentId, tutorId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/disassociate")
    public ResponseEntity<Void> disassociateStudentAndTutor(
        @RequestParam Long studentId, @RequestParam Long tutorId
    ) {
        userAssociationService.disassociateStudentAndTutor(studentId, tutorId);
        return ResponseEntity.ok().build();
    }
}
