package com.tutorhelper.service;

import com.tutorhelper.config.ErrorMessages;
import com.tutorhelper.dto.TutorDTO;
import com.tutorhelper.entity.Tutor;
import com.tutorhelper.mapper.TutorMapper;
import com.tutorhelper.repository.TutorRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TutorService {

    private final TutorRepository tutorRepository;
    private final TutorMapper tutorMapper;

    public Long createTutor(TutorDTO tutorDTO) {
        Tutor user = tutorMapper.tutorDTOToTutor(tutorDTO);
        tutorRepository.save(user);
        return user.getId();
    }

    public TutorDTO get(Long id) {
        return tutorRepository.findById(id)
            .map(tutorMapper::tutorToTutorDTO)
            .orElseThrow(() -> new EntityNotFoundException(
                String.format(ErrorMessages.USER_NOT_FOUND, id)
            ));
    }

    public List<TutorDTO> getAll() {
        List<TutorDTO> tutorDTOS = tutorRepository.findAll()
            .stream()
            .map(tutorMapper::tutorToTutorDTO)
            .toList();
        return tutorDTOS;
    }

    public void deleteById(Long id) {
        if (!tutorRepository.existsById(id)) {
            throw new EntityNotFoundException(String.format(ErrorMessages.USER_NOT_FOUND, id));
        }
        tutorRepository.deleteById(id);
    }

}
