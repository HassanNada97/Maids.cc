package com.demo.maids.repository;

import com.demo.maids.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    public Account findUserByUsername(String username);
}
