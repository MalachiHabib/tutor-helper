package com.tutorhelper.mapper;

import com.tutorhelper.dto.StudentDTO;
import com.tutorhelper.entity.Student;
import com.tutorhelper.entity.Tutor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    @Mapping(target = "tutorIds", source = "tutors", qualifiedByName = "tutorsToIds")
    StudentDTO studentToStudentDTO(Student student);

    @Mapping(target = "tutors", ignore = true)
    Student studentDTOToStudent(StudentDTO studentDTO);

    @Named("tutorsToIds")
    default Set<Long> tutorsToIds(Set<Tutor> tutors) {
        if (tutors == null) {
            return null;
        }
        return tutors.stream()
          .map(Tutor::getId)
          .collect(Collectors.toSet());
    }
}
