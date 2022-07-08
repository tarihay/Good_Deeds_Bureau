package ru.nsu.gorin.shift.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.nsu.gorin.shift.repository.mapper.AccountInfoEntityRowMapper;
import ru.nsu.gorin.shift.repository.model.AccountInfoEntity;

import java.util.List;

/**
 * Класс-репозиторий для сущности "информации об аккаунте"
 * @see AccountInfoEntity
 */
@Repository
public class AccountsRepository {

    private JdbcTemplate jdbcTemplate;
    private AccountInfoEntityRowMapper rowMapper;

    @Autowired
    public AccountsRepository(JdbcTemplate jdbcTemplate, AccountInfoEntityRowMapper rowMapper){
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
    }

    /**
     * Метод с помощью sql-запроса находит данные человека по никнейму в базе данных
     * Никнейм уникален
     * @param currentNickname никнейм человека, данные которого нужно найти
     * @return возвращает данные человека, завернутые в сущность
     */
    public AccountInfoEntity selectCurrent(String currentNickname) {
        return jdbcTemplate.query("Select * from accounts where nickname=?", new Object[]{currentNickname}, rowMapper)
                .stream().findAny().orElse(null);
    }

    /**
     * Метод с помощью sql-запроса находит человека по id в базе данных и обновляет его данные
     * @param id уникальный идентификатор человека, данные которого нужно обновить
     * @param newInfo новые данные
     */
    public void updateCurrent(long id, AccountInfoEntity newInfo) {
        jdbcTemplate.update("Update accounts set nickname=?, first_name=?, last_name=? where id=?",
                newInfo.getNickname(), newInfo.getFirstName(), newInfo.getLastName(), id);
    }

    /**
     * Метод с помощью sql-запроса находит количество человека по никнейму в базе данных и обновляет его
     * Вызывается после выполнения человеком какого-то запроса другого человека
     * @param currentNickname никнейм человека, выполнившего чей-то запрос
     * @param additionalKarmaCount количество кармы, которое передали человеку
     */
    public void updateCurrentUsersKarma(String currentNickname, int additionalKarmaCount) {
        int currentKarmaCount = jdbcTemplate.queryForObject("Select karma_count from accounts where nickname=?",
                    new Object[]{currentNickname}, Integer.class);
        jdbcTemplate.update("Update accounts set karma_count=? where nickname=?", currentKarmaCount + additionalKarmaCount,
                currentNickname);
    }

    /**
     * Метод с помощью sql-запроса добавляет в базу данных нового человека
     * @param newAccount данные нового пользователя
     */
    public void saveNew(AccountInfoEntity newAccount) {
        jdbcTemplate.update("Insert into accounts Values(1, ?, ?, ?, ?)", newAccount.getNickname(),
                newAccount.getFirstName(), newAccount.getLastName(), newAccount.getKarmaCount());
    }
}
