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
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;
    private final TutorMapper tutorMapper;

    public Long createStudent(StudentDTO studentDTO) {
        Student student = studentMapper.studentDTOToStudent(studentDTO);
        studentRepository.save(student);
        return student.getId();
    }

    public StudentDTO get(Long id) {
        return studentRepository.findById(id)
          .map(studentMapper::studentToStudentDTO)
          .orElseThrow(() -> new EntityNotFoundException(
            String.format(ErrorMessages.USER_NOT_FOUND, id)
          ));
    }

    public List<StudentDTO> getAll() {
        return studentRepository.findAllWithTutors()
          .stream()
          .map(studentMapper::studentToStudentDTO)
          .toList();
    }

    public void deleteById(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new EntityNotFoundException(String.format(ErrorMessages.USER_NOT_FOUND, id));
        }
        studentRepository.deleteById(id);
    }

    public List<Long> getTutorIdsForStudent(Long studentId) {
        return studentRepository.findById(studentId)
          .map(student -> student.getTutors().stream()
            .map(Tutor::getId)
            .collect(Collectors.toList()))
          .orElseThrow(() -> new EntityNotFoundException("Student not found with id: " + studentId));
    }

}
