package com.github.neherim.entityid.typed;

import java.io.Serializable;


public record TypedId<T>(Long value) implements Serializable {
}
