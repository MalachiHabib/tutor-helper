package com.tutorhelper.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "students")
@DiscriminatorValue("STUDENT")
@PrimaryKeyJoinColumn(name = "user_id")
@Getter
@Setter
@NoArgsConstructor
public class Student extends User {

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "student_tutor",
        joinColumns = @JoinColumn(name = "student_id"),
        inverseJoinColumns = @JoinColumn(name = "tutor_id"),
        indexes = {
            @Index(name = "idx_student_tutor_student", columnList = "student_id"),
            @Index(name = "idx_student_tutor_tutor", columnList = "tutor_id")
        }
    )
    private Set<Tutor> tutors = new HashSet<>();
}
