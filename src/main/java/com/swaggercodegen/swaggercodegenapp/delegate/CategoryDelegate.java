package com.swaggercodegen.swaggercodegenapp.delegate;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ServerWebExchange;

import com.swaggercodegen.swaggercodegenapp.api.CategoryApiDelegate;
import com.swaggercodegen.swaggercodegenapp.exceptions.BadRequestException;
import com.swaggercodegen.swaggercodegenapp.model.BaseDto;
import com.swaggercodegen.swaggercodegenapp.model.CategoryDto;
import com.swaggercodegen.swaggercodegenapp.services.CategoryService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import static com.swaggercodegen.swaggercodegenapp.contants.GlobalStorage.*;

@Service
@RequiredArgsConstructor
public class CategoryDelegate implements CategoryApiDelegate {
    @Override
    public Mono<ResponseEntity<BaseDto>> createCategory(Mono<CategoryDto> categoryDto, ServerWebExchange exchange) {
        try {

            return categoryDto.flatMap(cate -> {
                if (cate.getName() == null || cate.getName().trim().isEmpty()) {
                    return Mono.error(new BadRequestException("Name cannot be blank"));
                }
                return Mono.just(ResponseEntity.ok(service.create(cate)));
            });

        } catch (BadRequestException e) {
            throw new BadRequestException(e.getMessage());

        } catch (Exception e) {
            throw new BadRequestException(GLOBAL_EXCEPTION);
        }
    }

    @Override
    public Mono<ResponseEntity<BaseDto>> updateCategory(Mono<CategoryDto> categoryDto, ServerWebExchange exchange) {

        try {
            return categoryDto.flatMap(cate -> Mono.just(ResponseEntity.ok(service.update(cate))));

        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    private final CategoryService service;

    @Override
    public Mono<ResponseEntity<Flux<CategoryDto>>> getAllCategories(ServerWebExchange exchange) {
        try {
            return Mono.just(ResponseEntity.ok(Flux.fromIterable(service.findAll())));

        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    @Override
    public Mono<ResponseEntity<CategoryDto>> getCategoryById(Integer categoryId, ServerWebExchange exchange) {
        try {
            return Mono.just(ResponseEntity.ok(service.findById(categoryId)));

        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    @Override
    public Mono<ResponseEntity<BaseDto>> deleteCategory(Long categoryId, ServerWebExchange exchange) {
        try {
            return Mono.just(ResponseEntity.ok(service.delete(categoryId)));

        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

}
