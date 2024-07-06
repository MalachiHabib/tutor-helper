package com.tutorhelper.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity(
  name = "user_principle"
)
@Table(
  name = "user_principle"
)
public class User {
    @Id
    @SequenceGenerator(
      name = "user_principle_sequence",
      sequenceName = "user_principle_sequence",
      allocationSize = 1
    )
    @GeneratedValue(
      strategy = GenerationType.SEQUENCE,
      generator = "user_principle_sequence"
    )
    @Column(
      name = "id",
      updatable = false
    )
    private Long id;
    @Column(
      name = "name",
      nullable = false
    )
    private String name;

    public User() {
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public void setName(final String name) {
        this.name = name;
    }
}
