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
public interface StudentRepository extends UserBaseRepository<Student> {

    @Query("SELECT DISTINCT s FROM Student s LEFT JOIN FETCH s.tutors")
    List<Student> findAllWithTutors();

    @EntityGraph(attributePaths = {"tutors"})
    @NonNull
    Optional<Student> findById(@NonNull Long id);

}
