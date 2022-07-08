package ru.nsu.gorin.shift.repository.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.nsu.gorin.shift.repository.model.AccountInfoEntity;

import java.sql.ResultSet;
import java.sql.SQLException;

import static ru.nsu.gorin.shift.Constants.STANDARD_AMOUNT_OF_KARMA;

@Component
public class AccountInfoEntityRowMapper implements RowMapper<AccountInfoEntity> {
    @Override
    public AccountInfoEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        AccountInfoEntity result = new AccountInfoEntity();
        result.setId(rs.getLong("id"));
        result.setNickname(rs.getString("nickname"));
        result.setFirstName(rs.getString("first_name"));
        result.setLastName(rs.getString("last_name"));
        result.setKarmaCount(STANDARD_AMOUNT_OF_KARMA);
        return result;
    }
}
