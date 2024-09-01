package com.github.neherim.entityid.converter;

import org.hibernate.annotations.IdGeneratorType;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@IdGeneratorType(EmbeddedIdSeqGenerator.class)
@Retention(RUNTIME)
@Target({METHOD, FIELD})
public @interface CustomIdGenerator {
    String sequenceName() default "";

    int allocationSize() default 50;
}


