package com.goldenkat.bank.repository;

import com.goldenkat.bank.entity.Account;
import com.goldenkat.bank.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findByOwner(User owner);
}
