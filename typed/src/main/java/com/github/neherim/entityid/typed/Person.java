package com.github.neherim.entityid.typed;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@Table(name = "person")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Person implements BaseAggregate<Person> {
    @Id
    @CustomIdGenerator(sequenceName = "global_seq", allocationSize = 1)
    private TypedId<Person> id;
    private String name;
    private LocalDate birthday;

    public Person(String name, LocalDate birthday) {
        this.name = name;
        this.birthday = birthday;
    }
}
