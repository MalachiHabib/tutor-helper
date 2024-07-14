package com.tutorhelper.controller;

import java.net.URI;
import java.util.List;
import java.util.Set;

import com.tutorhelper.config.Paths;
import com.tutorhelper.docs.StudentApiDocs;
import com.tutorhelper.dto.student.CreateStudentRequest;
import com.tutorhelper.dto.student.StudentResponse;
import com.tutorhelper.dto.student.StudentSummary;
import com.tutorhelper.dto.student.UpdateStudentRequest;
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
@AllArgsConstructor
@RequestMapping("/students")
public class StudentController implements StudentApiDocs {

    private final StudentService studentService;

    @PostMapping
    public ResponseEntity<Void> createStudent(@Valid @RequestBody CreateStudentRequest createStudentRequest) {
        Long studentId = studentService.createStudent(createStudentRequest);
        URI location = LocationURIBuilder.buildLocationURI(Paths.ID_PATH, studentId);
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{studentId}")
    public ResponseEntity<StudentResponse> updateStudent(
        @PathVariable Long studentId,
        @Valid @RequestBody UpdateStudentRequest createStudentDto
    ) {
        StudentResponse updatedStudent = studentService.updateStudent(studentId, createStudentDto);
        return ResponseEntity.ok(updatedStudent);
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<StudentResponse> getStudentById(@PathVariable Long studentId) {
        StudentResponse studentResponse = studentService.get(studentId);
        return ResponseEntity.ok(studentResponse);
    }

    @GetMapping("/all")
    public ResponseEntity<PagedResponse<StudentSummary>> getAllStudents() {
        List<StudentSummary> createStudentDTOS = studentService.getAll();
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
