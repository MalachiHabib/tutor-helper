package com.tutorhelper.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@DiscriminatorValue("STUDENT")
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class Student extends User {

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
      name = "student_tutor",
      joinColumns = @JoinColumn(name = "student_id"),
      inverseJoinColumns = @JoinColumn(name = "tutor_id"),
      indexes = {
        @Index(name = "idx_student_tutor_student", columnList = "student_id"),
        @Index(name = "idx_student_tutor_tutor", columnList = "tutor_id")
      }
    )
    @ToString.Exclude
    private Set<Tutor> tutors = new HashSet<>();

    @EqualsAndHashCode.Include
    @Override
    public Long getId() {
        return super.getId();
    }
}
