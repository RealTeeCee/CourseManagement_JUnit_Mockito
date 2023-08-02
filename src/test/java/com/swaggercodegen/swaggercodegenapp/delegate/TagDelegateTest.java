package com.swaggercodegen.swaggercodegenapp.delegate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ServerWebExchange;

import com.swaggercodegen.swaggercodegenapp.model.BaseDto;
import com.swaggercodegen.swaggercodegenapp.model.BaseDto.AntTypeEnum;
import com.swaggercodegen.swaggercodegenapp.model.TagDto;
import com.swaggercodegen.swaggercodegenapp.services.TagService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class TagDelegateTest {

    @Mock
    private TagService service;
    @Mock
    private ServerWebExchange exchange;

    @InjectMocks
    private TagDelegate delegate;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreate() {
        TagDto dto = TagDto.builder().name("Create").createdAt(OffsetDateTime.now())
                .build();

        BaseDto baseDto = BaseDto.builder()
                .antType(AntTypeEnum.SUCCESS)
                .message("Create tag successfully.")
                .build();

        when(service.create(eq(dto))).thenReturn(baseDto);

        Mono<TagDto> monoTagDto = Mono.just(dto);
        Mono<ResponseEntity<BaseDto>> resBaseDto = delegate.create(monoTagDto, exchange);

        ResponseEntity<BaseDto> result = resBaseDto.block();
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(AntTypeEnum.SUCCESS, result.getBody().getAntType());
        assertEquals("Create tag successfully.", result.getBody().getMessage());

        verify(service, times(1)).create(dto);
    }

    @Test
    void testDelete() {
        TagDto dto = TagDto.builder()
                .id(1L)
                .name("Delete")
                .build();

        BaseDto baseDto = BaseDto.builder()
                .antType(AntTypeEnum.SUCCESS)
                .message("Delete tag successfully.")
                .build();

        when(service.delete(eq(dto.getId()))).thenReturn(baseDto);

        Mono<ResponseEntity<BaseDto>> resBaseDto = delegate.delete(dto.getId(), exchange);

        ResponseEntity<BaseDto> result = resBaseDto.block();
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(AntTypeEnum.SUCCESS, result.getBody().getAntType());
        assertEquals("Delete tag successfully.", result.getBody().getMessage());

        verify(service, times(1)).delete(dto.getId());
    }

    @Test
    void testGetAllTags() throws SQLException {

        TagDto dto = TagDto.builder().id(1L).name("ABC").createdAt(OffsetDateTime.now())
                .updatedAt(OffsetDateTime.now())
                .build();
        List<TagDto> dtos = new ArrayList<>();
        dtos.add(dto);
        when(service.findAll()).thenReturn(dtos);

        Mono<ResponseEntity<Flux<TagDto>>> allTags = delegate.getAllTags(exchange);
        ResponseEntity<Flux<TagDto>> result = allTags.block();
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(dtos.get(0), result.getBody().blockFirst());

    }

    @Test
    void testUpdate() {
        TagDto dto = TagDto.builder().id(1L).name("Updated").updatedAt(OffsetDateTime.now())
                .build();

        BaseDto baseDto = BaseDto.builder()
                .antType(AntTypeEnum.SUCCESS)
                .message("Update tag successfully.")
                .build();

        when(service.update(eq(dto))).thenReturn(baseDto);

        Mono<TagDto> monoTagDto = Mono.just(dto);
        Mono<ResponseEntity<BaseDto>> resBaseDto = delegate.update(monoTagDto, exchange);

        ResponseEntity<BaseDto> result = resBaseDto.block();
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(AntTypeEnum.SUCCESS, result.getBody().getAntType());
        assertEquals("Update tag successfully.", result.getBody().getMessage());

        verify(service, times(1)).update(dto);
    }
}
