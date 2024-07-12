package com.tutorhelper.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "tutors")
@DiscriminatorValue("TUTOR")
@PrimaryKeyJoinColumn(name = "user_id")
public class Tutor extends User {

    @ManyToMany(mappedBy = "tutors", fetch = FetchType.LAZY)
    private Set<Student> students = new HashSet<>();

    public void removeStudent(Student student) {
        this.getStudents().remove(student);
    }
}
