package ru.nsu.gorin.shift.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.nsu.gorin.shift.repository.UserRepository;
import ru.nsu.gorin.shift.repository.model.UserEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
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
