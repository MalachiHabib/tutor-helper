package com.tutorhelper.mapper;

import java.util.Set;
import java.util.stream.Collectors;

import com.tutorhelper.dto.tutor.CreateTutorRequest;
import com.tutorhelper.dto.tutor.TutorResponse;
import com.tutorhelper.dto.tutor.TutorSummary;
import com.tutorhelper.dto.tutor.UpdateTutorRequest;
import com.tutorhelper.entity.Student;
import com.tutorhelper.entity.Tutor;
import org.springframework.stereotype.Component;

@Component
public final class TutorMapper {

    public TutorResponse toResponseDTO(Tutor tutor) {
        if (tutor == null) {
            return null;
        }

        TutorResponse tutorResponse = new TutorResponse();
        tutorResponse.setStudentIds(studentsToIds(tutor.getStudents()));
        tutorResponse.setId(tutor.getId());
        tutorResponse.setFirstName(tutor.getFirstName());
        tutorResponse.setLastName(tutor.getLastName());
        tutorResponse.setEmail(tutor.getEmail());
        tutorResponse.setPhone(tutor.getPhone());

        return tutorResponse;
    }

    public CreateTutorRequest toCreateDTO(Tutor tutor) {
        if (tutor == null) {
            return null;
        }

        CreateTutorRequest createTutorRequest = new CreateTutorRequest();
        createTutorRequest.setStudentIds(studentsToIds(tutor.getStudents()));
        createTutorRequest.setFirstName(tutor.getFirstName());
        createTutorRequest.setLastName(tutor.getLastName());
        createTutorRequest.setEmail(tutor.getEmail());
        createTutorRequest.setPhone(tutor.getPhone());

        return createTutorRequest;
    }

    public TutorSummary toSummaryDTO(Tutor tutor) {
        if (tutor == null) {
            return null;
        }

        TutorSummary tutorSummary = new TutorSummary();
        tutorSummary.setId(tutor.getId());
        tutorSummary.setFirstName(tutor.getFirstName());
        tutorSummary.setLastName(tutor.getLastName());

        return tutorSummary;
    }

    public Tutor toEntity(CreateTutorRequest createTutorRequest) {
        if (createTutorRequest == null) {
            return null;
        }

        Tutor tutor = new Tutor();
        tutor.setFirstName(createTutorRequest.getFirstName());
        tutor.setLastName(createTutorRequest.getLastName());
        tutor.setEmail(createTutorRequest.getEmail());
        tutor.setPhone(createTutorRequest.getPhone());

        return tutor;
    }

    public Tutor toEntity(TutorResponse tutorResponse) {
        if (tutorResponse == null) {
            return null;
        }

        Tutor tutor = new Tutor();
        tutor.setFirstName(tutorResponse.getFirstName());
        tutor.setLastName(tutorResponse.getLastName());
        tutor.setEmail(tutorResponse.getEmail());
        tutor.setPhone(tutorResponse.getPhone());

        return tutor;
    }


    public void updateEntityFromDTO(UpdateTutorRequest updateTutorRequest, Tutor tutor) {
        if (updateTutorRequest == null || tutor == null) {
            return;
        }

        if (updateTutorRequest.getFirstName() != null) {
            tutor.setFirstName(updateTutorRequest.getFirstName());
        }
        if (updateTutorRequest.getLastName() != null) {
            tutor.setLastName(updateTutorRequest.getLastName());
        }
        if (updateTutorRequest.getEmail() != null) {
            tutor.setEmail(updateTutorRequest.getEmail());
        }
        if (updateTutorRequest.getPhone() != null) {
            tutor.setPhone(updateTutorRequest.getPhone());
        }
    }

    private Set<Long> studentsToIds(Set<Student> students) {
        return students == null ? null : students.stream()
            .map(Student::getId)
            .collect(Collectors.toSet());
    }
}
