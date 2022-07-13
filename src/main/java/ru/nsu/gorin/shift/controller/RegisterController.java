package ru.nsu.gorin.shift.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nsu.gorin.shift.model.SignupRequestDto;
import ru.nsu.gorin.shift.repository.model.UserEntity;
import ru.nsu.gorin.shift.security.jwt.JwtTokenProvider;
import ru.nsu.gorin.shift.service.UserService;

import java.util.HashMap;
import java.util.Map;

import static ru.nsu.gorin.shift.repository.mapper.UserEntityRowMapper.STANDARD_AMOUNT_OF_KARMA;

@RestController
@RequestMapping("/auth/")
public class RegisterController {
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public RegisterController(UserService userService, JwtTokenProvider jwtTokenProvider) {
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/register")
    public ResponseEntity login(@RequestBody SignupRequestDto requestDto) {
        try {
            String nickname = requestDto.getNickname();

            UserEntity userToFind = userService.findByNickname(nickname);

            if (userToFind != null) {
                return new ResponseEntity<>("Such user is already registered", HttpStatus.CONFLICT);
            }

            UserEntity newUser = new UserEntity();
            newUser.setNickname(requestDto.getNickname());
            newUser.setFirstName(requestDto.getFirstName());
            newUser.setLastName(requestDto.getLastName());
            newUser.setPassword(requestDto.getPassword());
            newUser.setKarmaCount(STANDARD_AMOUNT_OF_KARMA);

            userService.saveNew(newUser);

            String token = jwtTokenProvider.createToken(nickname);

            Map<Object, Object> response = new HashMap<>();
            response.put("nickname", nickname);
            response.put("token", token);

            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid nickname or password");
        }
    }
}
