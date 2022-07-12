package ru.nsu.gorin.shift.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.nsu.gorin.shift.service.UserService;

/**
 * Класс-контроллер раздела "Аккаунт пользователя"
 */
@Api
@RestController
@RequestMapping("/account")
public class AccountController {

    private final UserService userService;

    @Autowired
    public AccountController (UserService userService) {
        this.userService = userService;
    }

    @PostMapping("post/update/nickname")
    public void updateNickname(@RequestParam long id, String nickname) {
        userService.updateCurrentsNickname(id, nickname);
    }

    @PostMapping("post/update/first_name")
    public void updateFirstName(@RequestParam long id, String firstName) {
        userService.updateCurrentsNickname(id, firstName);
    }

    @PostMapping("post/update/last_name")
    public void updateLastName(@RequestParam long id, String lastName) {
        userService.updateCurrentsNickname(id, lastName);
    }

    @PostMapping("post/update/password")
    public void updatePassword(@RequestParam long id, String password) {
        userService.updateCurrentsNickname(id, password);
    }
}
