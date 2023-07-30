package com.swaggercodegen.swaggercodegenapp.storeProcedure;

import java.sql.Types;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Component;

import com.swaggercodegen.swaggercodegenapp.mapper.CategoryMapper;
import com.swaggercodegen.swaggercodegenapp.model.BaseResponseDto;
import com.swaggercodegen.swaggercodegenapp.model.CategoryDto;

@Component
public class SPCategory {
        private SimpleJdbcCall simpleJdbcCall;
        // private final JdbcTemplate jdbcTemplate;

        JdbcTemplate jdbcTemplate;

        @Autowired
        public SPCategory(JdbcTemplate jdbcTemplate) {

                this.jdbcTemplate = jdbcTemplate;
                this.simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);

        }

        public List<CategoryDto> getAllCategories() {
                // simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
                simpleJdbcCall.withProcedureName("sp_CRUD_category").declareParameters(
                                new SqlParameter("id", Types.BIGINT),
                                new SqlParameter("name", Types.VARCHAR),
                                new SqlParameter("description", Types.VARCHAR),
                                new SqlParameter("slug", Types.VARCHAR),
                                new SqlParameter("image", Types.VARCHAR),
                                new SqlParameter("action", Types.VARCHAR)).returningResultSet("rsGetAllCategories",
                                                new CategoryMapper());

                MapSqlParameterSource parameterSource = new MapSqlParameterSource();
                parameterSource.addValue("id", null);
                parameterSource.addValue("name", null);
                parameterSource.addValue("description", null);
                parameterSource.addValue("slug", null);
                parameterSource.addValue("image", null);
                parameterSource.addValue("action", null);
                Map<String, Object> result = simpleJdbcCall.execute(parameterSource);
                @SuppressWarnings("unchecked")
                List<CategoryDto> dtos = (List<CategoryDto>) result.get("rsGetAllCategories");
                return dtos;
        }

        public BaseResponseDto insertCategory(CategoryDto categoryDto) {

                simpleJdbcCall.withProcedureName("sp_CRUD_category").declareParameters(
                                new SqlParameter("id", Types.BIGINT),
                                new SqlParameter("name", Types.VARCHAR),
                                new SqlParameter("description", Types.VARCHAR),
                                new SqlParameter("slug", Types.VARCHAR),
                                new SqlParameter("image", Types.VARCHAR),
                                new SqlParameter("action", Types.VARCHAR));

                MapSqlParameterSource parameterSource = new MapSqlParameterSource();
                parameterSource.addValue("id", 0);
                parameterSource.addValue("name", categoryDto.getName());
                parameterSource.addValue("description", categoryDto.getDescription());
                parameterSource.addValue("slug", categoryDto.getSlug());
                parameterSource.addValue("image", categoryDto.getImage());
                parameterSource.addValue("action", "INSERT");

                Map<String, Object> result = simpleJdbcCall.execute(parameterSource);

                BaseResponseDto dto = BaseResponseDto.builder()
                                .statusCode((Integer) result.get("statusCode"))
                                .message(result.get("message").toString())
                                .build();

                return dto;
        }

        public BaseResponseDto updateCategory(CategoryDto categoryDto) {

                simpleJdbcCall.withProcedureName("sp_CRUD_category").declareParameters(
                                new SqlParameter("id", Types.BIGINT),
                                new SqlParameter("name", Types.VARCHAR),
                                new SqlParameter("description", Types.VARCHAR),
                                new SqlParameter("slug", Types.VARCHAR),
                                new SqlParameter("image", Types.VARCHAR),
                                new SqlParameter("action", Types.VARCHAR));

                MapSqlParameterSource parameterSource = new MapSqlParameterSource();
                parameterSource.addValue("id", categoryDto.getId());
                parameterSource.addValue("name", categoryDto.getName());
                parameterSource.addValue("description", categoryDto.getDescription());
                parameterSource.addValue("slug", categoryDto.getSlug());
                parameterSource.addValue("image", categoryDto.getImage());
                parameterSource.addValue("action", "UPDATE");

                Map<String, Object> result = simpleJdbcCall.execute(parameterSource);

                BaseResponseDto dto = BaseResponseDto.builder()
                                .statusCode((Integer) result.get("statusCode"))
                                .message(result.get("message").toString())
                                .build();

                return dto;
        }

        public BaseResponseDto deleteCategory(long categoryId) {

                simpleJdbcCall.withProcedureName("sp_CRUD_category").declareParameters(
                                new SqlParameter("id", Types.BIGINT),
                                new SqlParameter("name", Types.VARCHAR),
                                new SqlParameter("description", Types.VARCHAR),
                                new SqlParameter("slug", Types.VARCHAR),
                                new SqlParameter("image", Types.VARCHAR),
                                new SqlParameter("action", Types.VARCHAR));

                MapSqlParameterSource parameterSource = new MapSqlParameterSource();
                parameterSource.addValue("id", categoryId);
                parameterSource.addValue("action", "DELETE");

                Map<String, Object> result = simpleJdbcCall.execute(parameterSource);

                BaseResponseDto dto = BaseResponseDto.builder()
                                .statusCode((int) result.get("statusCode"))
                                .message(result.get("message").toString())
                                .build();

                return dto;
        }
}
