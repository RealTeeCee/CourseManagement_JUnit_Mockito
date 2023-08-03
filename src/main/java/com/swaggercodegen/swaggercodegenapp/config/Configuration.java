package com.swaggercodegen.swaggercodegenapp.config;

import java.time.LocalDate;

import org.springframework.context.annotation.Bean;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;

import lombok.RequiredArgsConstructor;

// @org.springframework.context.annotation.Configuration
// @RequiredArgsConstructor
// public class Configuration {
//     @Bean
//     public ObjectMapper objectMapper() {
//         JavaTimeModule module = new JavaTimeModule();
//         LocalDateTimeDeserializer localDateTimeDeserializer = new LocalDateTimeDeserializer(
//                 DateFormatter.ofPattern("yyyy-MM-dd"));
//         module.addDeserializer(LocalDate.class, localDateTimeDeserializer);
//         ObjectMapper objectMapperObj = Jackson2ObjectMapperBuilder.json()
//                 .modules(module)
//                 .featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
//                 .build();
//         return objectMapperObj;
//     }
// }
