package com.tickethub.enums;

import java.time.Duration;

/**
 * Prioridades con su SLA (propuesta del documento de alcance, horas naturales).
 */
public enum Priority {
    LOW(Duration.ofHours(24), Duration.ofDays(5)),
    MEDIUM(Duration.ofHours(8), Duration.ofDays(3)),
    HIGH(Duration.ofHours(4), Duration.ofHours(24)),
    CRITICAL(Duration.ofHours(1), Duration.ofHours(8));

    private final Duration firstResponse;
    private final Duration resolution;

    Priority(Duration firstResponse, Duration resolution) {
        this.firstResponse = firstResponse;
        this.resolution = resolution;
    }

    public Duration firstResponse() {
        return firstResponse;
    }

    public Duration resolution() {
        return resolution;
    }
}
