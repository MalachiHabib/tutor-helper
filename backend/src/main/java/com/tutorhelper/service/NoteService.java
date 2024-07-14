package com.tutorhelper.service;

import com.tutorhelper.dto.note.CreateNoteRequest;
import com.tutorhelper.entity.Note;
import com.tutorhelper.mapper.NoteMapper;
import com.tutorhelper.repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class NoteService {

    private final NoteRepository noteRepository;
    private final NoteMapper noteMapper;


    @Transactional
    @CacheEvict(value = "allNotes", allEntries = true)
    @CachePut(value = "notes", key = "#result")
    public Long createNote(CreateNoteRequest createNoteRequest) {
        Note note = noteMapper.toEntity(createNoteRequest);
        return noteRepository.save(note).getId();
    }
    
}
