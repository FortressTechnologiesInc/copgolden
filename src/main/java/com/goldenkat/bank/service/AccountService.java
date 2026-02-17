package com.goldenkat.bank.service;

import com.goldenkat.bank.entity.Account;
import com.goldenkat.bank.entity.User;
import com.goldenkat.bank.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account createDefaultAccount(User owner) {
        Account account = new Account();
        account.setOwner(owner);
        account.setAccountNumber("GK-" + UUID.randomUUID().toString().substring(0, 10));
        account.setCurrency("CAD");
        account.setBalance(BigDecimal.ZERO);
        return accountRepository.save(account);
    }

    public List<Account> getAccounts(User owner) {
        return accountRepository.findByOwner(owner);
    }
}
