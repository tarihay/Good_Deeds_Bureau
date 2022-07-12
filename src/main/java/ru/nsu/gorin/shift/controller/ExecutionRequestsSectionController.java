package ru.nsu.gorin.shift.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.nsu.gorin.shift.repository.model.ExecutionRequestEntity;
import ru.nsu.gorin.shift.repository.model.RequestEntity;
import ru.nsu.gorin.shift.repository.model.UserEntity;
import ru.nsu.gorin.shift.service.ExecutionRequestsService;

import java.util.List;

@Api
@RestController
@RequestMapping("/execution_requests")
public class ExecutionRequestsSectionController {
    private ExecutionRequestsService executionRequestsService;

    @Autowired
    public ExecutionRequestsSectionController(ExecutionRequestsService executionRequestsService) {
        this.executionRequestsService = executionRequestsService;
    }

    @GetMapping("/get/all")
    public List<ExecutionRequestEntity> getAllExecutionRequests() {
        return executionRequestsService.selectAll();
    }

    @GetMapping("/get/my/ingoing")
    public List<ExecutionRequestEntity> getCurrentUsersIngoingExecutionRequests(@AuthenticationPrincipal UserEntity customer) {
        return executionRequestsService.selectCurrentUsersIngoingExecutionRequests(customer.getNickname());
    }

    @GetMapping("/get/my/outgoing")
    public List<ExecutionRequestEntity> getCurrentUsersOutgoingExecutionRequests(@AuthenticationPrincipal UserEntity customer) {
        return executionRequestsService.selectCurrentUsersOutgoingExecutionRequests(customer.getNickname());
    }

    @PostMapping("/post/new_execution_request")
    public void createNewExecutionRequest(@AuthenticationPrincipal UserEntity executor,
                                          @RequestParam UserEntity customer,
                                          @RequestParam String info,
                                          @RequestParam RequestEntity requestInfo,
                                          int suggestedKarmaCount) {
        ExecutionRequestEntity executionRequest =
                new ExecutionRequestEntity(customer.getNickname(), executor.getNickname(),
                        info, requestInfo.getKarmaCount(), suggestedKarmaCount);
        executionRequestsService.saveNew(executionRequest);
    }
}
