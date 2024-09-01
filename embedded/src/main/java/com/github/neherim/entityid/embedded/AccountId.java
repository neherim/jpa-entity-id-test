package com.github.neherim.entityid.embedded;

import jakarta.persistence.Embeddable;

@Embeddable
public record AccountId(Long id) {
}

