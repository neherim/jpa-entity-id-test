package com.github.neherim.entityid.typed;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, TypedId<Person>> {
}