package com.tutorhelper.docs;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "User Associations", description = "Endpoints for managing associations between students and tutors")
public interface UserAssociationApiDocs {

    @Operation(
        summary = "Associate a student with a tutor",
        description = "Creates an association between a student and a tutor using their respective IDs"
    )
    ResponseEntity<Void> associateStudentAndTutor(
        @Parameter(description = "ID of the student to associate", required = true) @RequestParam Long studentId,
        @Parameter(description = "ID of the tutor to associate", required = true) @RequestParam Long tutorId
    );

    @Operation(summary = "Disassociate a student from a tutor")
    ResponseEntity<Void> disassociateStudentAndTutor(
        @Parameter(description = "ID of the student to disassociate", required = true) @RequestParam Long studentId,
        @Parameter(description = "ID of the tutor to disassociate", required = true) @RequestParam Long tutorId
    );
}
