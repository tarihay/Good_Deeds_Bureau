package ru.nsu.gorin.shift.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.nsu.gorin.shift.repository.mapper.UserEntityRowMapper;
import ru.nsu.gorin.shift.repository.model.UserEntity;

/**
 * Класс-репозиторий для сущности "информации об аккаунте"
 * @see UserEntity
 */
@Repository
public class UserRepository {

    private JdbcTemplate jdbcTemplate;
    private UserEntityRowMapper rowMapper;

    @Autowired
    public UserRepository(JdbcTemplate jdbcTemplate, UserEntityRowMapper rowMapper){
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
    }

    /**
     * Метод с помощью sql-запроса находит данные человека по никнейму в базе данных
     * Никнейм уникален
     * @param id идентификатор человека, данные которого нужно найти
     * @return возвращает данные человека, завернутые в сущность
     */
    public UserEntity selectCurrentById(long id) {
        return jdbcTemplate.query("SELECT * FROM accounts WHERE id=?", new Object[]{id}, rowMapper)
                .stream().findAny().orElse(null);
    }

    /**
     * Метод с помощью sql-запроса находит данные человека по никнейму в базе данных
     * Никнейм уникален
     * @param nickname никнейм человека, данные которого нужно найти
     * @return возвращает данные человека, завернутые в сущность
     */
    public UserEntity selectCurrentByNickname(String nickname) {
        return jdbcTemplate.query("SELECT * FROM accounts WHERE nickname=?", new Object[]{nickname}, rowMapper)
                .stream().findAny().orElse(null);
    }

    /**
     * Метод с помощью sql-запроса находит человека по id в базе данных и обновляет его данные
     * @param id уникальный идентификатор человека, данные которого нужно обновить
     * @param nickname новый никнейм
     */
    public void updateCurrentsNickname(long id, String nickname) {
        jdbcTemplate.update("UPDATE accounts SET nickname=? WHERE id=?", nickname);
    }

    /**
     * Метод с помощью sql-запроса находит человека по id в базе данных и обновляет его данные
     * @param id уникальный идентификатор человека, данные которого нужно обновить
     * @param firstName новый first_name
     */
    public void updateCurrentsFirstName(long id, String firstName) {
        jdbcTemplate.update("UPDATE accounts SET first_name=? WHERE id=?", firstName);
    }

    /**
     * Метод с помощью sql-запроса находит человека по id в базе данных и обновляет его данные
     * @param id уникальный идентификатор человека, данные которого нужно обновить
     * @param lastName новый last_name
     */
    public void updateCurrentsLastName(long id, String lastName) {
        jdbcTemplate.update("UPDATE accounts SET last_name=? WHERE id=?", lastName);
    }

    /**
     * Метод с помощью sql-запроса находит человека по id в базе данных и обновляет его данные
     * @param id уникальный идентификатор человека, данные которого нужно обновить
     * @param password новый пароль
     */
    public void updateCurrentsPassword(long id, String password) {
        jdbcTemplate.update("UPDATE accounts SET password=? WHERE id=?", password);
    }

    /**
     * Метод с помощью sql-запроса находит количество человека по никнейму в базе данных и обновляет его
     * Вызывается после выполнения человеком какого-то запроса другого человека
     * @param currentNickname никнейм человека, выполнившего чей-то запрос
     * @param additionalKarmaCount количество кармы, которое передали человеку
     */
    public void updateCurrentUsersKarma(String currentNickname, int additionalKarmaCount) {
        int currentKarmaCount = jdbcTemplate.queryForObject("SELECT karma_count FROM accounts WHERE nickname=?",
                    new Object[]{currentNickname}, Integer.class);
        jdbcTemplate.update("UPDATE accounts SET karma_count=? WHERE nickname=?", currentKarmaCount + additionalKarmaCount,
                currentNickname);
    }

    /**
     * Метод с помощью sql-запроса добавляет в базу данных нового человека
     * @param newAccount данные нового пользователя
     */
    public void saveNew(UserEntity newAccount) {
        jdbcTemplate.update("INSERT INTO accounts VALUES(1, ?, ?, ?, ?, ?)", newAccount.getNickname(),
                newAccount.getFirstName(), newAccount.getLastName(), newAccount.getRoles(), newAccount.getKarmaCount());
    }
}
