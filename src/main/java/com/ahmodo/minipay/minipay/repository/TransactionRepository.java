package com.ahmodo.minipay.minipay.repository;

import com.ahmodo.minipay.minipay.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}