package ru.nsu.gorin.shift.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nsu.gorin.shift.repository.AccountsRepository;
import ru.nsu.gorin.shift.repository.model.AccountInfoEntity;

@Service
public class AccountsService {
    private final AccountsRepository accountsRepository;

    @Autowired
    public AccountsService(AccountsRepository accountsRepository) {
        this.accountsRepository = accountsRepository;
    }

    public AccountInfoEntity selectCurrent(String currentNickname) {
        return accountsRepository.selectCurrent(currentNickname);
    }

    public void updateCurrent(long id, AccountInfoEntity newInfo) {
        accountsRepository.updateCurrent(id, newInfo);
    }

    public void updateCurrentUsersKarma(String currentNickname, int additionalKarmaCount) {
        accountsRepository.updateCurrentUsersKarma(currentNickname, additionalKarmaCount);
    }

    public void saveNew(AccountInfoEntity newAccount) {
        accountsRepository.saveNew(newAccount);
    }
}
