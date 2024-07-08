package com.tutorhelper.controller;

import com.tutorhelper.config.Paths;
import com.tutorhelper.dto.StudentDTO;
import com.tutorhelper.dto.TutorDTO;
import com.tutorhelper.response.PagedResponse;
import com.tutorhelper.service.TutorService;
import com.tutorhelper.util.LocationURIBuilder;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tutor")
@AllArgsConstructor
public class TutorController {

    private final TutorService tutorService;

    @PostMapping
    public ResponseEntity<Void> createTutor(@Valid @RequestBody TutorDTO tutorDto) {
        Long tutorId = tutorService.createTutor(tutorDto);
        URI location = LocationURIBuilder.buildLocationURI(Paths.ID_PATH, tutorId);
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{tutorId}")
    public ResponseEntity<TutorDTO> getTutorById(@PathVariable Long tutorId) {
        TutorDTO tutorDTO = tutorService.get(tutorId);
        return ResponseEntity.ok(tutorDTO);
    }

    @GetMapping("/all")
    public ResponseEntity<PagedResponse<TutorDTO>> getAllTutors() {
        List<TutorDTO> tutorDTOS = tutorService.getAll();
        return ResponseEntity.ok(PagedResponse.from(tutorDTOS));
    }

    @DeleteMapping("/{tutorId}")
    public ResponseEntity<Void> deleteTutorById(@PathVariable Long tutorId) {
        tutorService.deleteById(tutorId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{tutorId}/student-ids")
    public ResponseEntity<List<Long>> getTutorStudentIds(@PathVariable Long tutorId) {
        List<Long> studentIds = tutorService.getStudentIdsForTutor(tutorId);
        return ResponseEntity.ok(studentIds);
    }

}
