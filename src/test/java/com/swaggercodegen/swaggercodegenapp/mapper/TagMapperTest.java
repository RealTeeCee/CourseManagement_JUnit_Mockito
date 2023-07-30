package com.swaggercodegen.swaggercodegenapp.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.RowMapper;

import com.swaggercodegen.swaggercodegenapp.model.TagDto;

public class TagMapperTest {
    @Test
    void testMapRow() throws SQLException {
        ResultSet rs = mock(ResultSet.class);

        when(rs.getLong("id")).thenReturn(1L);
        when(rs.getString("name")).thenReturn("abc");
        when(rs.getTimestamp("created_at")).thenReturn(null);
        when(rs.getTimestamp("updated_at")).thenReturn(null);

        RowMapper<TagDto> mapper = new TagMapper();
        TagDto tagDto = mapper.mapRow(rs, 1);

        assert tagDto != null;
        assertEquals(1L, tagDto.getId());
        assertEquals("abc", tagDto.getName());
        assertEquals(null, tagDto.getCreatedAt());
        assertEquals(null, tagDto.getUpdatedAt());

    }
}
