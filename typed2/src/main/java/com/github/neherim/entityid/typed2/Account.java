package com.github.neherim.entityid.typed2;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "account")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Account {
    @Id
    @CustomIdGenerator(sequenceName = "global_seq", allocationSize = 1)
    private AccountId id;
    @Column(name = "link_person")
    private PersonId personId;
    private Integer balance;

    public Account(PersonId personId, Integer balance) {
        this.personId = personId;
        this.balance = balance;
    }
}
