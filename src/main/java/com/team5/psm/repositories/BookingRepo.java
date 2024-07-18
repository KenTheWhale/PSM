package com.team5.psm.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.team5.psm.models.entity_models.Booking;

public interface BookingRepo extends JpaRepository<Booking, Long> {
	List<Booking> findAllByPet_User_Account_IdOrderByCreateDateAsc(Long id);
}
