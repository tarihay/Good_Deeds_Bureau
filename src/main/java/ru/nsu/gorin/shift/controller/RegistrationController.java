package ru.nsu.gorin.shift.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.nsu.gorin.shift.repository.model.UserEntity;
import ru.nsu.gorin.shift.service.UserService;
import org.springframework.ui.Model;

import javax.validation.Valid;

@Api
@RestController
public class RegistrationController {
    private UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new UserEntity());

        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@ModelAttribute("userForm") @Valid UserEntity userForm,
                          BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "registration";
        }
        if (!userForm.getPassword().equals(userForm.getPasswordConfirm())){
            model.addAttribute("passwordError", "Passwords do not match");
            return "registration";
        }
        if (!userService.saveNew(userForm)){
            model.addAttribute("usernameError", "A user with the same name already exists");
            return "registration";
        }

        return "redirect:/";
    }
}
