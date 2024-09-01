package com.github.neherim.entityid.typed;

public interface BaseAggregate<T> {
    TypedId<T> getId();
}

