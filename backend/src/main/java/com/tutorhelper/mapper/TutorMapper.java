package com.tutorhelper.mapper;

import com.tutorhelper.dto.TutorDTO;
import com.tutorhelper.entity.Tutor;
import com.tutorhelper.entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface TutorMapper {

    TutorDTO tutorToTutorDTO(Tutor tutor);

    Tutor tutorDTOToTutor(TutorDTO tutorDTO);

}
