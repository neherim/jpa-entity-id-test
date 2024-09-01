package com.github.neherim.entityid.converter;

import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Person {
    @Id
    @Convert(converter = PersonIdConverter.class)
    private PersonId id;
    private String name;
    private LocalDate birthday;

    public Person(PersonId personId, String name, LocalDate birthday) {
        this.id = personId;
        this.name = name;
        this.birthday = birthday;
    }
}
