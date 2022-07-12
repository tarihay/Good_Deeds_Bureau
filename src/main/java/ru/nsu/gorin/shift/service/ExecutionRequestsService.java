package ru.nsu.gorin.shift.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nsu.gorin.shift.repository.ExecutionRequestsRepository;
import ru.nsu.gorin.shift.repository.model.ExecutionRequestEntity;
import ru.nsu.gorin.shift.repository.model.RequestEntity;

import java.util.List;

@Service
public class ExecutionRequestsService {
    private final ExecutionRequestsRepository executionRequestsRepository;

    @Autowired
    public ExecutionRequestsService(ExecutionRequestsRepository executionRequestsRepository) {
        this.executionRequestsRepository = executionRequestsRepository;
    }

    public List<ExecutionRequestEntity> selectAll() {
        return executionRequestsRepository.selectAll();
    }

    public List<ExecutionRequestEntity> selectCurrentUsersIngoingExecutionRequests(String currentNickname) {
        return executionRequestsRepository.selectCurrentUsersIngoingExecutionRequests(currentNickname);
    }

    public List<ExecutionRequestEntity> selectCurrentUsersOutgoingExecutionRequests(String currentNickname) {
        return executionRequestsRepository.selectCurrentUsersOutgoingExecutionRequests(currentNickname);
    }

    public ExecutionRequestEntity selectCurrenRequest(long id) {
        return executionRequestsRepository.selectCurrenExecutionRequest(id);
    }

    public void saveNew(ExecutionRequestEntity newExecutionRequest) {
        executionRequestsRepository.saveNew(newExecutionRequest);
    }

    public void deleteCurrentUsersExecutionRequest(long id) {
        executionRequestsRepository.deleteCurrentUsersExecutionRequest(id);
    }
}
