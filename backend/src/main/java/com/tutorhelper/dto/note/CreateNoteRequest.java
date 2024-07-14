package com.tutorhelper.dto.note;

import com.tutorhelper.annotation.StudentOrTutorIdRequired;
import com.tutorhelper.entity.Note;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@StudentOrTutorIdRequired
public class CreateNoteRequest {

    private Long studentId;

    private Long tutorId;

    @NotBlank(message = "Content cannot be blank")
    private String content;

    @NotNull(message = "Accessibility cannot be null")
    private Note.AccessibilityLevel accessibility = Note.AccessibilityLevel.PRIVATE;

    @NotNull(message = "Note type cannot be null")
    private Note.NoteType type;

}
