package com.swaggercodegen.swaggercodegenapp.services;

import java.util.List;

import com.swaggercodegen.swaggercodegenapp.model.BaseDto;
import com.swaggercodegen.swaggercodegenapp.model.Tag;
import com.swaggercodegen.swaggercodegenapp.model.TagDto;

public interface TagService {
    public Tag findTagByName(String tagName);

    public List<TagDto> findAll();

    public BaseDto create(TagDto tagDto);

    public BaseDto update(TagDto tagDto);

    public BaseDto delete(long tagId);

    public boolean saveAll(List<TagDto> tagDtos);

    public TagDto findById(long tagId);
}
