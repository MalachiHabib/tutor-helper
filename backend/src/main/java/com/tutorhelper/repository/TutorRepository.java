package com.tutorhelper.repository;

import com.tutorhelper.entity.Student;
import com.tutorhelper.entity.Tutor;
import io.micrometer.common.lang.NonNull;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TutorRepository extends UserBaseRepository<Tutor> {

    @Query("SELECT DISTINCT s FROM Tutor s LEFT JOIN FETCH s.students")
    List<Tutor> findAllWithStudents();

    @EntityGraph(attributePaths = {"students"})
    @NonNull
    Optional<Tutor> findById(@NonNull Long id);

}
