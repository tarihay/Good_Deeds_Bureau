package ru.nsu.gorin.shift.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.nsu.gorin.shift.repository.mapper.ExecutionRequestEntityRowMapper;
import ru.nsu.gorin.shift.repository.model.ExecutionRequestEntity;

import java.util.List;

/**
 * Класс-репозиторий для сущности "выполнение запроса"
 * @see ExecutionRequestEntity
 */
@Repository
public class ExecutionRequestsRepository {
    private JdbcTemplate jdbcTemplate;
    private ExecutionRequestEntityRowMapper rowMapper;

    @Autowired
    public ExecutionRequestsRepository(JdbcTemplate jdbcTemplate, ExecutionRequestEntityRowMapper rowMapper){
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
    }

    /**
     * Метод с помощью sql-запроса возвращает запросы на выполнение его просьбы
     * @param currentNickname никнейм владельца просьб
     * @return возвращает список всех запросов на выполнение
     */
    public List<ExecutionRequestEntity> selectCurrentUsersExecutionRequests(String currentNickname) {
        return jdbcTemplate.query("Select * from execution_requests where customer_nickname=?",
                new Object[]{currentNickname}, rowMapper);
    }

    /**
     * Метод с помощью sql-запроса показывает конкретный запрос на выполнение его просьбы
     * @param id номер запроса на выполнение
     * @return возвращает данные запроса на выполнение, обернутые в сущность
     */
    public ExecutionRequestEntity selectCurrenExecutionRequest(long id) {
        return jdbcTemplate.query("Select * from execution_requests where id=?", new Object[]{id}, rowMapper)
                .stream().findAny().orElse(null);
    }

    /**
     * Метод с помощью sql-запроса добавляет в базу данных новый запрос на выполнение просьбы
     * @param newExecutionRequest данные нового запроса на выполнение, обернутые в сущность
     */
    public void saveNew(ExecutionRequestEntity newExecutionRequest) {
        jdbcTemplate.update("Insert into execution_requests Values(1, ?, ?, ?, ?, ?)", newExecutionRequest.getCustomerNickname(),
                newExecutionRequest.getExecutorNickname(), newExecutionRequest.getExecutionRequestInfo(),
                newExecutionRequest.getActualKarmaCount(), newExecutionRequest.getSuggestedKarmaCount());
    }

    /**
     * Метод с помощью sql-запроса находит по id запрос на выполнение и удаляет его из базы данных
     * @param id номер запроса на выполнение
     */
    public void deleteCurrentUsersExecutionRequest(long id) {
        jdbcTemplate.update("Delete from execution_requests where id=?", id);
    }
}
