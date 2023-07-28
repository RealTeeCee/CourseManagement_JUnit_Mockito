package com.swaggercodegen.swaggercodegenapp.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

import org.springframework.jdbc.core.RowMapper;

import com.swaggercodegen.swaggercodegenapp.model.CategoryDto;

public class CategoryMapper implements RowMapper<CategoryDto> {

    @Override
    public CategoryDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        ZoneId zoneId = ZoneId.of("UTC");
        LocalDateTime createdAt = rs.getTimestamp("created_at") == null ? null
                : rs.getTimestamp("created_at").toLocalDateTime();
        ZoneOffset offsetCreatedAt = zoneId.getRules().getOffset(createdAt);
        LocalDateTime updatedAt = rs.getTimestamp("updated_at") == null ? null
                : rs.getTimestamp("updated_at").toLocalDateTime();
        ZoneOffset offsetUpdatedAt = zoneId.getRules().getOffset(updatedAt);
        return CategoryDto.builder()
                .id(rs.getLong("id"))
                .name(rs.getString("name"))
                .description(rs.getString("description"))
                .slug(rs.getString("slug"))
                .image(rs.getString("image"))
                .createdAt(createdAt == null ? null : OffsetDateTime.of(createdAt, offsetCreatedAt))
                .updatedAt(updatedAt == null ? null : OffsetDateTime.of(updatedAt, offsetUpdatedAt))
                .build();
    }

}
