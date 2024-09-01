package com.github.neherim.entityid.typed2;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Transactional
@SpringBootTest
public class TypedIdTest {
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    PersonRepository personRepository;
    @Autowired
    EntityManager entityManager;

    @Test
    void test1() {
        var person = personRepository.save(new Person("Mike", LocalDate.of(1988, 1, 1)));
        accountRepository.save(new Account(person.getId(), 900));
        accountRepository.save(new Account(person.getId(), 80));

        entityManager.flush();

        var accounts = accountRepository.findAllByPersonId(person.getId());
        assertEquals(2, accounts.size());
    }

    @Test
    void test2() {
        record Total(String name, Long sum) {
        }
        var person1 = personRepository.save(new Person("Mike", LocalDate.of(1988, 1, 1)));
        accountRepository.save(new Account(person1.getId(), 900));
        accountRepository.save(new Account(person1.getId(), 80));

        var person2 = personRepository.save(new Person("Bill", LocalDate.of(1990, 1, 1)));
        accountRepository.save(new Account(person2.getId(), 200));
        accountRepository.save(new Account(person2.getId(), 30));

        var totals = entityManager.createQuery("""
                        select p.name, sum(a.balance)
                        from Account a, Person p
                        where
                            a.personId = p.id
                        group by
                            p.name
                        """, Total.class)
                .getResultList();

        entityManager.flush();

        assertEquals(2, totals.size());
    }
}
