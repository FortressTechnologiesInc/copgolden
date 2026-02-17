package com.goldenkat.bank.service;

import com.goldenkat.bank.entity.Account;
import com.goldenkat.bank.entity.Transaction;
import com.goldenkat.bank.repository.TransactionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Transactional
    public Transaction createTransaction(Account account, BigDecimal amount, String type, String description) {
        Transaction tx = new Transaction();
        tx.setAccount(account);
        tx.setAmount(amount);
        tx.setType(type);
        tx.setDescription(description);
        return transactionRepository.save(tx);
    }

    public List<Transaction> getRecentTransactions(Account account) {
        return transactionRepository.findByAccountOrderByCreatedAtDesc(account);
    }
}
