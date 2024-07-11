package com.tutorhelper.mapper;

import com.tutorhelper.dto.tutor.CreateTutorDTO;
import com.tutorhelper.dto.tutor.TutorResponseDTO;
import com.tutorhelper.dto.tutor.TutorSummaryDTO;
import com.tutorhelper.dto.tutor.UpdateTutorDTO;
import com.tutorhelper.entity.Student;
import com.tutorhelper.entity.Tutor;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public final class TutorMapper {

    public TutorResponseDTO toResponseDTO(Tutor tutor) {
        if (tutor == null) {
            return null;
        }

        TutorResponseDTO tutorResponseDTO = new TutorResponseDTO();
        tutorResponseDTO.setStudentIds(studentsToIds(tutor.getStudents()));
        tutorResponseDTO.setId(tutor.getId());
        tutorResponseDTO.setFirstName(tutor.getFirstName());
        tutorResponseDTO.setLastName(tutor.getLastName());
        tutorResponseDTO.setEmail(tutor.getEmail());
        tutorResponseDTO.setPhone(tutor.getPhone());

        return tutorResponseDTO;
    }

    public CreateTutorDTO toCreateDTO(Tutor tutor) {
        if (tutor == null) {
            return null;
        }

        CreateTutorDTO createTutorDTO = new CreateTutorDTO();
        createTutorDTO.setStudentIds(studentsToIds(tutor.getStudents()));
        createTutorDTO.setFirstName(tutor.getFirstName());
        createTutorDTO.setLastName(tutor.getLastName());
        createTutorDTO.setEmail(tutor.getEmail());
        createTutorDTO.setPhone(tutor.getPhone());

        return createTutorDTO;
    }

    public TutorSummaryDTO toSummaryDTO(Tutor tutor) {
        if (tutor == null) {
            return null;
        }

        TutorSummaryDTO tutorSummaryDTO = new TutorSummaryDTO();
        tutorSummaryDTO.setId(tutor.getId());
        tutorSummaryDTO.setFirstName(tutor.getFirstName());
        tutorSummaryDTO.setLastName(tutor.getLastName());

        return tutorSummaryDTO;
    }

    public Tutor toEntity(CreateTutorDTO createTutorDTO) {
        if (createTutorDTO == null) {
            return null;
        }

        Tutor tutor = new Tutor();
        tutor.setFirstName(createTutorDTO.getFirstName());
        tutor.setLastName(createTutorDTO.getLastName());
        tutor.setEmail(createTutorDTO.getEmail());
        tutor.setPhone(createTutorDTO.getPhone());

        return tutor;
    }

    public void updateEntityFromDTO(UpdateTutorDTO updateTutorDTO, Tutor tutor) {
        if (updateTutorDTO == null || tutor == null) {
            return;
        }

        if (updateTutorDTO.getFirstName() != null) {
            tutor.setFirstName(updateTutorDTO.getFirstName());
        }
        if (updateTutorDTO.getLastName() != null) {
            tutor.setLastName(updateTutorDTO.getLastName());
        }
        if (updateTutorDTO.getEmail() != null) {
            tutor.setEmail(updateTutorDTO.getEmail());
        }
        if (updateTutorDTO.getPhone() != null) {
            tutor.setPhone(updateTutorDTO.getPhone());
        }
    }

    private Set<Long> studentsToIds(Set<Student> students) {
        return students == null ? null : students.stream()
            .map(Student::getId)
            .collect(Collectors.toSet());
    }
}
