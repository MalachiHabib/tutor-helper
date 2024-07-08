package com.tutorhelper.controller;

import com.tutorhelper.config.Paths;
import com.tutorhelper.dto.StudentDTO;
import com.tutorhelper.dto.TutorDTO;
import com.tutorhelper.response.PagedResponse;
import com.tutorhelper.service.StudentService;
import com.tutorhelper.util.LocationURIBuilder;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
@AllArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PostMapping
    public ResponseEntity<Void> createStudent(@Valid @RequestBody StudentDTO studentDto) {
        Long studentId = studentService.createStudent(studentDto);
        URI location = LocationURIBuilder.buildLocationURI(Paths.ID_PATH, studentId);
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable Long studentId) {
        StudentDTO studentDTO = studentService.get(studentId);
        return ResponseEntity.ok(studentDTO);
    }

    @GetMapping("/all")
    public ResponseEntity<PagedResponse<StudentDTO>> getAllStudents() {
        List<StudentDTO> studentDTOS = studentService.getAll();
        return ResponseEntity.ok(PagedResponse.from(studentDTOS));
    }

    @DeleteMapping("/{studentId}")
    public ResponseEntity<Void> deleteStudentById(@PathVariable Long studentId) {
        studentService.deleteById(studentId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{studentId}/tutors")
    public ResponseEntity<List<Long>> getStudentTutors(@PathVariable Long studentId) {
        List<Long> tutors = studentService.getTutorIdsForStudent(studentId);
        return ResponseEntity.ok(tutors);
    }

}
