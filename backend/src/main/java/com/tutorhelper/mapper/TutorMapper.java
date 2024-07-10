package com.tutorhelper.mapper;

import com.tutorhelper.dto.tutor.TutorDTO;
import com.tutorhelper.entity.Student;
import com.tutorhelper.entity.Tutor;
import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface TutorMapper {

    @Mapping(target = "studentIds", source = "students", qualifiedByName = "studentsToIds")
    TutorDTO convertToDTO(Tutor tutor);

    @Mapping(target = "students", ignore = true)
    Tutor convertToEntity(TutorDTO tutorDTO);

    @Named("studentsToIds")
    default Set<Long> studentsToIds(Set<Student> students) {
        return students.stream()
            .map(Student::getId)
            .collect(Collectors.toSet());
    }
}
