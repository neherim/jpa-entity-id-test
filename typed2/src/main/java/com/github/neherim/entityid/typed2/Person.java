package com.github.neherim.entityid.typed2;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@Table(name = "person")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Person {
    @Id
    @CustomIdGenerator(sequenceName = "global_seq", allocationSize = 1)
    private PersonId id;
    private String name;
    private LocalDate birthday;

    public Person(String name, LocalDate birthday) {
        this.name = name;
        this.birthday = birthday;
    }
}
