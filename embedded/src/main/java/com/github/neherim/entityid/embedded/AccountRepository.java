package com.github.neherim.entityid.embedded;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, AccountId> {
    List<Account> findAllByPersonId(PersonId id);
}