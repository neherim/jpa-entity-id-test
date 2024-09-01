package com.github.neherim.entityid.embedded;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Person implements BaseAggregate<PersonId> {
    @EmbeddedId
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq")
//    @GenericGenerator(name = "global_seq", type = EmbeddedIdSeqGenerator.class,
//            parameters = {
//                    @Parameter(name = "sequence_name", value = "global_seq"),
//                    @Parameter(name = "increment_size", value = "1"),
//            })
    private PersonId id;
    private String name;
    private LocalDate birthday;

    public Person(String name, LocalDate birthday) {
        this.name = name;
        this.birthday = birthday;
    }
}
