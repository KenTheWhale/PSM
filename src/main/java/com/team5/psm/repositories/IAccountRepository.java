package com.team5.psm.repositories;

import com.team5.psm.pojo.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAccountRepository extends JpaRepository<Account, Long> {
}
