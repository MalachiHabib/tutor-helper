package com.tutorhelper.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@DiscriminatorValue("TUTOR")
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class Tutor extends User {

    @ManyToMany(mappedBy = "tutors", fetch = FetchType.EAGER)
    @ToString.Exclude
    private Set<Student> students = new HashSet<>();

    @EqualsAndHashCode.Include
    @Override
    public Long getId() {
        return super.getId();
    }
}
