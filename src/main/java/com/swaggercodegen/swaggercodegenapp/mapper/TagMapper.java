package com.swaggercodegen.swaggercodegenapp.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

import org.springframework.jdbc.core.RowMapper;

import com.swaggercodegen.swaggercodegenapp.model.TagDto;

public class TagMapper implements RowMapper<TagDto> {

    @Override
    public TagDto mapRow(ResultSet rs, int rowNum) throws SQLException {

        ZoneId zoneId = ZoneId.of("UTC");
        LocalDateTime createdAt = rs.getTimestamp("created_at").toLocalDateTime();
        ZoneOffset offsetCreatedAt = zoneId.getRules().getOffset(createdAt);
        LocalDateTime updatedAt = rs.getTimestamp("updated_at").toLocalDateTime();
        ZoneOffset offsetUpdatedAt = zoneId.getRules().getOffset(updatedAt);
        return TagDto.builder()
                .id(rs.getLong("id"))
                .name(rs.getString("name"))
                .createdAt(OffsetDateTime.of(createdAt, offsetCreatedAt))
                .updatedAt(OffsetDateTime.of(updatedAt, offsetUpdatedAt))
                .build();
    }

}
