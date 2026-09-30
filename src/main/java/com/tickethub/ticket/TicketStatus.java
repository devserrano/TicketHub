package com.tickethub.ticket;

import java.util.EnumSet;
import java.util.Set;

/**
 * Estados del ticket y las transiciones permitidas (sección 5 del documento de alcance).
 * Cualquier transición que no esté aquí se rechaza con 409 Conflict.
 */
public enum TicketStatus {
    OPEN,
    IN_PROGRESS,
    ON_HOLD,
    RESOLVED,
    CLOSED,
    CANCELLED;

    public Set<TicketStatus> allowedNext() {
        return switch (this) {
            case OPEN -> EnumSet.of(IN_PROGRESS, CANCELLED);
            case IN_PROGRESS -> EnumSet.of(ON_HOLD, RESOLVED);
            case ON_HOLD -> EnumSet.of(IN_PROGRESS);
            case RESOLVED -> EnumSet.of(CLOSED, IN_PROGRESS); // IN_PROGRESS = reabrir
            case CLOSED, CANCELLED -> EnumSet.noneOf(TicketStatus.class);
        };
    }

    public boolean canTransitionTo(TicketStatus next) {
        return allowedNext().contains(next);
    }

    public boolean isFinal() {
        return this == CLOSED || this == CANCELLED;
    }
}
