package com.tutorhelper.mapper;

import com.tutorhelper.dto.TutorDTO;
import com.tutorhelper.entity.Tutor;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-07-07T13:11:14+0930",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.3 (Amazon.com Inc.)"
)
@Component
public class TutorMapperImpl implements TutorMapper {

    @Override
    public TutorDTO tutorToTutorDTO(Tutor tutor) {
        if ( tutor == null ) {
            return null;
        }

        TutorDTO tutorDTO = new TutorDTO();

        tutorDTO.setId( tutor.getId() );
        tutorDTO.setFirstName( tutor.getFirstName() );
        tutorDTO.setLastName( tutor.getLastName() );
        tutorDTO.setEmail( tutor.getEmail() );
        tutorDTO.setPhone( tutor.getPhone() );

        return tutorDTO;
    }

    @Override
    public Tutor tutorDTOToTutor(TutorDTO userDTO) {
        if ( userDTO == null ) {
            return null;
        }

        Tutor tutor = new Tutor();

        tutor.setId( userDTO.getId() );
        tutor.setFirstName( userDTO.getFirstName() );
        tutor.setLastName( userDTO.getLastName() );
        tutor.setEmail( userDTO.getEmail() );
        tutor.setPhone( userDTO.getPhone() );

        return tutor;
    }
}
