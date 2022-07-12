package ru.nsu.gorin.shift.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.nsu.gorin.shift.repository.mapper.RequestEntityRowMapper;
import ru.nsu.gorin.shift.repository.model.RequestEntity;

import java.util.List;

/**
 * Класс-репозиторий для сущности "запроса/просьбы"
 * @see RequestEntity
 */
@Repository
public class RequestsRepository {
    private JdbcTemplate jdbcTemplate;
    private RequestEntityRowMapper rowMapper;

    @Autowired
    public RequestsRepository(JdbcTemplate jdbcTemplate, RequestEntityRowMapper rowMapper){
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
    }

    /**
     * Метод с помощью sql-запроса находит все просьбы всех пользователей
     * @return возвращает список со всеми просьбами
     */
    public List<RequestEntity> selectAll() {
        return jdbcTemplate.query("SELECT * FROM requests", rowMapper);
    }

    /**
     * Метод с помощью sql-запроса находит все просьбы данного пользователя
     * @param currentNickname никнейм пользователя
     * @return возвращает список всех просьб пользователя
     */
    public List<RequestEntity> selectCurrentUsersRequests(String currentNickname) {
        return jdbcTemplate.query("SELECT * FROM requests WHERE customer_nickname=?", new Object[]{currentNickname}, rowMapper);
    }

    /**
     * Метод с помощью sql-запроса находит просьбу по id
     * @param id уникальный идентификатор просьбы
     * @return возвращает просьбу, обернутую в сущность
     */
    public RequestEntity selectCurrenRequest(long id) {
        return jdbcTemplate.query("SELECT * FROM requests WHERE id=?", new Object[]{id}, rowMapper)
                .stream().findAny().orElse(null);
    }

    /**
     * Метод с помощью sql-запроса находит просьбу по id и обновляет ее данные
     * @param id уникальный идентификатор просьбы
     * @param newInfo новые данные, обернутые в сущность
     */
    public void updateCurrent(long id, RequestEntity newInfo) {
        jdbcTemplate.update("UPDATE requests SET request_info=?, karma_count=? WHERE id=?",
                newInfo.getRequestInfo(), newInfo.getKarmaCount(), id);
    }

    /**
     * Метод с помощью sql-запроса добавляет в базу данных новую просьбу
     * @param newRequest данные новой просьбы, обернутые в сущность
     */
    public void saveNew(RequestEntity newRequest) {
        jdbcTemplate.update("INSERT INTO requests VALUES(1, ?, ?, ?)", newRequest.getCustomerNickname(),
                newRequest.getRequestInfo(), newRequest.getKarmaCount());
    }

    /**
     * Метод с помощью sql-запроса находит в базе данных просьбу по id и удаляет ее
     * @param id уникальный идентификатор просьбы
     */
    public void deleteCurrentUsersRequest(long id) {
        jdbcTemplate.update("DELETE FROM requests WHERE id=?", id);
    }
}
