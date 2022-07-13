package ru.nsu.gorin.shift.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nsu.gorin.shift.model.AuthenticationRequestDto;
import ru.nsu.gorin.shift.repository.model.UserEntity;
import ru.nsu.gorin.shift.security.jwt.JwtTokenProvider;
import ru.nsu.gorin.shift.service.UserService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth/")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    private final UserService userService;

    @Autowired
    public AuthenticationController(AuthenticationManager authenticationManager,
                                          JwtTokenProvider jwtTokenProvider,
                                          UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }


    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthenticationRequestDto requestDto) {
        try {
            String nickname = requestDto.getNickname();
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(nickname,
                            requestDto.getPassword()));

            UserEntity user = userService.findByNickname(nickname);

            if (user == null) {
                throw new UsernameNotFoundException("User with nickname: " + nickname + " not found");
            }

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