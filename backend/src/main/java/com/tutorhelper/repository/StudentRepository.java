package com.tutorhelper.repository;

import com.tutorhelper.entity.Student;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;
import reactor.util.annotation.NonNull;

@Repository
public interface StudentRepository extends UserBaseRepository<Student> {

    @NonNull
    Optional<Student> findById(@NonNull Long id);

    @NonNull
    List<Student> findAll();
    
}
