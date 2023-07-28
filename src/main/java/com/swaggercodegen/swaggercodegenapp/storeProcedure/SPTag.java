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

import com.swaggercodegen.swaggercodegenapp.mapper.TagMapper;
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
        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
        simpleJdbcCall.withProcedureName("sp_CRUD_tag").declareParameters(
                new SqlParameter("id", Types.BIGINT),
                new SqlParameter("name", Types.VARCHAR),
                new SqlParameter("action", Types.VARCHAR)).returningResultSet("rsGetAllTags",
                        new TagMapper());

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id", null);
        parameterSource.addValue("name", null);
        parameterSource.addValue("action", null);
        Map<String, Object> result = simpleJdbcCall.execute(parameterSource);
        @SuppressWarnings("unchecked")
        List<TagDto> dtos = (List<TagDto>) result.get("rsGetAllTags");
        return dtos;
    }

    public void insertTag(TagDto tagDto) {
        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
        simpleJdbcCall.withProcedureName("sp_CRUD_tag").declareParameters(
                new SqlParameter("id", Types.INTEGER),
                new SqlParameter("name", Types.VARCHAR),
                new SqlParameter("action", Types.VARCHAR));

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id", 0);
        parameterSource.addValue("name", tagDto.getName());
        parameterSource.addValue("action", "INSERT");

        simpleJdbcCall.execute(parameterSource);
    }

    public void updateTag(TagDto tagDto) {
        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
        simpleJdbcCall.withProcedureName("sp_CRUD_tag").declareParameters(
                new SqlParameter("id", Types.BIGINT),
                new SqlParameter("name", Types.VARCHAR),
                new SqlParameter("action", Types.VARCHAR));

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id", tagDto.getId());
        parameterSource.addValue("name", tagDto.getName());
        parameterSource.addValue("action", "UPDATE");

        simpleJdbcCall.execute(parameterSource);
    }

    public void deleteTag(long tagId) {
        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
        simpleJdbcCall.withProcedureName("sp_CRUD_tag").declareParameters(
                new SqlParameter("id", Types.BIGINT),
                new SqlParameter("name", Types.VARCHAR),
                new SqlParameter("action", Types.VARCHAR));

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id", tagId);
        parameterSource.addValue("action", "DELETE");

        simpleJdbcCall.execute(parameterSource);
    }

}
