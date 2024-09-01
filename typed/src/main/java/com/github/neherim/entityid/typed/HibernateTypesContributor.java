package com.github.neherim.entityid.typed;

import org.hibernate.boot.model.TypeContributions;
import org.hibernate.boot.model.TypeContributor;
import org.hibernate.service.ServiceRegistry;

/**
 * Регистрирует в Hibernate пользовательский тип TypedId.
 * <p>
 * Для запуска необходимо путь к данному классу указать в файле
 * resources/META-INF/services/org.hibernate.boot.model.TypeContributor
 */
public class HibernateTypesContributor implements TypeContributor {

    @Override
    public void contribute(TypeContributions typeContributions, ServiceRegistry serviceRegistry) {
        typeContributions.contributeType(TypedIdUserType.INSTANCE);
    }
}
