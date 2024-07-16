package com.team5.psm.repositories;

import com.team5.psm.pojo.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IAccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByEmailAndPassword(String email, String password);
}
