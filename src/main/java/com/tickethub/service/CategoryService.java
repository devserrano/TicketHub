package com.tickethub.service;

import com.tickethub.dto.response.CategoryResponse;

import java.util.List;

public interface CategoryService {

    List<CategoryResponse> findActive();
}
