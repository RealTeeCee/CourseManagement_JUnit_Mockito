package com.swaggercodegen.swaggercodegenapp.storeProcedure;

import java.sql.Types;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlInOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Component;

import com.swaggercodegen.swaggercodegenapp.mapper.TagMapper;
import com.swaggercodegen.swaggercodegenapp.model.BaseResponseDto;
import com.swaggercodegen.swaggercodegenapp.model.TagDto;

@Component
public class SPTag {
        private SimpleJdbcCall simpleJdbcCall;
        // private final JdbcTemplate jdbcTemplate;

        @Autowired
        JdbcTemplate jdbcTemplate;

        public SPTag(JdbcTemplate jdbcTemplate) {
                // this.jdbcTemplate = jdbcTemplate;
                this.simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);

        }

        public List<TagDto> getAllTags() {
                simpleJdbcCall.withSchemaName("cmdb").withProcedureName("sp_CRUD_tag")

                                .declareParameters(
                                                new SqlInOutParameter("statuscode", Types.INTEGER),
                                                new SqlInOutParameter("message", Types.VARCHAR),
                                                new SqlInOutParameter("resultset", Types.REF_CURSOR, new TagMapper()),
                                                new SqlParameter("pi_id", Types.INTEGER),
                                                new SqlParameter("pi_name", Types.VARCHAR),
                                                new SqlParameter("pi_action", Types.VARCHAR));

                MapSqlParameterSource parameterSource = new MapSqlParameterSource();
                parameterSource.addValue("pi_id", 0);
                parameterSource.addValue("pi_name", "");
                parameterSource.addValue("pi_action", "");
                Map<String, Object> result = simpleJdbcCall.execute(parameterSource);
                @SuppressWarnings("unchecked")
                List<TagDto> dtos = (List<TagDto>) result.get("resultset");
                Integer statusCode = (Integer) result.get("statuscode");
                String message = (String) result.get("message");
                return dtos;
        }

        public BaseResponseDto insertTag(TagDto tagDto) {
                simpleJdbcCall.withSchemaName("cmdb").withProcedureName("sp_CRUD_tag").declareParameters(
                                new SqlInOutParameter("statuscode", Types.INTEGER),
                                new SqlInOutParameter("message", Types.VARCHAR),
                                new SqlInOutParameter("resultset", Types.REF_CURSOR, new TagMapper()),
                                new SqlParameter("pi_id", Types.BIGINT),
                                new SqlParameter("pi_name", Types.VARCHAR),
                                new SqlParameter("pi_action", Types.VARCHAR));

                MapSqlParameterSource parameterSource = new MapSqlParameterSource();                
                parameterSource.addValue("pi_id", 0);
                parameterSource.addValue("pi_name", tagDto.getName());
                parameterSource.addValue("pi_action", "INSERT");

                Map<String, Object> result = simpleJdbcCall.execute(parameterSource);

                BaseResponseDto dto = BaseResponseDto.builder()
                                .statusCode((Integer) result.get("statuscode"))
                                .message(result.get("message").toString())
                                .build();

                return dto;
        }

        public BaseResponseDto updateTag(TagDto tagDto) {
                simpleJdbcCall.withProcedureName("sp_CRUD_tag").declareParameters(
                                new SqlParameter("id", Types.BIGINT),
                                new SqlParameter("name", Types.VARCHAR),
                                new SqlParameter("action", Types.VARCHAR));

                MapSqlParameterSource parameterSource = new MapSqlParameterSource();
                parameterSource.addValue("id", tagDto.getId());
                parameterSource.addValue("name", tagDto.getName());
                parameterSource.addValue("action", "UPDATE");

                Map<String, Object> result = simpleJdbcCall.execute(parameterSource);

                BaseResponseDto dto = BaseResponseDto.builder()
                                .statusCode((Integer) result.get("statusCode"))
                                .message(result.get("message").toString())
                                .build();

                return dto;
        }

        public BaseResponseDto deleteTag(long tagId) {
                simpleJdbcCall.withProcedureName("sp_CRUD_tag").declareParameters(
                                new SqlParameter("id", Types.BIGINT),
                                new SqlParameter("name", Types.VARCHAR),
                                new SqlParameter("action", Types.VARCHAR));

                MapSqlParameterSource parameterSource = new MapSqlParameterSource();
                parameterSource.addValue("id", tagId);
                parameterSource.addValue("action", "DELETE");

                Map<String, Object> result = simpleJdbcCall.execute(parameterSource);

                BaseResponseDto dto = BaseResponseDto.builder()
                                .statusCode((Integer) result.get("statusCode"))
                                .message(result.get("message").toString())
                                .build();

                return dto;
        }

}
