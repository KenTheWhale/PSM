package com.team5.psm.repositories;

import com.team5.psm.models.entity_models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
}
