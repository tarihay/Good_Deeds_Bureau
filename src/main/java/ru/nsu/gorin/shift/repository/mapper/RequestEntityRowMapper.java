package ru.nsu.gorin.shift.repository.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.nsu.gorin.shift.repository.model.RequestEntity;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class RequestEntityRowMapper implements RowMapper<RequestEntity> {

    @Override
    public RequestEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        RequestEntity result = new RequestEntity();
        result.setId(rs.getLong("id"));
        result.setCustomerNickname((rs.getString("customer_nickname")));
        result.setRequestInfo(rs.getString("request_info"));
        result.setKarmaCount(rs.getInt("karma_count"));
        return result;
    }
}
