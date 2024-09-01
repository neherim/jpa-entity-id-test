package com.github.neherim.entityid.typed;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, TypedId<Account>> {
    List<Account> findAllByPersonId(TypedId<Person> id);
}