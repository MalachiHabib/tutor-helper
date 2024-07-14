package com.tutorhelper.docs;

import com.tutorhelper.dto.note.CreateNoteRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Notes", description = "Endpoints for managing notes")
public interface NoteApiDocs {

    @Operation(summary = "Create a new note", description = "Creates a new note record with the provided information")
    @ApiResponse(responseCode = "201", description = "Note created successfully")
    ResponseEntity<Void> createNote(
        @RequestBody CreateNoteRequest createNoteRequest
    );
}
