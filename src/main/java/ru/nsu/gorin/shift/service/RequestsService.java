package ru.nsu.gorin.shift.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nsu.gorin.shift.repository.RequestsRepository;
import ru.nsu.gorin.shift.repository.model.RequestEntity;

import java.util.List;

@Service
public class RequestsService {
    private final RequestsRepository requestsRepository;

    @Autowired
    public RequestsService(RequestsRepository requestsRepository) {
        this.requestsRepository = requestsRepository;
    }

    public List<RequestEntity> selectAll() {
        return requestsRepository.selectAll();
    }

    public List<RequestEntity> selectCurrentUsersRequests(String currentNickname) {
        return requestsRepository.selectCurrentUsersRequests(currentNickname);
    }

    public RequestEntity selectCurrenRequest(long id) {
        return requestsRepository.selectCurrenRequest(id);
    }

    public void updateCurrent(long id, RequestEntity newInfo) {
        requestsRepository.updateCurrent(id, newInfo);
    }

    public void saveNew(RequestEntity newRequest) {
        requestsRepository.saveNew(newRequest);
    }

    public void deleteCurrentUsersRequest(long id) {
        requestsRepository.deleteCurrentUsersRequest(id);
    }
}
