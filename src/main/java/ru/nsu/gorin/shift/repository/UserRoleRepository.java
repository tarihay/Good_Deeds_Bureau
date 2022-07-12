package ru.nsu.gorin.shift.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nsu.gorin.shift.repository.model.UserRoleEntity;

public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long> {
}
