package com.ahmodo.minipay.minipay.repository;


import com.ahmodo.minipay.minipay.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
}
