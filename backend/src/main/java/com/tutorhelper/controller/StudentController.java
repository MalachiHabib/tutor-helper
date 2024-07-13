package com.tutorhelper.controller;

import java.net.URI;
import java.util.List;
import java.util.Set;

import com.tutorhelper.config.Paths;
import com.tutorhelper.dto.student.CreateStudentDTO;
import com.tutorhelper.dto.student.StudentResponseDTO;
import com.tutorhelper.dto.student.StudentSummaryDTO;
import com.tutorhelper.dto.student.UpdateStudentDTO;
import com.tutorhelper.response.PagedResponse;
import com.tutorhelper.service.StudentService;
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
@RequestMapping("/students")
@AllArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PostMapping
    public ResponseEntity<Void> createStudent(@Valid @RequestBody CreateStudentDTO createStudentDto) {
        Long studentId = studentService.createStudent(createStudentDto);
        URI location = LocationURIBuilder.buildLocationURI(Paths.ID_PATH, studentId);
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{studentId}")
    public ResponseEntity<StudentResponseDTO> updateStudent(
        @PathVariable Long studentId,
        @Valid @RequestBody UpdateStudentDTO createStudentDto
    ) {
        StudentResponseDTO updatedStudent = studentService.updateStudent(studentId, createStudentDto);
        return ResponseEntity.ok(updatedStudent);
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<StudentResponseDTO> getStudentById(@PathVariable Long studentId) {
        StudentResponseDTO studentResponseDTO = studentService.get(studentId);
        return ResponseEntity.ok(studentResponseDTO);
    }

    @GetMapping("/all")
    public ResponseEntity<PagedResponse<StudentSummaryDTO>> getAllStudents() {
        List<StudentSummaryDTO> createStudentDTOS = studentService.getAll();
        return ResponseEntity.ok(PagedResponse.from(createStudentDTOS));
    }

    @DeleteMapping("/{studentId}")
    public ResponseEntity<Void> deleteStudentById(@PathVariable Long studentId) {
        studentService.deleteById(studentId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{studentId}/tutors")
    public ResponseEntity<Set<Long>> getStudentTutors(@PathVariable Long studentId) {
        Set<Long> tutors = studentService.getTutorIdsForStudent(studentId);
        return ResponseEntity.ok(tutors);
    }

}
