package com.tutorhelper.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.tutorhelper.dto.tutor.CreateTutorDTO;
import com.tutorhelper.dto.tutor.TutorResponseDTO;
import com.tutorhelper.dto.tutor.TutorSummaryDTO;
import com.tutorhelper.dto.tutor.UpdateTutorDTO;
import com.tutorhelper.entity.Student;
import com.tutorhelper.entity.Tutor;
import com.tutorhelper.mapper.TutorMapper;
import com.tutorhelper.repository.StudentRepository;
import com.tutorhelper.repository.TutorRepository;
import com.tutorhelper.utils.ExceptionUtils;
import com.tutorhelper.utils.IntuitiveCollectionUtils;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class TutorService {

    private final TutorRepository tutorRepository;
    private final StudentRepository studentRepository;
    private final TutorMapper tutorMapper;

    @Transactional
    @CacheEvict(value = "allTutors", allEntries = true)
    @CachePut(value = "tutors", key = "#result")
    public Long createTutor(CreateTutorDTO createTutorDTO) {
        Tutor tutor = tutorMapper.toEntity(createTutorDTO);
        assignStudentsToTutor(tutor, createTutorDTO.getStudentIds());
        return tutorRepository.save(tutor).getId();
    }

    private void assignStudentsToTutor(Tutor tutor, Set<Long> studentIds) {
        if (IntuitiveCollectionUtils.isNotEmpty(studentIds)) {
            Set<Student> students = new HashSet<>(studentRepository.findAllById(studentIds));
            tutor.setStudents(students);
        }
    }

    @Transactional
    @CachePut(value = "tutors", key = "#tutorId")
    @CacheEvict(value = "allTutors", allEntries = true)
    public TutorResponseDTO updateTutor(Long tutorId, UpdateTutorDTO updateTutorDTO) {
        Tutor tutor = tutorRepository.findById(tutorId)
            .orElseThrow(ExceptionUtils.entityNotFoundExceptionSupplier(Tutor.class, tutorId));

        tutorMapper.updateEntityFromDTO(updateTutorDTO, tutor);
        tutor = tutorRepository.save(tutor);
        return tutorMapper.toResponseDTO(tutor);
    }

    @Cacheable(value = "tutors", key = "#id")
    public TutorResponseDTO get(Long id) {
        return tutorRepository
            .findById(id)
            .map(tutorMapper::toResponseDTO)
            .orElseThrow(ExceptionUtils.entityNotFoundExceptionSupplier(Tutor.class, id));
    }

    @Transactional
    @CacheEvict(value = {"tutors", "allTutors"}, allEntries = true)
    public void deleteById(Long id) {
        tutorRepository.findById(id)
            .ifPresentOrElse(
                this::deleteTutor,
                () -> ExceptionUtils.throwEntityNotFoundException(Tutor.class, id)
            );
    }

    private void deleteTutor(Tutor tutor) {
        tutor.getStudents().forEach(student -> student.getTutors().remove(tutor));
        tutorRepository.delete(tutor);
    }

    @Cacheable(value = "allTutors")
    public List<TutorSummaryDTO> getAll() {
        return tutorRepository
            .findAll()
            .stream()
            .map(tutorMapper::toSummaryDTO)
            .toList();
    }

    @Cacheable(value = "tutors", key = "#tutorId")
    public Set<Long> getStudentIdsForTutor(Long tutorId) {
        return tutorRepository.findById(tutorId)
            .map(tutor -> tutor.getStudents().stream()
                .map(Student::getId)
                .collect(Collectors.toSet()))
            .orElseThrow(ExceptionUtils.entityNotFoundExceptionSupplier(Tutor.class, tutorId));
    }
}
