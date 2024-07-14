package com.tutorhelper.controller;

import java.net.URI;
import java.util.List;
import java.util.Set;

import com.tutorhelper.config.Paths;
import com.tutorhelper.docs.TutorApiDocs;
import com.tutorhelper.dto.tutor.CreateTutorRequest;
import com.tutorhelper.dto.tutor.TutorResponse;
import com.tutorhelper.dto.tutor.TutorSummary;
import com.tutorhelper.dto.tutor.UpdateTutorRequest;
import com.tutorhelper.response.PagedResponse;
import com.tutorhelper.service.TutorService;
import com.tutorhelper.util.LocationURIBuilder;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tutors")
@AllArgsConstructor
public class TutorController implements TutorApiDocs {

    private final TutorService tutorService;

    @PostMapping
    public ResponseEntity<Void> createTutor(@Valid @RequestBody CreateTutorRequest createTutorRequest) {
        Long tutorId = tutorService.createTutor(createTutorRequest);
        URI location = LocationURIBuilder.buildLocationURI(Paths.ID_PATH, tutorId);
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{tutorId}")
    public ResponseEntity<TutorResponse> getTutorById(@PathVariable Long tutorId) {
        TutorResponse tutorResponse = tutorService.get(tutorId);
        return ResponseEntity.ok(tutorResponse);
    }

    @GetMapping("/all")
    public ResponseEntity<PagedResponse<TutorSummary>> getAllTutors() {
        List<TutorSummary> tutorSummaries = tutorService.getAll();
        return ResponseEntity.ok(PagedResponse.from(tutorSummaries));
    }

    @PutMapping("/{tutorId}")
    public ResponseEntity<TutorResponse> updateTutor(
        @PathVariable Long tutorId,
        @Valid @RequestBody UpdateTutorRequest updateTutorRequest
    ) {
        TutorResponse updatedTutor = tutorService.updateTutor(tutorId, updateTutorRequest);
        return ResponseEntity.ok(updatedTutor);
    }

    @DeleteMapping("/{tutorId}")
    public ResponseEntity<Void> deleteTutorById(@PathVariable Long tutorId) {
        tutorService.deleteById(tutorId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{tutorId}/student-ids")
    public ResponseEntity<Set<Long>> getTutorStudentIds(@PathVariable Long tutorId) {
        Set<Long> studentIds = tutorService.getStudentIdsForTutor(tutorId);
        return ResponseEntity.ok(studentIds);
    }

}
