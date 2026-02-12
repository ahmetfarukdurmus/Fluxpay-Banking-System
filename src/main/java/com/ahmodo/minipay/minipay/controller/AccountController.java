package com.ahmodo.minipay.minipay.controller;

import com.ahmodo.minipay.minipay.entity.Account;
import com.ahmodo.minipay.minipay.entity.Transaction;
import com.ahmodo.minipay.minipay.service.AccountService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/accounts")
@CrossOrigin(origins = "*")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public Account createAccount(@RequestBody Account account) {
        return accountService.createAccount(account);
    }

    @GetMapping("/{id}")
    public Account getAccount(@PathVariable Long id) {
        return accountService.getAccount(id);
    }

    @GetMapping
    public List<Account> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    @PostMapping("/transfer")
    public String transfer(@RequestParam Long from, @RequestParam Long to, @RequestParam BigDecimal amount, @RequestParam String pin) {
        accountService.transferMoney(from, to, amount, pin);
        return "Para başarıyla gönderildi !";
    }

    @GetMapping("/transactions")
    public List<Transaction> getAllTransactions() {
        return accountService.getAllTransactions();
    }
}