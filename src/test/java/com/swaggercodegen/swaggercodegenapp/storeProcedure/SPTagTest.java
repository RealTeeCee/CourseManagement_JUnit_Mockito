package com.swaggercodegen.swaggercodegenapp.storeProcedure;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.sql.Types;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.test.util.ReflectionTestUtils;

import com.swaggercodegen.swaggercodegenapp.mapper.CategoryMapper;
import com.swaggercodegen.swaggercodegenapp.model.BaseResponseDto;
import com.swaggercodegen.swaggercodegenapp.model.TagDto;

public class SPTagTest {
    @Mock
    private SimpleJdbcCall simpleJdbcCall;

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private SPTag spTag;

    @BeforeEach
    void init() throws SQLException {
        MockitoAnnotations.openMocks(this);

        ReflectionTestUtils.setField(spTag, "simpleJdbcCall", simpleJdbcCall);

        // when(dataSource.getConnection()).thenReturn(mock(Connection.class));
        // // when(jdbcTemplate.setDataSource(dataSource)).thenReturn(dataSource);
        // doNothing().when(jdbcTemplate).setDataSource(dataSource);
    }

    @Test
    @SuppressWarnings("unchecked")
    void testDeleteTag() {
        long tagId = 1;
        BaseResponseDto expectedResponse = BaseResponseDto.builder()
                .statusCode(200)
                .message("Success")
                .build();

        Map<String, Object> result = new HashMap<>();
        result.put("statusCode", expectedResponse.getStatusCode());
        result.put("message", expectedResponse.getMessage());
        // Mock the behavior of JdbcTemplate and SimpleJdbcCall
        when(jdbcTemplate.call((CallableStatementCreator) any(SimpleJdbcCall.class),
                (List<SqlParameter>) any(MapSqlParameterSource.class))).thenReturn(result);
        when(simpleJdbcCall.withProcedureName(anyString())).thenReturn(simpleJdbcCall);
        when(simpleJdbcCall.declareParameters(
                any(SqlParameter.class))).thenReturn(simpleJdbcCall);
        when(simpleJdbcCall.execute(any(MapSqlParameterSource.class)))
                .thenReturn(result);

        // Act
        BaseResponseDto actualResponse = spTag.deleteTag(tagId);

        // Assert
        verify(simpleJdbcCall).withProcedureName("sp_CRUD_tag");

        ArgumentCaptor<SqlParameter> captor = ArgumentCaptor.forClass(SqlParameter.class);
        verify(simpleJdbcCall).declareParameters(captor.capture());
        List<SqlParameter> actualParams = captor.getAllValues();

        assertEquals(3, actualParams.size());
        assertEquals("id", actualParams.get(0).getName());
        assertEquals(Types.BIGINT, actualParams.get(0).getSqlType());
        assertEquals("name", actualParams.get(1).getName());
        assertEquals(Types.VARCHAR, actualParams.get(1).getSqlType());
        assertEquals("action", actualParams.get(2).getName());
        assertEquals(Types.VARCHAR, actualParams.get(2).getSqlType());
        // verify(simpleJdbcCall).returningResultSet("rsGetAllCategories", new
        // TagMapper());
        verify(simpleJdbcCall).execute(ArgumentMatchers.any(MapSqlParameterSource.class));

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    @SuppressWarnings("unchecked")
    void testGetAllTags() {
        List<TagDto> expectedTags = Arrays.asList(
                new TagDto(1L, "Category 1", null, null),
                new TagDto(2L, "Category 2", null, null));

        Map<String, Object> result = new HashMap<>();
        result.put("rsGetAllTags", expectedTags);

        // Mock the behavior of JdbcTemplate and SimpleJdbcCall
        when(jdbcTemplate.call((CallableStatementCreator) any(SimpleJdbcCall.class),
                (List<SqlParameter>) any(MapSqlParameterSource.class))).thenReturn(result);
        when(simpleJdbcCall.withProcedureName(anyString())).thenReturn(simpleJdbcCall);
        when(simpleJdbcCall.declareParameters(
                any(SqlParameter.class))).thenReturn(simpleJdbcCall);
        when(simpleJdbcCall.returningResultSet(anyString(), any(CategoryMapper.class)))
                .thenReturn(simpleJdbcCall);
        when(simpleJdbcCall.execute(any(MapSqlParameterSource.class)))
                .thenReturn(result);

        // Act
        List<TagDto> actualTags = spTag.getAllTags();

        // Assert
        verify(simpleJdbcCall).withProcedureName("sp_CRUD_tag");

        ArgumentCaptor<SqlParameter> captor = ArgumentCaptor.forClass(SqlParameter.class);
        verify(simpleJdbcCall).declareParameters(captor.capture());
        List<SqlParameter> actualParams = captor.getAllValues();

        assertEquals(3, actualParams.size());
        assertEquals("id", actualParams.get(0).getName());
        assertEquals(Types.BIGINT, actualParams.get(0).getSqlType());
        assertEquals("name", actualParams.get(1).getName());
        assertEquals(Types.VARCHAR, actualParams.get(1).getSqlType());
        assertEquals("action", actualParams.get(2).getName());
        assertEquals(Types.VARCHAR, actualParams.get(2).getSqlType());
        // verify(simpleJdbcCall).returningResultSet("rsGetAllCategories", new
        // TagMapper());
        verify(simpleJdbcCall).execute(ArgumentMatchers.any(MapSqlParameterSource.class));

        assertEquals(expectedTags, actualTags);
    }

    @Test
    @SuppressWarnings("unchecked")
    void testInsertTag() {
        TagDto tagDto = TagDto.builder()
                .id(1L)
                .name("abc")
                .build();

        BaseResponseDto expectedResponse = BaseResponseDto.builder()
                .statusCode(200)
                .message("Success")
                .build();

        Map<String, Object> result = new HashMap<>();
        result.put("statusCode", expectedResponse.getStatusCode());
        result.put("message", expectedResponse.getMessage());
        // Mock the behavior of JdbcTemplate and SimpleJdbcCall
        when(jdbcTemplate.call((CallableStatementCreator) any(SimpleJdbcCall.class),
                (List<SqlParameter>) any(MapSqlParameterSource.class))).thenReturn(result);
        when(simpleJdbcCall.withProcedureName(anyString())).thenReturn(simpleJdbcCall);
        when(simpleJdbcCall.declareParameters(
                any(SqlParameter.class))).thenReturn(simpleJdbcCall);
        when(simpleJdbcCall.execute(any(MapSqlParameterSource.class)))
                .thenReturn(result);

        // Act
        BaseResponseDto actualResponse = spTag.insertTag(tagDto);

        // Assert
        verify(simpleJdbcCall).withProcedureName("sp_CRUD_tag");

        ArgumentCaptor<SqlParameter> captor = ArgumentCaptor.forClass(SqlParameter.class);
        verify(simpleJdbcCall).declareParameters(captor.capture());
        List<SqlParameter> actualParams = captor.getAllValues();

        assertEquals(3, actualParams.size());
        assertEquals("id", actualParams.get(0).getName());
        assertEquals(Types.BIGINT, actualParams.get(0).getSqlType());
        assertEquals("name", actualParams.get(1).getName());
        assertEquals(Types.VARCHAR, actualParams.get(1).getSqlType());
        assertEquals("action", actualParams.get(2).getName());
        assertEquals(Types.VARCHAR, actualParams.get(2).getSqlType());
        // verify(simpleJdbcCall).returningResultSet("rsGetAllCategories", new
        // TagMapper());
        verify(simpleJdbcCall).execute(ArgumentMatchers.any(MapSqlParameterSource.class));

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    @SuppressWarnings("unchecked")
    void testUpdateTag() {
        TagDto tagDto = TagDto.builder()
                .id(1L)
                .name("abc")
                .build();

        BaseResponseDto expectedResponse = BaseResponseDto.builder()
                .statusCode(200)
                .message("Success")
                .build();

        Map<String, Object> result = new HashMap<>();
        result.put("statusCode", expectedResponse.getStatusCode());
        result.put("message", expectedResponse.getMessage());
        // Mock the behavior of JdbcTemplate and SimpleJdbcCall
        when(jdbcTemplate.call((CallableStatementCreator) any(SimpleJdbcCall.class),
                (List<SqlParameter>) any(MapSqlParameterSource.class))).thenReturn(result);
        when(simpleJdbcCall.withProcedureName(anyString())).thenReturn(simpleJdbcCall);
        when(simpleJdbcCall.declareParameters(
                any(SqlParameter.class))).thenReturn(simpleJdbcCall);
        when(simpleJdbcCall.execute(any(MapSqlParameterSource.class)))
                .thenReturn(result);

        // Act
        BaseResponseDto actualResponse = spTag.updateTag(tagDto);

        // Assert
        verify(simpleJdbcCall).withProcedureName("sp_CRUD_tag");

        ArgumentCaptor<SqlParameter> captor = ArgumentCaptor.forClass(SqlParameter.class);
        verify(simpleJdbcCall).declareParameters(captor.capture());
        List<SqlParameter> actualParams = captor.getAllValues();

        assertEquals(3, actualParams.size());
        assertEquals("id", actualParams.get(0).getName());
        assertEquals(Types.BIGINT, actualParams.get(0).getSqlType());
        assertEquals("name", actualParams.get(1).getName());
        assertEquals(Types.VARCHAR, actualParams.get(1).getSqlType());
        assertEquals("action", actualParams.get(2).getName());
        assertEquals(Types.VARCHAR, actualParams.get(2).getSqlType());
        // verify(simpleJdbcCall).returningResultSet("rsGetAllCategories", new
        // TagMapper());
        verify(simpleJdbcCall).execute(ArgumentMatchers.any(MapSqlParameterSource.class));

        assertEquals(expectedResponse, actualResponse);
    }
}
