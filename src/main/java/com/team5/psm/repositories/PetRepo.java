package com.team5.psm.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.team5.psm.models.entity_models.Pet;

import java.util.Optional;

public interface PetRepo extends JpaRepository<Pet, Long> {


    Optional<Pet> findByName(String name);


	List<Pet> findAllByUser_Account_Id(Long id);

}
