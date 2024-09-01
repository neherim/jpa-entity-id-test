package com.github.neherim.entityid.typed;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.usertype.UserType;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Objects;

/**
 * Описание типа TypedId для Hibernate
 */
public class TypedIdUserType implements UserType<TypedId> {

    public static final TypedIdUserType INSTANCE = new TypedIdUserType();

    @Override
    public int getSqlType() {
        // SQL тип Id в базе данных
        return Types.BIGINT;
    }

    @Override
    public Class<TypedId> returnedClass() {
        return TypedId.class;
    }

    @Override
    public boolean equals(TypedId id1, TypedId id2) {
        return Objects.equals(id1.value(), id2.value());
    }

    @Override
    public int hashCode(TypedId id) {
        return id.hashCode();
    }

    @Override
    public TypedId nullSafeGet(ResultSet rs, int position, SharedSessionContractImplementor session,
                               Object owner) throws SQLException {
        var id = rs.getObject(position, Long.class);
        return id != null ? new TypedId<>(id) : null;
    }

    @Override
    public void nullSafeSet(PreparedStatement st, TypedId value, int index,
                            SharedSessionContractImplementor session) throws SQLException {
        if (value == null) {
            st.setNull(index, Types.BIGINT);
        } else {
            st.setLong(index, value.value());
        }
    }

    @Override
    public TypedId deepCopy(TypedId value) {
        return value;
    }

    @Override
    public boolean isMutable() {
        return false;
    }

    @Override
    public Serializable disassemble(TypedId value) {
        return value;
    }

    @Override
    public TypedId assemble(Serializable cached, Object owner) {
        return (TypedId) cached;
    }
}
