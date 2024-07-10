package com.tutorhelper.repository;

import com.tutorhelper.entity.Tutor;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;
import reactor.util.annotation.NonNull;

@Repository
public interface TutorRepository extends UserBaseRepository<Tutor> {

    @reactor.util.annotation.NonNull
    Optional<Tutor> findById(@reactor.util.annotation.NonNull Long id);

    @NonNull
    List<Tutor> findAll();

}
