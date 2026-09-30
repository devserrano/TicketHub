package com.tickethub.util;

import java.time.Year;

/** Genera el folio visible del ticket: TCK-AAAA-NNNNNN (ej. TCK-2026-000123). */
public final class FolioGenerator {

    private static final String PREFIX = "TCK";

    private FolioGenerator() {
    }

    public static String generate(Year year, long sequence) {
        if (sequence < 1 || sequence > 999_999) {
            throw new IllegalArgumentException("La secuencia debe estar entre 1 y 999999");
        }
        return String.format("%s-%d-%06d", PREFIX, year.getValue(), sequence);
    }
}
