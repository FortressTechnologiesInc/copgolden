package com.goldenkat.bank.controller;

import com.goldenkat.bank.entity.User;
import com.goldenkat.bank.service.AccountService;
import com.goldenkat.bank.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    private final UserService userService;
    private final AccountService accountService;

    public DashboardController(UserService userService,
                               AccountService accountService) {
        this.userService = userService;
        this.accountService = accountService;
    }

    @GetMapping("/dashboard")
    public String dashboard(Authentication authentication, Model model) {
        User user = userService.findByUsername(authentication.getName());
        model.addAttribute("user", user);
        model.addAttribute("accounts", accountService.getAccounts(user));
        return "dashboard";
    }
}
