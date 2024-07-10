package com.tutorhelper.dto.student;

import lombok.Data;

@Data
public class StudentSummaryDTO {

    private Long id;
    private String fullName;
    private String email;
    private int tutorCount;

}
