package com.tickethub.util;

import org.junit.jupiter.api.Test;

import java.time.Year;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FolioGeneratorTest {

    @Test
    void padsSequenceWithZeros() {
        assertThat(FolioGenerator.generate(Year.of(2026), 123)).isEqualTo("TCK-2026-000123");
    }

    @Test
    void rejectsInvalidSequence() {
        assertThatThrownBy(() -> FolioGenerator.generate(Year.of(2026), 0))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
