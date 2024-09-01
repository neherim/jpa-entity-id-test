package com.github.neherim.entityid.embedded;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Account implements BaseAggregate<AccountId> {
    /**
     * Для генерации значений ID мы вынуждены использовать deprecated @GenericGenerator вместо @CustomIdGenerator
     * из за баги в Hibernate <a href="https://hibernate.atlassian.net/browse/HHH-18276">HHH-18276</a>
     */
    @EmbeddedId
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq")
//    @GenericGenerator(name = "global_seq", type = EmbeddedIdSeqGenerator.class,
//            parameters = {
//                    @Parameter(name = "sequence_name", value = "global_seq"),
//                    @Parameter(name = "increment_size", value = "1"),
//            })
    private AccountId id;
    @AttributeOverride(name = "id", column = @Column(name = "link_person"))
    private PersonId personId;
    private Integer balance;

    public Account(PersonId personId, Integer balance) {
        this.personId = personId;
        this.balance = balance;
    }
}
