package com.github.neherim.entityid.typed;

import org.hibernate.annotations.IdGeneratorType;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@IdGeneratorType(EmbeddedIdSeqGenerator.class)
@Retention(RUNTIME)
@Target({METHOD, FIELD, PACKAGE})
public @interface CustomIdGenerator {
    String sequenceName() default "";

    int allocationSize() default 50;
}


