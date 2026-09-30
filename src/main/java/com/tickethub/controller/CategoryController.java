package com.tickethub.controller;

import com.tickethub.constants.ApiPaths;
import com.tickethub.dto.response.CategoryResponse;
import com.tickethub.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Ejemplo completo del flujo por capas: Controller -> Service -> Repository -> Entity -> Mapper -> DTO.
 */
@Tag(name = "Categorías")
@RestController
@RequestMapping(ApiPaths.CATEGORIES)
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @Operation(summary = "Lista las categorías activas")
    @GetMapping
    public List<CategoryResponse> findActive() {
        return categoryService.findActive();
    }
}
