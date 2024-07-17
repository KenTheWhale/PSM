package com.team5.psm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.team5.psm.models.entity_models.Feedback;

public interface FeedbackRepo extends JpaRepository<Feedback, Long> {

}
