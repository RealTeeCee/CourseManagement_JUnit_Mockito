package com.swaggercodegen.swaggercodegenapp.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.RowMapper;

import com.swaggercodegen.swaggercodegenapp.model.CategoryDto;

public class CategoryMapperTest {
    @Test
    void testMapRow() throws SQLException {
        ResultSet rs = mock(ResultSet.class);

        when(rs.getLong("id")).thenReturn(1L);
        when(rs.getString("name")).thenReturn("abc");
        when(rs.getString("description")).thenReturn("abc");
        when(rs.getString("slug")).thenReturn("abc");
        when(rs.getString("image")).thenReturn("abc");
        when(rs.getTimestamp("created_at")).thenReturn(null);
        when(rs.getTimestamp("updated_at")).thenReturn(null);

        RowMapper<CategoryDto> mapper = new CategoryMapper();
        CategoryDto categoryDto = mapper.mapRow(rs, 1);

        assert categoryDto != null;
        assertEquals(1L, categoryDto.getId());
        assertEquals("abc", categoryDto.getName());
        assertEquals("abc", categoryDto.getDescription());
        assertEquals("abc", categoryDto.getSlug());
        assertEquals("abc", categoryDto.getImage());
        assertEquals(null, categoryDto.getCreatedAt());
        assertEquals(null, categoryDto.getUpdatedAt());

    }
}
