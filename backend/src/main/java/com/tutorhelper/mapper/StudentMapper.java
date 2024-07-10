package com.tutorhelper.mapper;

import com.tutorhelper.dto.student.CreateStudentDTO;
import com.tutorhelper.dto.student.StudentResponseDTO;
import com.tutorhelper.dto.student.StudentSummaryDTO;
import com.tutorhelper.dto.student.UpdateStudentDTO;
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
public interface StudentMapper {

    @Mapping(target = "tutorIds", source = "tutors", qualifiedByName = "tutorsToIds")
    StudentResponseDTO toResponseDTO(Student student);

    @Mapping(target = "tutorIds", source = "tutors", qualifiedByName = "tutorsToIds")
    CreateStudentDTO toCreateDTO(Student student);

    @Mapping(target = "tutorCount", expression = "java(student.getTutors().size())")
    @Mapping(target = "fullName", expression = "java(student.getFirstName() + ' ' + student.getLastName())")
    StudentSummaryDTO toSummaryDTO(Student student);

    @Mapping(target = "tutors", ignore = true)
    @Mapping(target = "id", ignore = true)
    Student toEntity(CreateStudentDTO createStudentDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "tutors", ignore = true)
    void updateEntityFromDTO(UpdateStudentDTO updateStudentDTO, @MappingTarget Student student);

    @Named("tutorsToIds")
    default Set<Long> tutorsToIds(Set<Tutor> tutors) {
        return tutors == null ? null : tutors.stream()
            .map(Tutor::getId)
            .collect(Collectors.toSet());
    }
}
