package ru.nsu.gorin.shift.repository.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.nsu.gorin.shift.repository.model.UserEntity;

import java.sql.ResultSet;
import java.sql.SQLException;


@Component
public class UserEntityRowMapper implements RowMapper<UserEntity> {
    public static final String STANDARD_ROLE = "USER";
    public static final int STANDARD_AMOUNT_OF_KARMA = 1000;

    @Override
    public UserEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        UserEntity result = new UserEntity();
        result.setId(rs.getLong("id"));
        result.setNickname(rs.getString("nickname"));
        result.setFirstName(rs.getString("first_name"));
        result.setLastName(rs.getString("last_name"));
        result.setPassword(rs.getString("password"));
        result.setKarmaCount(STANDARD_AMOUNT_OF_KARMA);
        return result;
    }
}
