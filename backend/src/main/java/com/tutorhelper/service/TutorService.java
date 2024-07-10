package com.tutorhelper.service;

import com.tutorhelper.config.ErrorMessages;
import com.tutorhelper.dto.tutor.TutorDTO;
import com.tutorhelper.entity.Student;
import com.tutorhelper.entity.Tutor;
import com.tutorhelper.mapper.TutorMapper;
import com.tutorhelper.repository.StudentRepository;
import com.tutorhelper.repository.TutorRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class TutorService {

    private final TutorRepository tutorRepository;
    private final StudentRepository studentRepository;
    private final TutorMapper tutorMapper;

    @Transactional
    public Long createTutor(TutorDTO tutorDTO) {
        Tutor tutor = tutorMapper.convertToEntity(tutorDTO);
        if (tutorDTO.getStudentIds() != null && !tutorDTO.getStudentIds().isEmpty()) {
            Set<Student> students = studentRepository.findAllById(tutorDTO.getStudentIds()).stream().collect(
                Collectors.toSet());
            tutor.setStudents(students);
        }
        tutorRepository.save(tutor);
        return tutor.getId();
    }

    public TutorDTO get(Long id) {
        return tutorRepository.findById(id)
            .map(tutorMapper::convertToDTO)
            .orElseThrow(() -> new EntityNotFoundException(
                String.format(ErrorMessages.USER_NOT_FOUND, id)
            ));
    }

    public List<TutorDTO> getAll() {
        return tutorRepository.findAll()
            .stream()
            .map(tutorMapper::convertToDTO)
            .toList();
    }

    @Transactional
    public void deleteById(Long id) {
        Tutor tutor = tutorRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException(String.format(ErrorMessages.USER_NOT_FOUND, id)));

        // Remove this tutor from all associated students
        for (Student student : tutor.getStudents()) {
            student.getTutors().remove(tutor);
        }

        tutorRepository.delete(tutor);
    }

    public Set<Long> getStudentIdsForTutor(Long tutorId) {
        return tutorRepository.findById(tutorId)
            .map(tutor -> tutor.getStudents().stream()
                .map(Student::getId)
                .collect(Collectors.toSet()))
            .orElseThrow(() -> new EntityNotFoundException(String.format(ErrorMessages.USER_NOT_FOUND, tutorId)));
    }

    @Transactional
    public void updateTutorStudents(Long tutorId, Set<Long> studentIds) {
        Tutor tutor = tutorRepository.findById(tutorId)
            .orElseThrow(() -> new EntityNotFoundException(String.format(ErrorMessages.USER_NOT_FOUND, tutorId)));

        Set<Student> newStudents = studentRepository.findAllById(studentIds).stream().collect(Collectors.toSet());

        // Remove tutor from students that are no longer associated
        tutor.getStudents().stream()
            .filter(student -> !newStudents.contains(student))
            .forEach(student -> student.getTutors().remove(tutor));

        // Update tutor's students
        tutor.setStudents(newStudents);

        // Add tutor to new students
        newStudents.forEach(student -> student.getTutors().add(tutor));

        tutorRepository.save(tutor);
    }
}
