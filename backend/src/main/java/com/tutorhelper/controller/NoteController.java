package com.tutorhelper.controller;

import java.net.URI;

import com.tutorhelper.config.Paths;
import com.tutorhelper.docs.NoteApiDocs;
import com.tutorhelper.dto.note.CreateNoteRequest;
import com.tutorhelper.service.NoteService;
import com.tutorhelper.util.LocationURIBuilder;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notes")
public class NoteController implements NoteApiDocs {

    private final NoteService noteService;

    @PostMapping
    public ResponseEntity<Void> createNote(@Valid @RequestBody CreateNoteRequest createNoteRequest) {
        Long noteId = noteService.createNote(createNoteRequest);
        URI location = LocationURIBuilder.buildLocationURI(Paths.ID_PATH, noteId);
        return ResponseEntity.created(location).build();
    }

}
