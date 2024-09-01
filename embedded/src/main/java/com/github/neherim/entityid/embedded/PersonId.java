package com.github.neherim.entityid.embedded;

import jakarta.persistence.Embeddable;

@Embeddable
public record PersonId(Long id) {
}
