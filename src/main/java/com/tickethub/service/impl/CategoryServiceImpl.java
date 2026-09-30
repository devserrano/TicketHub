package com.tickethub.service.impl;

import com.tickethub.dto.response.CategoryResponse;
import com.tickethub.mapper.CategoryMapper;
import com.tickethub.repository.CategoryRepository;
import com.tickethub.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    @Transactional(readOnly = true)
    public List<CategoryResponse> findActive() {
        return categoryRepository.findByActiveTrueOrderByNameAsc()
                .stream()
                .map(CategoryMapper::toResponse)
                .toList();
    }
}
