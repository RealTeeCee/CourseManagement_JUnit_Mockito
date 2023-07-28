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
import com.swaggercodegen.swaggercodegenapp.model.CategoryDto;

public class SPCategoryTest {

        @Mock
        private SimpleJdbcCall simpleJdbcCall;

        @Mock
        private JdbcTemplate jdbcTemplate;

        @InjectMocks
        private SPCategory spCategory;

        @BeforeEach
        void init() throws SQLException {
                MockitoAnnotations.openMocks(this);

                ReflectionTestUtils.setField(spCategory, "simpleJdbcCall", simpleJdbcCall);

                // when(dataSource.getConnection()).thenReturn(mock(Connection.class));

                // // when(jdbcTemplate.setDataSource(dataSource)).thenReturn(dataSource);
                // doNothing().when(jdbcTemplate).setDataSource(dataSource);

        }

        @Test
        void testDeleteCategory() {

        }

        @Test
        @SuppressWarnings("unchecked")
        void testGetAllCategories() {
                // Arrange
                List<CategoryDto> expectedCategories = Arrays.asList(
                                new CategoryDto(1L, "Category 1", "Description 1", "slug-1", "image-1", null, null),
                                new CategoryDto(2L, "Category 2", "Description 2", "slug-2", "image-2", null, null));
                Map<String, Object> result = new HashMap<>();
                result.put("rsGetAllCategories", expectedCategories);
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

                List<CategoryDto> actualCategories = spCategory.getAllCategories();

                // Assert

                verify(simpleJdbcCall).withProcedureName("sp_CRUD_category");
                // verify(simpleJdbcCall).declareParameters(
                // new SqlParameter("id", Types.BIGINT),
                // new SqlParameter("name", Types.VARCHAR),
                // new SqlParameter("description", Types.VARCHAR),
                // new SqlParameter("slug", Types.VARCHAR),
                // new SqlParameter("image", Types.VARCHAR),
                // new SqlParameter("action", Types.VARCHAR));
                ArgumentCaptor<SqlParameter> captor = ArgumentCaptor.forClass(SqlParameter.class);
                verify(simpleJdbcCall).declareParameters(captor.capture());
                List<SqlParameter> actualParams = captor.getAllValues();

                assertEquals(6, actualParams.size());
                assertEquals("id", actualParams.get(0).getName());
                assertEquals(Types.BIGINT, actualParams.get(0).getSqlType());
                assertEquals("name", actualParams.get(1).getName());
                assertEquals(Types.VARCHAR, actualParams.get(1).getSqlType());
                assertEquals("description", actualParams.get(2).getName());
                assertEquals(Types.VARCHAR, actualParams.get(2).getSqlType());
                assertEquals("slug", actualParams.get(3).getName());
                assertEquals(Types.VARCHAR, actualParams.get(3).getSqlType());
                assertEquals("image", actualParams.get(4).getName());
                assertEquals(Types.VARCHAR, actualParams.get(4).getSqlType());
                assertEquals("action", actualParams.get(5).getName());
                assertEquals(Types.VARCHAR, actualParams.get(5).getSqlType());
                // verify(simpleJdbcCall).returningResultSet("rsGetAllCategories", new
                // CategoryMapper());
                verify(simpleJdbcCall).execute(ArgumentMatchers.any(MapSqlParameterSource.class));

                assertEquals(expectedCategories, actualCategories);
        }

        @Test
        void testInsertCategory() {

        }

        @Test
        void testUpdateCategory() {

        }
}
