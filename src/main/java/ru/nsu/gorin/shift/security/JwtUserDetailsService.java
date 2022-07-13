package ru.nsu.gorin.shift.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.nsu.gorin.shift.repository.model.UserEntity;
import ru.nsu.gorin.shift.security.jwt.JwtUser;
import ru.nsu.gorin.shift.security.jwt.JwtUserFactory;
import ru.nsu.gorin.shift.service.UserService;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public JwtUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String nickname) throws UsernameNotFoundException {
        UserEntity user = userService.findByNickname(nickname);

        if (user == null) {
            throw new UsernameNotFoundException("User with nickname: " + nickname + " not found");
        }

        JwtUser jwtUser = JwtUserFactory.create(user);
        return jwtUser;
    }
}