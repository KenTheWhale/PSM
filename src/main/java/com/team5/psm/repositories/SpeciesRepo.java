package com.team5.psm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.team5.psm.models.entity_models.Species;

public interface SpeciesRepo extends JpaRepository<Species, Long> {
	Species findByName(String name);
}
