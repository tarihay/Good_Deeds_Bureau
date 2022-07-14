package ru.nsu.gorin.shift.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nsu.gorin.shift.repository.UserRepository;
import ru.nsu.gorin.shift.repository.UserRepositoryJpa;
import ru.nsu.gorin.shift.repository.model.UserEntity;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserRepositoryJpa userRepositoryJpa;

    @Autowired
    public UserService(UserRepository userRepository, UserRepositoryJpa userRepositoryJpa) {
        this.userRepository = userRepository;
        this.userRepositoryJpa = userRepositoryJpa;
    }

    public void updateCurrentsNickname(long id, String nickname) {
        userRepository.updateCurrentsNickname(id, nickname);
    }

    public void updateCurrentsFirstName(long id, String firstName) {
        userRepository.updateCurrentsFirstName(id, firstName);
    }

    public void updateCurrentsLastName(long id, String lastName) {
        userRepository.updateCurrentsLastName(id, lastName);
    }

    public void updateCurrentsPassword(long id, String password) {
        userRepository.updateCurrentsPassword(id, password);
    }

    public void updateCurrentUsersKarma(String currentNickname, int additionalKarmaCount) {
        userRepository.updateCurrentUsersKarma(currentNickname, additionalKarmaCount);
    }

    public boolean saveNew(UserEntity newAccount) {
        UserEntity user = userRepository.selectCurrentByNickname(newAccount.getNickname());
        if (user != null) {
            return false;
        }

//        UserEntity newUser = userRepositoryJpa.save(newAccount);
        userRepository.saveNew(newAccount);
        return true;
    }

    public UserEntity findByNickname(String nickname) {
        return userRepository.selectCurrentByNickname(nickname);
    }

    public void deleteCurrentUsersRequest(long id) {
        userRepository.deleteCurrentUser(id);
    }
}
