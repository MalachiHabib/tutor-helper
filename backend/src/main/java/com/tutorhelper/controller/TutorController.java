package com.tutorhelper.controller;

import com.tutorhelper.config.Paths;
import com.tutorhelper.dto.tutor.CreateTutorDTO;
import com.tutorhelper.dto.tutor.TutorResponseDTO;
import com.tutorhelper.dto.tutor.TutorSummaryDTO;
import com.tutorhelper.dto.tutor.UpdateTutorDTO;
import com.tutorhelper.response.PagedResponse;
import com.tutorhelper.service.TutorService;
import com.tutorhelper.utils.LocationURIBuilder;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Set;
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
public class TutorController {

    private final TutorService tutorService;

    @PostMapping
    public ResponseEntity<Void> createTutor(@Valid @RequestBody CreateTutorDTO createTutorDto) {
        Long tutorId = tutorService.createTutor(createTutorDto);
        URI location = LocationURIBuilder.buildLocationURI(Paths.ID_PATH, tutorId);
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{tutorId}")
    public ResponseEntity<TutorResponseDTO> getTutorById(@PathVariable Long tutorId) {
        TutorResponseDTO tutorResponseDTO = tutorService.get(tutorId);
        return ResponseEntity.ok(tutorResponseDTO);
    }

    @GetMapping("/all")
    public ResponseEntity<PagedResponse<TutorSummaryDTO>> getAllTutors() {
        List<TutorSummaryDTO> tutorSummaryDTOs = tutorService.getAll();
        return ResponseEntity.ok(PagedResponse.from(tutorSummaryDTOs));
    }

    @PutMapping("/{tutorId}")
    public ResponseEntity<TutorResponseDTO> updateTutor(
        @PathVariable Long tutorId,
        @Valid @RequestBody UpdateTutorDTO updateTutorDTO
    ) {
        TutorResponseDTO updatedTutor = tutorService.updateTutor(tutorId, updateTutorDTO);
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

    @PutMapping("/{tutorId}/students")
    public ResponseEntity<Void> updateTutorStudents(@PathVariable Long tutorId, @RequestBody Set<Long> studentIds) {
        tutorService.updateTutorStudents(tutorId, studentIds);
        return ResponseEntity.noContent().build();
    }
}
