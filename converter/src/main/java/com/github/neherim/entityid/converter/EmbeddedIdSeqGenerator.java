package com.github.neherim.entityid.converter;

import org.hibernate.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.generator.EventType;
import org.hibernate.id.enhanced.SequenceStyleGenerator;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;
import org.hibernate.type.spi.TypeConfiguration;

import java.lang.reflect.Constructor;
import java.util.Properties;

/**
 * Генератор id для сущностей. Поддерживает не только базовые числовые типы в качестве id, но и классы обертки над id
 */
public class EmbeddedIdSeqGenerator extends SequenceStyleGenerator {
    private Constructor<?> idWrapperConstructor;
    private final TypeConfiguration typeConfiguration = new TypeConfiguration();

    @Override
    public Object generate(SharedSessionContractImplementor session, Object owner, Object currentValue,
                           EventType eventType) {
        var newValue = super.generate(session, owner, currentValue, eventType);
        try {
            // Оборачиваем в класс обертку наш id
            return idWrapperConstructor.newInstance(newValue);
        } catch (ReflectiveOperationException ex) {
            throw new IllegalArgumentException("Error when creating id wrapper object", ex);
        }
    }

    @Override
    public void configure(Type type, Properties parameters, ServiceRegistry serviceRegistry) throws MappingException {
        // Сохраняем ссылку на конструктор этого объекта обертки id
        this.idWrapperConstructor = getIdWrapperConstructor(type.getReturnedClass());

        // Получаем тип для id поля внутри объекта обертки
        Class<?> innerIdClass = this.idWrapperConstructor.getParameterTypes()[0];
        var overrideType = typeConfiguration.getBasicTypeForJavaType(innerIdClass);

        super.configure(overrideType, parameters, serviceRegistry);
    }

    // Ищем конструктор класса принимающий один аргумент
    private Constructor<?> getIdWrapperConstructor(Class<?> idClass) {
        for (var constructor : idClass.getDeclaredConstructors()) {
            if (constructor.getParameterCount() == 1) {
                return constructor;
            }
        }
        throw new IllegalArgumentException("The Id wrapper class must have a constructor that "
                + "accepts a single numeric parameter");
    }
}
