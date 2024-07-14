package com.tutorhelper.annotation;

import com.tutorhelper.dto.note.CreateNoteRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class StudentOrTutorIdRequiredValidator
    implements ConstraintValidator<StudentOrTutorIdRequired, CreateNoteRequest> {

    @Override
    public boolean isValid(CreateNoteRequest request, ConstraintValidatorContext context) {
        return request.getStudentId() != null || request.getTutorId() != null;
    }
}
