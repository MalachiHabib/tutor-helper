package com.tutorhelper.service;

import com.tutorhelper.dto.student.CreateStudentDTO;
import com.tutorhelper.dto.student.StudentResponseDTO;
import com.tutorhelper.dto.student.StudentSummaryDTO;
import com.tutorhelper.dto.student.UpdateStudentDTO;
import com.tutorhelper.entity.Student;
import com.tutorhelper.entity.Tutor;
import com.tutorhelper.mapper.StudentMapper;
import com.tutorhelper.repository.StudentRepository;
import com.tutorhelper.repository.TutorRepository;
import com.tutorhelper.utils.ExceptionUtils;
import com.tutorhelper.utils.IntuitiveCollectionUtils;
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
    @CacheEvict(value = "allStudents", allEntries = true)
    @CachePut(value = "students", key = "#result")
    public Long createStudent(CreateStudentDTO createStudentDTO) {
        Student student = studentMapper.toEntity(createStudentDTO);
        assignTutorsToStudent(student, createStudentDTO.getTutorIds());
        return studentRepository.save(student).getId();
    }

    private void assignTutorsToStudent(Student student, Set<Long> tutorIds) {
        if (IntuitiveCollectionUtils.isNotEmpty(tutorIds)) {
            Set<Tutor> tutors = new HashSet<>(tutorRepository.findAllById(tutorIds));
            student.setTutors(tutors);
        }
    }

    @Transactional
    @CachePut(value = "students", key = "#studentId")
    @CacheEvict(value = "allStudents", allEntries = true)
    public StudentResponseDTO updateStudent(Long studentId, UpdateStudentDTO updateStudentDTO) {
        Student student = studentRepository.findById(studentId)
            .orElseThrow(ExceptionUtils.entityNotFoundExceptionSupplier(Student.class, studentId));

        studentMapper.updateEntityFromDTO(updateStudentDTO, student);
        student = studentRepository.save(student);
        return studentMapper.toResponseDTO(student);
    }

    @Cacheable(value = "students", key = "#id")
    public StudentResponseDTO get(Long id) {
        return studentRepository
            .findById(id)
            .map(studentMapper::toResponseDTO)
            .orElseThrow(ExceptionUtils.entityNotFoundExceptionSupplier(Student.class, id));
    }

    @Transactional
    @CacheEvict(value = {"students", "allStudents"}, allEntries = true)
    public void deleteById(Long id) {
        studentRepository.findById(id)
            .ifPresentOrElse(
                this::deleteStudent,
                () -> ExceptionUtils.throwEntityNotFoundException(Student.class, id)
            );
    }

    private void deleteStudent(Student student) {
        student.getTutors().forEach(tutor -> tutor.removeStudent(student));
        studentRepository.delete(student);
    }

    @Cacheable(value = "allStudents")
    public List<StudentSummaryDTO> getAll() {
        return studentRepository
            .findAll()
            .stream()
            .map(studentMapper::toSummaryDTO)
            .toList();
    }

    @Cacheable(value = "students", key = "#studentId")
    public Set<Long> getTutorIdsForStudent(Long studentId) {
        return studentRepository.findById(studentId)
            .map(student -> student.getTutors().stream()
                .map(Tutor::getId)
                .collect(Collectors.toSet()))
            .orElseThrow(ExceptionUtils.entityNotFoundExceptionSupplier(Student.class, studentId));
    }
}
