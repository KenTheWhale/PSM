package com.team5.psm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.team5.psm.models.entity_models.Pet;

import java.util.Optional;

public interface PetRepo extends JpaRepository<Pet, Long> {

    Optional<Pet> findByName(String name);

}
