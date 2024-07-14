package com.tutorhelper.docs;

import java.util.Set;

import com.tutorhelper.dto.student.CreateStudentRequest;
import com.tutorhelper.dto.student.StudentResponse;
import com.tutorhelper.dto.student.StudentSummary;
import com.tutorhelper.dto.student.UpdateStudentRequest;
import com.tutorhelper.response.PagedResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Students", description = "Endpoints for managing student information")
public interface StudentApiDocs {

    @Operation(summary = "Create a new student", description = "Creates a new student record with the provided information")
    @ApiResponse(responseCode = "201", description = "Student created successfully")
    ResponseEntity<Void> createStudent(
        @RequestBody CreateStudentRequest createStudentRequest
    );

    @Operation(summary = "Update an existing student", description = "Updates the information of an existing student")
    @ApiResponse(responseCode = "200", description = "Student updated successfully",
        content = @Content(schema = @Schema(implementation = StudentResponse.class)))
    ResponseEntity<StudentResponse> updateStudent(
        @Parameter(description = "ID of the student to update", required = true) @PathVariable Long studentId,
        @RequestBody UpdateStudentRequest updateStudentRequest
    );

    @Operation(summary = "Get a student by ID", description = "Retrieves the details of a specific student")
    @ApiResponse(responseCode = "200", description = "Student found",
        content = @Content(schema = @Schema(implementation = StudentResponse.class)))
    ResponseEntity<StudentResponse> getStudentById(
        @Parameter(description = "ID of the student to retrieve", required = true) @PathVariable Long studentId
    );

    @Operation(summary = "Get all students", description = "Retrieves a paged list of all students")
    @ApiResponse(responseCode = "200", description = "List of students retrieved successfully",
        content = @Content(schema = @Schema(implementation = PagedResponse.class)))
    ResponseEntity<PagedResponse<StudentSummary>> getAllStudents();

    @Operation(summary = "Delete a student", description = "Deletes a specific student by ID")
    @ApiResponse(responseCode = "204", description = "Student deleted successfully")
    ResponseEntity<Void> deleteStudentById(
        @Parameter(description = "ID of the student to delete", required = true) @PathVariable Long studentId
    );

    @Operation(summary = "Get tutors for a student", description = "Retrieves the IDs of tutors associated with a specific student")
    @ApiResponse(responseCode = "200", description = "List of tutor IDs retrieved successfully",
        content = @Content(schema = @Schema(implementation = Set.class)))
    ResponseEntity<Set<Long>> getStudentTutors(
        @Parameter(description = "ID of the student to get tutors for", required = true) @PathVariable Long studentId
    );
}
