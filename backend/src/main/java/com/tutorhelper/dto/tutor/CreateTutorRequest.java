package com.tutorhelper.dto.tutor;

import java.util.Set;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateTutorRequest {

    @Null(message = "ID must be null for new tutors (it is auto generated)")
    private Long id;

    @NotBlank(message = "First name is required")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    private String lastName;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    @Size(max = 20, message = "Phone number can't be longer than 20 characters")
    private String phone;

    private Set<Long> studentIds;

}
