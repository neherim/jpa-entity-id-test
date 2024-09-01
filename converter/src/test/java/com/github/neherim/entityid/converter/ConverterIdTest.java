package com.github.neherim.entityid.converter;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@Transactional
@SpringBootTest
public class ConverterIdTest {
    @Autowired
    PersonRepository personRepository;
    @Autowired
    EntityManager entityManager;

    @Test
    void failedTest() {
        personRepository.save(new Person(new PersonId(1L), "Mike", LocalDate.of(1988, 1, 1)));
        entityManager.flush();
    }
}
