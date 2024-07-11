package com.tutorhelper.service;

import com.tutorhelper.config.ErrorMessages;
import com.tutorhelper.dto.tutor.CreateTutorDTO;
import com.tutorhelper.dto.tutor.TutorResponseDTO;
import com.tutorhelper.dto.tutor.TutorSummaryDTO;
import com.tutorhelper.dto.tutor.UpdateTutorDTO;
import com.tutorhelper.entity.Student;
import com.tutorhelper.entity.Tutor;
import com.tutorhelper.mapper.TutorMapper;
import com.tutorhelper.repository.StudentRepository;
import com.tutorhelper.repository.TutorRepository;
import com.tutorhelper.util.IntuitiveCollectionUtils;
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
        return saveAndReturnTutorId(tutor);
    }

    private void assignStudentsToTutor(Tutor tutor, Set<Long> studentIds) {
        if (IntuitiveCollectionUtils.isNotEmpty(studentIds)) {
            Set<Student> students = new HashSet<>(studentRepository.findAllById(studentIds));
            tutor.setStudents(students);
        }
    }

    private Long saveAndReturnTutorId(Tutor tutor) {
        return tutorRepository.save(tutor).getId();
    }

    @Transactional
    @CachePut(value = "tutors", key = "#tutorId")
    @CacheEvict(value = "allTutors", allEntries = true)
    public TutorResponseDTO updateTutor(Long tutorId, UpdateTutorDTO updateTutorDTO) {
        Tutor tutor = tutorRepository.findById(tutorId)
            .orElseThrow(() -> new EntityNotFoundException(String.format(ErrorMessages.USER_NOT_FOUND, tutorId)));

        tutorMapper.updateEntityFromDTO(updateTutorDTO, tutor);
        tutor = tutorRepository.save(tutor);
        return tutorMapper.toResponseDTO(tutor);
    }

    @Cacheable(value = "tutors", key = "#id")
    public TutorResponseDTO get(Long id) {
        return tutorRepository.findById(id)
            .map(tutorMapper::toResponseDTO)
            .orElseThrow(() -> new EntityNotFoundException(
                String.format(ErrorMessages.USER_NOT_FOUND, id)));
    }

    @Transactional
    @CacheEvict(value = {"tutors", "allTutors"}, allEntries = true)
    public void deleteById(Long id) {
        Tutor tutor = tutorRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException(String.format(ErrorMessages.USER_NOT_FOUND, id)));

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
            .orElseThrow(() -> new EntityNotFoundException(String.format(ErrorMessages.USER_NOT_FOUND, tutorId)));
    }

    @Transactional
    @CacheEvict(value = {"tutors", "allTutors"}, allEntries = true)
    public void updateTutorStudents(Long tutorId, Set<Long> studentIds) {
        Tutor tutor = tutorRepository.findById(tutorId)
            .orElseThrow(() -> new EntityNotFoundException(String.format(ErrorMessages.USER_NOT_FOUND, tutorId)));

        Set<Student> newStudents = new HashSet<>(studentRepository.findAllById(studentIds));

        tutor.getStudents().stream()
            .filter(student -> !newStudents.contains(student))
            .forEach(student -> student.getTutors().remove(tutor));

        tutor.setStudents(newStudents);
        newStudents.forEach(student -> student.getTutors().add(tutor));

        tutorRepository.save(tutor);
    }
}
