package com.team5.psm.repositories;

import com.team5.psm.models.entity_models.SpaCenter;
import org.springframework.data.jpa.repository.JpaRepository;

import com.team5.psm.models.entity_models.Services;

import java.util.Optional;

public interface ServicesRepo extends JpaRepository<Services, Long> {
    Optional<Services> findByNameAndSpaCenter(String name, SpaCenter spaCenter);
}
