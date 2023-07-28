package com.swaggercodegen.swaggercodegenapp.services.serviceImpl;

import static com.swaggercodegen.swaggercodegenapp.contants.GlobalStorage.BAD_REQUEST_EXCEPTION;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.swaggercodegen.swaggercodegenapp.exceptions.BadRequestException;
import com.swaggercodegen.swaggercodegenapp.model.BaseDto;
import com.swaggercodegen.swaggercodegenapp.model.BaseDto.AntTypeEnum;
import com.swaggercodegen.swaggercodegenapp.model.Tag;
import com.swaggercodegen.swaggercodegenapp.model.TagDto;
import com.swaggercodegen.swaggercodegenapp.repositories.TagRepository;
import com.swaggercodegen.swaggercodegenapp.services.TagService;
import com.swaggercodegen.swaggercodegenapp.storeProcedure.SPTag;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {
    private final TagRepository tagRepository;
    private final SPTag spTag;

    @Override
    public Tag findTagByName(String name) {
        return tagRepository.findTagByName(name);
    }

    @Override
    public List<TagDto> findAll() {

        return spTag.getAllTags();
    }

    @Override
    public BaseDto create(TagDto tagDto) {
        try {
            spTag.insertTag(tagDto);

            return BaseDto.builder().antType(AntTypeEnum.SUCCESS).message("Create tag successfully.").build();
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException(
                    "The tag with tagId: [" + tagDto.getId() + "] is not exist.");
        } catch (Exception e) {
            throw new BadRequestException(BAD_REQUEST_EXCEPTION);
        }
    }

    @Override
    public boolean saveAll(List<TagDto> tagsDto) {
        List<Tag> tags = tagsDto.stream().map(tagDto -> findTagByName(tagDto.getName())).collect(Collectors.toList());
        tagRepository.saveAll(tags);
        return true;
    }

    @Override
    public TagDto findById(long tagId) {
        Tag tag = tagRepository.findById(tagId).orElseThrow(
                () -> new NoSuchElementException("This tag with tagId: [" + tagId + "] is not exist."));
        TagDto tagDto = toTagDto(tag);
        return tagDto;
    }

    @Override
    public BaseDto update(TagDto tagDto) {
        try {
            spTag.updateTag(tagDto);

            return BaseDto.builder().antType(AntTypeEnum.SUCCESS).message("Update tag successfully.").build();
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException(e.getMessage());
        } catch (Exception e) {
            throw new BadRequestException(BAD_REQUEST_EXCEPTION);
        }
    }

    @Override
    public BaseDto delete(long tagId) {
        try {
            spTag.deleteTag(tagId);
            
            return BaseDto.builder().antType(AntTypeEnum.SUCCESS).message("Delete tag successfully.")
                    .build();
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException(e.getMessage());
        } catch (Exception e) {
            throw new BadRequestException(BAD_REQUEST_EXCEPTION);
        }
    }

    private TagDto toTagDto(Tag tag) {
        TagDto tagDto = TagDto.builder()
                .id(tag.getId())
                .name(tag.getName()).build();
        return tagDto;
    }
}