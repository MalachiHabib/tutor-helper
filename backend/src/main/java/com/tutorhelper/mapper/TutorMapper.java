package com.tutorhelper.mapper;

import com.tutorhelper.dto.tutor.CreateTutorDTO;
import com.tutorhelper.dto.tutor.TutorResponseDTO;
import com.tutorhelper.dto.tutor.TutorSummaryDTO;
import com.tutorhelper.dto.tutor.UpdateTutorDTO;
import com.tutorhelper.entity.Student;
import com.tutorhelper.entity.Tutor;
import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface TutorMapper {

    @Mapping(target = "studentIds", source = "students", qualifiedByName = "studentsToIds")
    TutorResponseDTO toResponseDTO(Tutor tutor);

    @Mapping(target = "studentIds", source = "students", qualifiedByName = "studentsToIds")
    CreateTutorDTO toCreateDTO(Tutor tutor);

    @Mapping(target = "studentCount", expression = "java(tutor.getStudents().size())")
    @Mapping(target = "fullName", expression = "java(tutor.getFirstName() + ' ' + tutor.getLastName())")
    TutorSummaryDTO toSummaryDTO(Tutor tutor);

    @Mapping(target = "students", ignore = true)
    @Mapping(target = "id", ignore = true)
    Tutor toEntity(CreateTutorDTO createTutorDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "students", ignore = true)
    void updateEntityFromDTO(UpdateTutorDTO updateTutorDTO, @MappingTarget Tutor tutor);

    @Named("studentsToIds")
    default Set<Long> studentsToIds(Set<Student> students) {
        return students == null ? null : students.stream()
            .map(Student::getId)
            .collect(Collectors.toSet());
    }
}
