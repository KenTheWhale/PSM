package com.team5.psm.repositories;

import com.team5.psm.consts.ERoleEnum;
import com.team5.psm.pojo.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERoleEnum name);
}
