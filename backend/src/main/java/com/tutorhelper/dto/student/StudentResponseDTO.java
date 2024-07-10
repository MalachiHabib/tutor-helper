package com.tutorhelper.dto.student;

import java.util.Set;
import lombok.Data;

@Data
public class StudentResponseDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Set<Long> tutorIds;

}
