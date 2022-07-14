package ru.nsu.gorin.shift.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nsu.gorin.shift.repository.model.UserEntity;

@Repository
public interface UserRepositoryJpa extends JpaRepository<UserEntity, Long> {
    UserEntity save(UserEntity user);
}
