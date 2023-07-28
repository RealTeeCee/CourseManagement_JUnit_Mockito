package com.swaggercodegen.swaggercodegenapp.services;

import java.util.List;

import com.swaggercodegen.swaggercodegenapp.model.BaseDto;
import com.swaggercodegen.swaggercodegenapp.model.CategoryDto;

public interface CategoryService {
    public CategoryDto findById(long id);

    public List<CategoryDto> findAll();

    public BaseDto create(CategoryDto categoryDto);

    public BaseDto createAll(List<CategoryDto> categories);

    public BaseDto update(CategoryDto categoryDto);

    public BaseDto updateAll(List<CategoryDto> categories);

    public BaseDto delete(long categoryId);

}
