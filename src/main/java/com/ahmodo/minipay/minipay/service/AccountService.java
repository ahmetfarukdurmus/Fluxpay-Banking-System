package com.ahmodo.minipay.minipay.service;

import com.ahmodo.minipay.minipay.entity.Account;
import com.ahmodo.minipay.minipay.entity.Transaction;
import com.ahmodo.minipay.minipay.repository.AccountRepository;
import com.ahmodo.minipay.minipay.repository.TransactionRepository;
import org.springframework.security.crypto.password.PasswordEncoder; // Import doğru olmalı
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final PasswordEncoder passwordEncoder;


    public AccountService(AccountRepository accountRepository,
                          TransactionRepository transactionRepository,
                          PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Account createAccount(Account account) {
        account.setPin(passwordEncoder.encode(account.getPin()));
        return accountRepository.save(account);
    }

    public Account getAccount(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hesap bulunamadı!"));
    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    @Transactional
    public void transferMoney(Long fromId, Long toId, BigDecimal amount, String incomingPin) {
        Account fromAccount = getAccount(fromId);
        Account toAccount = getAccount(toId);

        if (!passwordEncoder.matches(incomingPin, fromAccount.getPin())) {
            throw new RuntimeException("Şifre yanlış!");
        }

        if (fromAccount.getBalance().compareTo(amount) < 0) {
            throw new RuntimeException("Bakiye yetersiz!");
        }

        fromAccount.setBalance(fromAccount.getBalance().subtract(amount));
        toAccount.setBalance(toAccount.getBalance().add(amount));

        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);

        Transaction transaction = new Transaction();
        transaction.setSenderId(fromId);
        transaction.setReceiverId(toId);
        transaction.setAmount(amount);
        transaction.setTimestamp(LocalDateTime.now());
        transaction.setStatus("SUCCESS");

        transactionRepository.save(transaction);
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }
}