package com.tutorhelper.mapper;

import com.tutorhelper.dto.TutorDTO;
import com.tutorhelper.entity.Tutor;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TutorMapper {

    TutorDTO tutorToTutorDTO(Tutor tutor);

    Tutor tutorDTOToTutor(TutorDTO userDTO);

}