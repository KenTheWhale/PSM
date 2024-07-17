package com.team5.psm.models.entity_models;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "pet")
public class Pet {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@ManyToOne
	@JoinColumn(name = "owner_id")
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "species_id")
	private Species species;
	
	private String name;
	
	private String description;
	
	@OneToMany(mappedBy = "pet")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private List<Booking> bookings;
	
	@OneToMany(mappedBy = "pet")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private List<DraftBooking> drafts;
}
