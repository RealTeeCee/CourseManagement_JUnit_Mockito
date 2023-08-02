package com.swaggercodegen.swaggercodegenapp.delegate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.server.ServerWebExchange;

import com.swaggercodegen.swaggercodegenapp.api.TagApiDelegate;
import com.swaggercodegen.swaggercodegenapp.model.BaseDto;
import com.swaggercodegen.swaggercodegenapp.model.TagDto;
import com.swaggercodegen.swaggercodegenapp.model.BaseDto.AntTypeEnum;
import com.swaggercodegen.swaggercodegenapp.services.TagService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class TagDelegate implements TagApiDelegate {
    private final TagService service;

    @Override
    public Mono<ResponseEntity<BaseDto>> create(Mono<TagDto> tagDto, ServerWebExchange exchange) {
        return tagDto.flatMap(tag -> Mono.just(ResponseEntity.ok(service.create(tag))));
    }

    @Override
    public Mono<ResponseEntity<BaseDto>> delete(Long tagId, ServerWebExchange exchange) {
        return Mono.just(ResponseEntity.ok(service.delete(tagId)));
    }

    @Override
    public Mono<ResponseEntity<Flux<TagDto>>> getAllTags(ServerWebExchange exchange) {

        try {
            return Mono.just(ResponseEntity.ok(Flux.fromIterable(service.findAll())));
        } catch (SQLException e) {
         
           return Mono.just(ResponseEntity.internalServerError().body(null));
        }
    }

    @Override
    public Mono<ResponseEntity<BaseDto>> update(Mono<TagDto> tagDto, ServerWebExchange exchange) {

        // TagDto block = tagDto.block();
        // return Mono.just(ResponseEntity.ok(service.update(block)));
        return tagDto.flatMap(tag -> Mono.just(ResponseEntity.ok(service.update(tag))));
    }

}
