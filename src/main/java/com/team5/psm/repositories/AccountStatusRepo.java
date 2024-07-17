package com.team5.psm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.team5.psm.models.entity_models.AccountStatus;

import java.util.Optional;

public interface AccountStatusRepo extends JpaRepository<AccountStatus, Long> {
    Optional<AccountStatus> findByStatus(String status);
}
