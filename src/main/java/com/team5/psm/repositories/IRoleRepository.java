package com.team5.psm.repositories;

import com.team5.psm.pojo.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoleRepository extends JpaRepository<Role, Long> {
}
