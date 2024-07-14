package com.tutorhelper.mapper;

import java.util.Set;
import java.util.stream.Collectors;

import com.tutorhelper.dto.student.CreateStudentRequest;
import com.tutorhelper.dto.student.StudentResponse;
import com.tutorhelper.dto.student.StudentSummary;
import com.tutorhelper.dto.student.UpdateStudentRequest;
import com.tutorhelper.entity.Student;
import com.tutorhelper.entity.Tutor;
import org.springframework.stereotype.Component;

@Component
public final class StudentMapper {

    public StudentResponse toResponseDTO(Student student) {
        if (student == null) {
            return null;
        }

        StudentResponse studentResponse = new StudentResponse();

        studentResponse.setTutorIds(tutorsToIds(student.getTutors()));
        studentResponse.setId(student.getId());
        studentResponse.setFirstName(student.getFirstName());
        studentResponse.setLastName(student.getLastName());
        studentResponse.setEmail(student.getEmail());
        studentResponse.setPhone(student.getPhone());

        return studentResponse;
    }

    public CreateStudentRequest toCreateDTO(Student student) {
        if (student == null) {
            return null;
        }

        CreateStudentRequest createStudentRequest = new CreateStudentRequest();

        createStudentRequest.setTutorIds(tutorsToIds(student.getTutors()));
        createStudentRequest.setId(student.getId());
        createStudentRequest.setFirstName(student.getFirstName());
        createStudentRequest.setLastName(student.getLastName());
        createStudentRequest.setEmail(student.getEmail());
        createStudentRequest.setPhone(student.getPhone());

        return createStudentRequest;
    }

    public StudentSummary toSummaryDTO(Student student) {
        if (student == null) {
            return null;
        }

        StudentSummary studentSummary = new StudentSummary();

        studentSummary.setId(student.getId());
        studentSummary.setEmail(student.getEmail());

        studentSummary.setTutorCount(student.getTutors().size());
        studentSummary.setFullName(student.getFirstName() + ' ' + student.getLastName());

        return studentSummary;
    }

    public Student toEntity(CreateStudentRequest createStudentRequest) {
        if (createStudentRequest == null) {
            return null;
        }

        Student student = new Student();

        student.setFirstName(createStudentRequest.getFirstName());
        student.setLastName(createStudentRequest.getLastName());
        student.setEmail(createStudentRequest.getEmail());
        student.setPhone(createStudentRequest.getPhone());

        return student;
    }

    public Student toEntity(StudentResponse studentResponse) {
        if (studentResponse == null) {
            return null;
        }

        Student student = new Student();

        student.setFirstName(studentResponse.getFirstName());
        student.setLastName(studentResponse.getLastName());
        student.setEmail(studentResponse.getEmail());
        student.setPhone(studentResponse.getPhone());

        return student;
    }

    public void updateEntityFromDTO(UpdateStudentRequest updateStudentRequest, Student student) {
        if (updateStudentRequest == null) {
            return;
        }

        if (updateStudentRequest.getFirstName() != null) {
            student.setFirstName(updateStudentRequest.getFirstName());
        }
        if (updateStudentRequest.getLastName() != null) {
            student.setLastName(updateStudentRequest.getLastName());
        }
        if (updateStudentRequest.getEmail() != null) {
            student.setEmail(updateStudentRequest.getEmail());
        }
        if (updateStudentRequest.getPhone() != null) {
            student.setPhone(updateStudentRequest.getPhone());
        }
    }

    private Set<Long> tutorsToIds(Set<Tutor> tutors) {
        return tutors == null ? null : tutors.stream()
            .map(Tutor::getId)
            .collect(Collectors.toSet());
    }
}
