package ru.nsu.gorin.shift.repository.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.nsu.gorin.shift.repository.model.ExecutionRequestEntity;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ExecutionRequestEntityRowMapper implements RowMapper<ExecutionRequestEntity> {
    @Override
    public ExecutionRequestEntity mapRow(ResultSet rs, int i) throws SQLException {
        ExecutionRequestEntity result = new ExecutionRequestEntity();
        result.setId(rs.getLong("id"));
        result.setCustomerNickname((rs.getString("customer_nickname")));
        result.setExecutorNickname(rs.getString("executor_nickname"));
        result.setActualKarmaCount(rs.getInt("actual_karma_count"));
        result.setSuggestedKarmaCount(rs.getInt("suggested_karma_count"));
        result.setExecutionRequestInfo(rs.getString("info"));
        return result;
    }
}
