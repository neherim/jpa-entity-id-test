package com.github.neherim.entityid.typed2;

import org.hibernate.boot.model.TypeContributions;
import org.hibernate.boot.model.TypeContributor;
import org.hibernate.service.ServiceRegistry;

/**
 * Регистрирует в Hibernate пользовательские типы Id.
 * <p>
 * Для запуска необходимо путь к данному классу указать в файле
 * resources/META-INF/services/org.hibernate.boot.model.TypeContributor
 */
public class HibernateTypesContributor implements TypeContributor {

    @Override
    public void contribute(TypeContributions typeContributions, ServiceRegistry serviceRegistry) {
        typeContributions.contributeType(new EntityIdUserType<>(PersonId.class));
        typeContributions.contributeType(new EntityIdUserType<>(AccountId.class));
    }
}
