package com.swaggercodegen.swaggercodegenapp.services.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.swaggercodegen.swaggercodegenapp.model.BaseDto;
import com.swaggercodegen.swaggercodegenapp.model.BaseDto.AntTypeEnum;
import com.swaggercodegen.swaggercodegenapp.model.CategoryDto;
import com.swaggercodegen.swaggercodegenapp.services.CategoryService;
import com.swaggercodegen.swaggercodegenapp.storeProcedure.SPCategory;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final SPCategory spCategory;

    @Override
    public CategoryDto findById(long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public List<CategoryDto> findAll() {

        return spCategory.getAllCategories();

    }

    @Override
    public BaseDto create(CategoryDto categoryDto) {
        spCategory.insertCategory(categoryDto);
        return BaseDto.builder().antType(AntTypeEnum.SUCCESS).message("Create category successfully.").build();
    }

    @Override
    public BaseDto createAll(List<CategoryDto> categories) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createAll'");
    }

    @Override
    public BaseDto update(CategoryDto categoryDto) {
        spCategory.updateCategory(categoryDto);
        return BaseDto.builder().antType(AntTypeEnum.SUCCESS).message("Update category successfully.").build();
    }

    @Override
    public BaseDto updateAll(List<CategoryDto> categories) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateAll'");
    }

    @Override
    public BaseDto delete(long categoryId) {
        spCategory.deleteCategory(categoryId);

        return BaseDto.builder().antType(AntTypeEnum.SUCCESS).message("Delete tag successfully.")
                .build();
    }

}
