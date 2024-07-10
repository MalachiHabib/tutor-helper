package com.tutorhelper.service;

import com.tutorhelper.config.ErrorMessages;
import com.tutorhelper.dto.student.CreateStudentDTO;
import com.tutorhelper.dto.student.StudentResponseDTO;
import com.tutorhelper.dto.student.StudentSummaryDTO;
import com.tutorhelper.dto.student.UpdateStudentDTO;
import com.tutorhelper.entity.Student;
import com.tutorhelper.entity.Tutor;
import com.tutorhelper.mapper.StudentMapper;
import com.tutorhelper.repository.StudentRepository;
import com.tutorhelper.repository.TutorRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final TutorRepository tutorRepository;
    private final StudentMapper studentMapper;

    @Transactional
    public Long createStudent(CreateStudentDTO createStudentDTO) {
        Student student = studentMapper.toEntity(createStudentDTO);
        if (createStudentDTO.getTutorIds() != null && !createStudentDTO.getTutorIds().isEmpty()) {
            Set<Tutor> tutors = new HashSet<>(tutorRepository.findAllById(createStudentDTO.getTutorIds()));
            student.setTutors(tutors);
        }
        studentRepository.save(student);
        return student.getId();
    }

    @Transactional
    @CachePut(value = "students", key = "#studentId")
    public StudentResponseDTO updateStudent(Long studentId, UpdateStudentDTO updateStudentDTO) {
        Student student = studentRepository.findById(studentId)
            .orElseThrow(() -> new EntityNotFoundException("Student not found with id: " + studentId));

        studentMapper.updateEntityFromDTO(updateStudentDTO, student);

        student = studentRepository.save(student);
        return studentMapper.toResponseDTO(student);
    }

    @Cacheable(value = "students", key = "#id")
    public StudentResponseDTO get(Long id) {
        return studentRepository
            .findById(id)
            .map(studentMapper::toResponseDTO)
            .orElseThrow(() -> new EntityNotFoundException(
                String.format(ErrorMessages.USER_NOT_FOUND, id)));
    }

    @Transactional
    @CacheEvict(value = "students", key = "#id")
    public void deleteById(Long id) {
        Student student = studentRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException(String.format(ErrorMessages.USER_NOT_FOUND, id)));

        // Remove this student from all associated tutors
        for (Tutor tutor : student.getTutors()) {
            tutor.getStudents().remove(student);
        }

        studentRepository.delete(student);
    }

    public List<StudentSummaryDTO> getAll() {
        return studentRepository
            .findAll()
            .stream()
            .map(studentMapper::toSummaryDTO)
            .toList();
    }

    public Set<Long> getTutorIdsForStudent(Long studentId) {
        return studentRepository.findById(studentId)
            .map(student -> student.getTutors().stream()
                .map(Tutor::getId)
                .collect(Collectors.toSet()))
            .orElseThrow(() -> new EntityNotFoundException(String.format(ErrorMessages.USER_NOT_FOUND, studentId)));
    }

    @Transactional
    public void updateStudentTutors(Long studentId, Set<Long> tutorIds) {
        Student student = studentRepository.findById(studentId)
            .orElseThrow(() -> new EntityNotFoundException(String.format(ErrorMessages.USER_NOT_FOUND, studentId)));

        Set<Tutor> newTutors = new HashSet<>(tutorRepository.findAllById(tutorIds));

        // Remove student from tutors that are no longer associated
        student.getTutors().stream()
            .filter(tutor -> !newTutors.contains(tutor))
            .forEach(tutor -> tutor.getStudents().remove(student));

        // Update student's tutors
        student.setTutors(newTutors);

        // Add student to new tutors
        newTutors.forEach(tutor -> tutor.getStudents().add(student));

        studentRepository.save(student);
    }
}
