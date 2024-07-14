package com.tutorhelper.mapper;

import com.tutorhelper.dto.note.CreateNoteRequest;
import com.tutorhelper.entity.Note;
import com.tutorhelper.entity.Student;
import com.tutorhelper.entity.Tutor;
import com.tutorhelper.repository.StudentRepository;
import com.tutorhelper.repository.TutorRepository;
import com.tutorhelper.util.ExceptionUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NoteMapper {

    private final StudentRepository studentRepository;
    private final TutorRepository tutorRepository;

    public Note toEntity(CreateNoteRequest createNoteRequest) {
        if (createNoteRequest == null) {
            return null;
        }

        Note note = new Note();
        note.setContent(createNoteRequest.getContent());
        note.setType(createNoteRequest.getType());
        note.setAccessibility(createNoteRequest.getAccessibility());

        Long studentId = createNoteRequest.getStudentId();
        Long tutorId = createNoteRequest.getTutorId();

        if (studentId != null) {
            Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> ExceptionUtils.entityNotFoundException(Student.class, studentId));
            note.setStudent(student);
        }

        if (tutorId != null) {
            Tutor tutor = tutorRepository.findById(tutorId)
                .orElseThrow(() -> ExceptionUtils.entityNotFoundException(Tutor.class, tutorId));
            note.setTutor(tutor);
        }

        return note;
    }
}
