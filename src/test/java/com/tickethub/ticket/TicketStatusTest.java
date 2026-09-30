package com.tickethub.ticket;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.assertj.core.api.Assertions.assertThat;

/** Pruebas unitarias del flujo de estados (sección 5 del alcance). No necesitan BD. */
class TicketStatusTest {

    @Test
    void openCanGoToInProgressOrCancelled() {
        assertThat(TicketStatus.OPEN.allowedNext())
                .containsExactlyInAnyOrder(TicketStatus.IN_PROGRESS, TicketStatus.CANCELLED);
    }

    @Test
    void cannotJumpFromOpenToClosed() {
        assertThat(TicketStatus.OPEN.canTransitionTo(TicketStatus.CLOSED)).isFalse();
    }

    @Test
    void resolvedCanBeReopened() {
        assertThat(TicketStatus.RESOLVED.canTransitionTo(TicketStatus.IN_PROGRESS)).isTrue();
    }

    @Test
    void onHoldReturnsToInProgress() {
        assertThat(TicketStatus.ON_HOLD.allowedNext()).containsExactly(TicketStatus.IN_PROGRESS);
    }

    @ParameterizedTest
    @EnumSource(value = TicketStatus.class, names = {"CLOSED", "CANCELLED"})
    void finalStatesHaveNoExit(TicketStatus status) {
        assertThat(status.isFinal()).isTrue();
        assertThat(status.allowedNext()).isEmpty();
    }
}
