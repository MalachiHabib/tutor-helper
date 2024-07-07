package com.tutorhelper.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class UserDTO {

    @NotEmpty(message = "name cannot be empty")
    private String firstName;

}
