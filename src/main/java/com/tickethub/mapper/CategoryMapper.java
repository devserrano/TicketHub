package com.tickethub.mapper;

import com.tickethub.dto.response.CategoryResponse;
import com.tickethub.entity.Category;

/** Convierte entidades a DTOs y viceversa. */
public final class CategoryMapper {

    private CategoryMapper() {
    }

    public static CategoryResponse toResponse(Category category) {
        return new CategoryResponse(category.getId(), category.getName(), category.getDescription());
    }
}
