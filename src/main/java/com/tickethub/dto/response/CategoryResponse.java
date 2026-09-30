package com.tickethub.dto.response;

/** Lo que la API regresa de una categoría (nunca se expone la entidad directamente). */
public record CategoryResponse(
        Long id,
        String name,
        String description
) {
}
