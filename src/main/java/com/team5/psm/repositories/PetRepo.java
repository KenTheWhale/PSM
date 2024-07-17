package com.team5.psm.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.team5.psm.models.entity_models.Pet;

public interface PetRepo extends JpaRepository<Pet, Long> {
	List<Pet> findAllByUser_Account_Id(Long id);
}
