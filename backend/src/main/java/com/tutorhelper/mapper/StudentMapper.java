package com.tutorhelper.mapper;

import com.tutorhelper.dto.student.CreateStudentDTO;
import com.tutorhelper.dto.student.StudentResponseDTO;
import com.tutorhelper.dto.student.StudentSummaryDTO;
import com.tutorhelper.dto.student.UpdateStudentDTO;
import com.tutorhelper.entity.Student;
import com.tutorhelper.entity.Tutor;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public final class StudentMapper {

    public StudentResponseDTO toResponseDTO(Student student) {
        if (student == null) {
            return null;
        }

        StudentResponseDTO studentResponseDTO = new StudentResponseDTO();

        studentResponseDTO.setTutorIds(tutorsToIds(student.getTutors()));
        studentResponseDTO.setId(student.getId());
        studentResponseDTO.setFirstName(student.getFirstName());
        studentResponseDTO.setLastName(student.getLastName());
        studentResponseDTO.setEmail(student.getEmail());
        studentResponseDTO.setPhone(student.getPhone());

        return studentResponseDTO;
    }

    public CreateStudentDTO toCreateDTO(Student student) {
        if (student == null) {
            return null;
        }

        CreateStudentDTO createStudentDTO = new CreateStudentDTO();

        createStudentDTO.setTutorIds(tutorsToIds(student.getTutors()));
        createStudentDTO.setId(student.getId());
        createStudentDTO.setFirstName(student.getFirstName());
        createStudentDTO.setLastName(student.getLastName());
        createStudentDTO.setEmail(student.getEmail());
        createStudentDTO.setPhone(student.getPhone());

        return createStudentDTO;
    }

    public StudentSummaryDTO toSummaryDTO(Student student) {
        if (student == null) {
            return null;
        }

        StudentSummaryDTO studentSummaryDTO = new StudentSummaryDTO();

        studentSummaryDTO.setId(student.getId());
        studentSummaryDTO.setEmail(student.getEmail());

        studentSummaryDTO.setTutorCount(student.getTutors().size());
        studentSummaryDTO.setFullName(student.getFirstName() + ' ' + student.getLastName());

        return studentSummaryDTO;
    }

    public Student toEntity(CreateStudentDTO createStudentDTO) {
        if (createStudentDTO == null) {
            return null;
        }

        Student student = new Student();

        student.setFirstName(createStudentDTO.getFirstName());
        student.setLastName(createStudentDTO.getLastName());
        student.setEmail(createStudentDTO.getEmail());
        student.setPhone(createStudentDTO.getPhone());

        return student;
    }

    public void updateEntityFromDTO(UpdateStudentDTO updateStudentDTO, Student student) {
        if (updateStudentDTO == null) {
            return;
        }

        if (updateStudentDTO.getFirstName() != null) {
            student.setFirstName(updateStudentDTO.getFirstName());
        }
        if (updateStudentDTO.getLastName() != null) {
            student.setLastName(updateStudentDTO.getLastName());
        }
        if (updateStudentDTO.getEmail() != null) {
            student.setEmail(updateStudentDTO.getEmail());
        }
        if (updateStudentDTO.getPhone() != null) {
            student.setPhone(updateStudentDTO.getPhone());
        }
    }

    private Set<Long> tutorsToIds(Set<Tutor> tutors) {
        return tutors == null ? null : tutors.stream()
            .map(Tutor::getId)
            .collect(Collectors.toSet());
    }
}
