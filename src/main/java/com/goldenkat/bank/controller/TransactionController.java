package com.goldenkat.bank.controller;

import com.goldenkat.bank.entity.Account;
import com.goldenkat.bank.entity.User;
import com.goldenkat.bank.repository.AccountRepository;
import com.goldenkat.bank.service.TransactionService;
import com.goldenkat.bank.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@Controller
@RequestMapping("/transactions")
public class TransactionController {

    private final UserService userService;
    private final AccountRepository accountRepository;
    private final TransactionService transactionService;

    public TransactionController(UserService userService,
                                 AccountRepository accountRepository,
                                 TransactionService transactionService) {
        this.userService = userService;
        this.accountRepository = accountRepository;
        this.transactionService = transactionService;
    }

    @GetMapping
    public String list(Authentication authentication, Model model) {
        User user = userService.findByUsername(authentication.getName());
        Account account = accountRepository.findByOwner(user).stream().findFirst().orElse(null);
        if (account != null) {
            model.addAttribute("transactions", transactionService.getRecentTransactions(account));
            model.addAttribute("account", account);
        }
        return "transactions";
    }

    @PostMapping("/create")
    public String create(Authentication authentication,
                         @RequestParam BigDecimal amount,
                         @RequestParam String type,
                         @RequestParam(required = false) String description) {
        User user = userService.findByUsername(authentication.getName());
        Account account = accountRepository.findByOwner(user).stream().findFirst().orElse(null);
        if (account != null) {
            transactionService.createTransaction(account, amount, type, description);
        }
        return "redirect:/transactions";
    }
}
