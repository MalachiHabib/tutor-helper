package com.tutorhelper.service;

import com.tutorhelper.config.ErrorMessages;
import com.tutorhelper.dto.StudentDTO;
import com.tutorhelper.dto.TutorDTO;
import com.tutorhelper.entity.Student;
import com.tutorhelper.entity.Tutor;
import com.tutorhelper.mapper.StudentMapper;
import com.tutorhelper.mapper.TutorMapper;
import com.tutorhelper.repository.StudentRepository;
import com.tutorhelper.repository.TutorRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TutorService {

    private final TutorRepository tutorRepository;
    private final TutorMapper tutorMapper;

    public Long createTutor(TutorDTO tutorDTO) {
        Tutor tutor = tutorMapper.tutorDTOToTutor(tutorDTO);
        tutorRepository.save(tutor);
        return tutor.getId();
    }

    public TutorDTO get(Long id) {
        return tutorRepository.findById(id)
          .map(tutorMapper::tutorToTutorDTO)
          .orElseThrow(() -> new EntityNotFoundException(
            String.format(ErrorMessages.USER_NOT_FOUND, id)
          ));
    }

    public List<TutorDTO> getAll() {
        return tutorRepository.findAllWithStudents()
          .stream()
          .map(tutorMapper::tutorToTutorDTO)
          .toList();
    }

    public void deleteById(Long id) {
        if (!tutorRepository.existsById(id)) {
            // todo: update the error messages to take a type (e.g. tutor)
            throw new EntityNotFoundException(String.format(ErrorMessages.USER_NOT_FOUND, id));
        }
        tutorRepository.deleteById(id);
    }

    public List<Long> getStudentIdsForTutor(Long tutorId) {
        return tutorRepository.findById(tutorId)
          .map(tutor -> tutor.getStudents().stream()
            .map(Student::getId)
            .collect(Collectors.toList()))
          .orElseThrow(() -> new EntityNotFoundException(String.format(ErrorMessages.USER_NOT_FOUND, tutorId)));
    }

}
