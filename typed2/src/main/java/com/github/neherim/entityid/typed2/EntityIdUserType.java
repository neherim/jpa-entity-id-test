package com.github.neherim.entityid.typed2;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.type.descriptor.jdbc.JdbcType;
import org.hibernate.type.spi.TypeConfiguration;
import org.hibernate.usertype.UserType;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

/**
 * Описание типа EntityId для Hibernate
 *
 * @param <T>
 */
public class EntityIdUserType<T extends EntityId<?>> implements UserType<T> {
    private final Class<T> idWrapperClass;
    private final Constructor<T> idWrapperClassConstructor;
    private final static TypeConfiguration TYPE_CONFIGURATION = new TypeConfiguration();
    private final JdbcType idValueJdbcType;
    private final Class<?> idValueClass;

    public EntityIdUserType(Class<T> idWrapperClass) {
        this.idWrapperClass = idWrapperClass;
        this.idWrapperClassConstructor = getIdWrapperConstructor(idWrapperClass);
        this.idValueClass = this.idWrapperClassConstructor.getParameterTypes()[0];
        this.idValueJdbcType = TYPE_CONFIGURATION.getBasicTypeForJavaType(idValueClass).getJdbcType();
    }

    // Ищем конструктор класса принимающий один аргумент
    private static <T> Constructor<T> getIdWrapperConstructor(Class<T> idClass) {
        for (var constructor : idClass.getDeclaredConstructors()) {
            if (constructor.getParameterCount() == 1) {
                return (Constructor<T>) constructor;
            }
        }
        throw new IllegalArgumentException("The Id wrapper class must have a constructor that "
                + "accepts a single numeric parameter");
    }

    private T newEntityIdInstance(Object value) {
        try {
            return idWrapperClassConstructor.newInstance(value);
        } catch (ReflectiveOperationException ex) {
            throw new IllegalArgumentException("Error when creating id wrapper object", ex);
        }
    }

    @Override
    public int getSqlType() {
        return idValueJdbcType.getJdbcTypeCode();
    }

    @Override
    public Class<T> returnedClass() {
        return idWrapperClass;
    }

    @Override
    public boolean equals(T id1, T id2) {
        return Objects.equals(id1, id2);
    }

    @Override
    public int hashCode(T id) {
        return id.hashCode();
    }

    @Override
    public T nullSafeGet(ResultSet rs, int position, SharedSessionContractImplementor session,
                         Object owner) throws SQLException {
        var id = rs.getObject(position, idValueClass);
        return id != null ? newEntityIdInstance(id) : null;
    }

    @Override
    public void nullSafeSet(PreparedStatement st, T value, int index,
                            SharedSessionContractImplementor session) throws SQLException {
        if (value == null) {
            st.setNull(index, idValueJdbcType.getJdbcTypeCode());
        } else {
            st.setObject(index, value.value(), idValueJdbcType.getJdbcTypeCode());
        }
    }

    @Override
    public T deepCopy(T value) {
        return value;
    }

    @Override
    public boolean isMutable() {
        return false;
    }

    @Override
    public Serializable disassemble(T value) {
        return value;
    }

    @Override
    public T assemble(Serializable cached, Object owner) {
        return (T) cached;
    }
}
