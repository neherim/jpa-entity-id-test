package com.github.neherim.entityid.typed2;

import java.io.Serializable;

public interface EntityId<T> extends Serializable {
    T value();
}
