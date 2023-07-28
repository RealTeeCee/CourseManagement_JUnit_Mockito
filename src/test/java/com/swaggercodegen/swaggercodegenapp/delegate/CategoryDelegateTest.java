package com.swaggercodegen.swaggercodegenapp.delegate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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

import com.swaggercodegen.swaggercodegenapp.exceptions.BadRequestException;
import com.swaggercodegen.swaggercodegenapp.model.BaseDto;
import com.swaggercodegen.swaggercodegenapp.model.BaseDto.AntTypeEnum;
import com.swaggercodegen.swaggercodegenapp.model.CategoryDto;
import com.swaggercodegen.swaggercodegenapp.services.CategoryService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class CategoryDelegateTest {
        @Mock
        private CategoryService service;
        @Mock
        private ServerWebExchange exchange;

        // @InjectMocks
        // private CategoryDelegate delegate;
        @InjectMocks
        private CategoryDelegate delegate;

        @BeforeEach
        void init() {
                MockitoAnnotations.openMocks(this);
        }

        @Test
        void testCreateSuccess() {
                CategoryDto dto = CategoryDto.builder()
                                .name("Create")
                                .description("Description")
                                .image("img.png")
                                .createdAt(OffsetDateTime.now())
                                .build();

                BaseDto baseDto = BaseDto.builder()
                                .antType(AntTypeEnum.SUCCESS)
                                .message("Create category successfully.")
                                .build();

                when(service.create(eq(dto))).thenReturn(baseDto);

                Mono<CategoryDto> monoCategoryDto = Mono.just(dto);
                Mono<ResponseEntity<BaseDto>> resBaseDto = delegate.createCategory(monoCategoryDto, exchange);

                ResponseEntity<BaseDto> result = resBaseDto.block();
                assertEquals(HttpStatus.OK, result.getStatusCode());
                assertEquals(AntTypeEnum.SUCCESS, result.getBody().getAntType());
                assertEquals("Create category successfully.", result.getBody().getMessage());

                verify(service, times(1)).create(dto);
        }

        @Test
        void testCreate_throwException() {

                CategoryDto dtomockDto = mock(CategoryDto.class);
                Mono<CategoryDto> monoCategoryDto = Mono.just(dtomockDto);
                when(dtomockDto.getName() == null).thenThrow(BadRequestException.class);
                Mono<ResponseEntity<BaseDto>> createCategory = delegate.createCategory(monoCategoryDto, exchange);
                assertThrows(BadRequestException.class, () -> createCategory.block());

                StepVerifier.create(delegate.createCategory(monoCategoryDto, exchange))
                                .expectError(BadRequestException.class).verify();
        }

        @Test
        void testDeleteSuccess() {
                CategoryDto dto = CategoryDto.builder()
                                .id(1L)
                                .name("Delete")
                                .build();

                BaseDto baseDto = BaseDto.builder()
                                .antType(AntTypeEnum.SUCCESS)
                                .message("Delete category successfully.")
                                .build();

                when(service.delete(eq(dto.getId()))).thenReturn(baseDto);

                Mono<ResponseEntity<BaseDto>> resBaseDto = delegate.deleteCategory(dto.getId(), exchange);

                ResponseEntity<BaseDto> result = resBaseDto.block();
                assertEquals(HttpStatus.OK, result.getStatusCode());
                assertEquals(AntTypeEnum.SUCCESS, result.getBody().getAntType());
                assertEquals("Delete category successfully.", result.getBody().getMessage());

                verify(service, times(1)).delete(dto.getId());
        }

        @Test
        void testGetAllCategorys() {

                CategoryDto dto = CategoryDto.builder().id(1L).name("ABC").createdAt(OffsetDateTime.now())
                                .updatedAt(OffsetDateTime.now())
                                .build();
                List<CategoryDto> dtos = new ArrayList<>();
                dtos.add(dto);
                when(service.findAll()).thenReturn(dtos);

                Mono<ResponseEntity<Flux<CategoryDto>>> allCategorys = delegate.getAllCategories(exchange);
                ResponseEntity<Flux<CategoryDto>> result = allCategorys.block();
                assertEquals(HttpStatus.OK, result.getStatusCode());
                assertEquals(dtos.get(0), result.getBody().blockFirst());

        }

        @Test
        void testUpdateSuccess() {
                CategoryDto dto = CategoryDto.builder().id(1L)
                                .name("Updated")
                                .description("Description")
                                .image("img.png")
                                .updatedAt(OffsetDateTime.now())
                                .build();

                BaseDto baseDto = BaseDto.builder()
                                .antType(AntTypeEnum.SUCCESS)
                                .message("Update category successfully.")
                                .build();

                when(service.update(eq(dto))).thenReturn(baseDto);

                Mono<CategoryDto> monoCategoryDto = Mono.just(dto);
                Mono<ResponseEntity<BaseDto>> resBaseDto = delegate.updateCategory(monoCategoryDto, exchange);

                ResponseEntity<BaseDto> result = resBaseDto.block();
                assertEquals(HttpStatus.OK, result.getStatusCode());
                assertEquals(AntTypeEnum.SUCCESS, result.getBody().getAntType());
                assertEquals("Update category successfully.", result.getBody().getMessage());

                verify(service, times(1)).update(dto);
        }
}
