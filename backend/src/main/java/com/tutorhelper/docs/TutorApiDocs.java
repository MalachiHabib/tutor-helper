package com.tutorhelper.docs;

import java.util.Set;

import com.tutorhelper.dto.tutor.CreateTutorRequest;
import com.tutorhelper.dto.tutor.TutorResponse;
import com.tutorhelper.dto.tutor.TutorSummary;
import com.tutorhelper.dto.tutor.UpdateTutorRequest;
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

@Tag(name = "Tutors", description = "Endpoints for managing tutor information")
public interface TutorApiDocs {

    @Operation(summary = "Create a new tutor", description = "Creates a new tutor record with the provided information")
    @ApiResponse(responseCode = "201", description = "Tutor created successfully")
    ResponseEntity<Void> createTutor(
        @RequestBody CreateTutorRequest createTutorRequest
    );

    @Operation(summary = "Get a tutor by ID", description = "Retrieves the details of a specific tutor")
    @ApiResponse(responseCode = "200", description = "Tutor found",
        content = @Content(schema = @Schema(implementation = TutorResponse.class)))
    ResponseEntity<TutorResponse> getTutorById(
        @Parameter(description = "ID of the tutor to retrieve", required = true) @PathVariable Long tutorId
    );

    @Operation(summary = "Get all tutors", description = "Retrieves a paged list of all tutors")
    @ApiResponse(responseCode = "200", description = "List of tutors retrieved successfully",
        content = @Content(schema = @Schema(implementation = PagedResponse.class)))
    ResponseEntity<PagedResponse<TutorSummary>> getAllTutors();

    @Operation(summary = "Update an existing tutor", description = "Updates the information of an existing tutor")
    @ApiResponse(responseCode = "200", description = "Tutor updated successfully",
        content = @Content(schema = @Schema(implementation = TutorResponse.class)))
    ResponseEntity<TutorResponse> updateTutor(
        @Parameter(description = "ID of the tutor to update", required = true) @PathVariable Long tutorId,
        @RequestBody UpdateTutorRequest updateTutorRequest
    );

    @Operation(summary = "Delete a tutor", description = "Deletes a specific tutor by ID")
    @ApiResponse(responseCode = "204", description = "Tutor deleted successfully")
    ResponseEntity<Void> deleteTutorById(
        @Parameter(description = "ID of the tutor to delete", required = true) @PathVariable Long tutorId
    );

    @Operation(summary = "Get students for a tutor", description = "Retrieves the IDs of students associated with a specific tutor")
    @ApiResponse(responseCode = "200", description = "List of student IDs retrieved successfully",
        content = @Content(schema = @Schema(implementation = Set.class)))
    ResponseEntity<Set<Long>> getTutorStudentIds(
        @Parameter(description = "ID of the tutor to get students for", required = true) @PathVariable Long tutorId
    );
}
