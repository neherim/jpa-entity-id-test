package com.github.neherim.entityid.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class PersonIdConverter implements AttributeConverter<PersonId, Long> {

    @Override
    public Long convertToDatabaseColumn(PersonId attribute) {
        return attribute.id();
    }

    @Override
    public PersonId convertToEntityAttribute(Long dbData) {
        return new PersonId(dbData);
    }
}
