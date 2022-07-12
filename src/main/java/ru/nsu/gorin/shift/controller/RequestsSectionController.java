package ru.nsu.gorin.shift.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.nsu.gorin.shift.repository.model.RequestEntity;
import ru.nsu.gorin.shift.repository.model.UserEntity;
import ru.nsu.gorin.shift.service.RequestsService;

import java.util.List;

/**
 * Класс-контроллер раздела "Все запросы добрых дел"
 */
@Api
@RestController
@RequestMapping("/requests")
public class RequestsSectionController {
    private RequestsService requestsService;

    @Autowired
    public RequestsSectionController(RequestsService requestsService) {
        this.requestsService = requestsService;
    }

    @GetMapping("/get/all")
    public List<RequestEntity> getAllRequests() {
        return requestsService.selectAll();
    }

    @GetMapping("/get/my")
    public List<RequestEntity> getCurrentUsersRequests(@AuthenticationPrincipal UserEntity customer) {
        return requestsService.selectCurrentUsersRequests(customer.getNickname());
    }

    @GetMapping("/get/all/current")
    public RequestEntity getCurrent(@RequestParam long id) {
        return requestsService.selectCurrenRequest(id);
    }

    @PostMapping("/post/new_request")
    public void createRequest(@AuthenticationPrincipal UserEntity customer,
                              @RequestParam String info,
                              @RequestParam int karmaCount) {
        RequestEntity newRequest = new RequestEntity(customer.getNickname(), info, karmaCount);
        requestsService.saveNew(newRequest);
    }
}
