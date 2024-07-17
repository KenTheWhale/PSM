package com.team5.psm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.team5.psm.models.entity_models.SpaCenter;

import java.util.Optional;

public interface SpaCenterRepo extends JpaRepository<SpaCenter, Long> {

    Optional<SpaCenter> findByName(String name);

}
