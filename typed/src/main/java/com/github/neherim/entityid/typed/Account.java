package com.github.neherim.entityid.typed;

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
//@TypeRegistration(basicClass = TypedId.class, userType = TypedIdUserType.class)
public class Account implements BaseAggregate<Account> {
    @Id
    @CustomIdGenerator(sequenceName = "global_seq", allocationSize = 1)
    //@Type(TypedIdUserType.class)
    private TypedId<Account> id;
    @Column(name = "link_person")
    private TypedId<Person> personId;
    private Integer balance;

    public Account(TypedId<Person> personId, Integer balance) {
        this.personId = personId;
        this.balance = balance;
    }
}
