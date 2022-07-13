package ru.nsu.gorin.shift.security.jwt;

import ru.nsu.gorin.shift.repository.model.UserEntity;

public final class JwtUserFactory {

    public JwtUserFactory() {
    }

    public static JwtUser create(UserEntity user) {
        return new JwtUser(
                user.getId(),
                user.getNickname(),
                user.getFirstName(),
                user.getLastName(),
                user.getPassword()
        );
    }
}
