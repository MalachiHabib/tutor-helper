package com.tutorhelper.service;

import com.tutorhelper.entity.Student;
import com.tutorhelper.entity.Tutor;
import com.tutorhelper.repository.StudentRepository;
import com.tutorhelper.repository.TutorRepository;
import com.tutorhelper.utils.ExceptionUtils;
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
            .orElseThrow(ExceptionUtils.entityNotFoundExceptionSupplier(Student.class, studentId));

        Tutor tutor = tutorRepository.findById(tutorId)
            .orElseThrow(ExceptionUtils.entityNotFoundExceptionSupplier(Tutor.class, tutorId));

        if (student.getTutors().contains(tutor)) {
            ExceptionUtils.throwAssociationAlreadyExistsException(Student.class, studentId, Tutor.class, tutorId);
        }

        student.getTutors().add(tutor);
        tutor.getStudents().add(student);

        studentRepository.save(student);
        tutorRepository.save(tutor);
    }

    @Transactional
    public void disassociateStudentAndTutor(Long studentId, Long tutorId) {
        Student student = studentRepository.findById(studentId)
            .orElseThrow(ExceptionUtils.entityNotFoundExceptionSupplier(Student.class, studentId));

        Tutor tutor = tutorRepository.findById(tutorId)
            .orElseThrow(ExceptionUtils.entityNotFoundExceptionSupplier(Tutor.class, tutorId));

        if (!student.getTutors().contains(tutor)) {
            throw ExceptionUtils.associationNotFoundException(Student.class, studentId, Tutor.class, tutorId);
        }

        student.getTutors().remove(tutor);
        tutor.getStudents().remove(student);

        studentRepository.save(student);
        tutorRepository.save(tutor);
    }
}
