package com.tutorhelper.service;

import com.tutorhelper.entity.Student;
import com.tutorhelper.entity.Tutor;
import com.tutorhelper.repository.StudentRepository;
import com.tutorhelper.repository.TutorRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class UserAssociationService {

    private final StudentRepository studentRepository;
    private final TutorRepository tutorRepository;

    @Transactional
    public void associateStudentAndTutor(Long studentId, Long tutorId) {
        Student student = studentRepository.findById(studentId)
          .orElseThrow(() -> new EntityNotFoundException("Student not found with id: " + studentId));

        Tutor tutor = tutorRepository.findById(tutorId)
          .orElseThrow(() -> new EntityNotFoundException("Tutor not found with id: " + tutorId));

        student.getTutors().add(tutor);
        tutor.getStudents().add(student);

        studentRepository.save(student);
        tutorRepository.save(tutor);
    }

    @Transactional
    public void disassociateStudentAndTutor(Long studentId, Long tutorId) {
        Student student = studentRepository.findById(studentId)
          .orElseThrow(() -> new EntityNotFoundException("Student not found with id: " + studentId));

        Tutor tutor = tutorRepository.findById(tutorId)
          .orElseThrow(() -> new EntityNotFoundException("Tutor not found with id: " + tutorId));

        student.getTutors().remove(tutor);
        tutor.getStudents().remove(student);

        studentRepository.save(student);
        tutorRepository.save(tutor);
    }
}
