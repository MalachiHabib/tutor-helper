package com.tutorhelper.dto.tutor;

import java.util.Set;

import lombok.Data;

@Data
public class TutorResponse {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Set<Long> studentIds;

}
