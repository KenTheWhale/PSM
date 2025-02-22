package com.team5.psm.models.entity_models;


import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
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
@Table(name = "service_plan")
public class ServicePlan {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@ManyToOne
	@JoinColumn(name = "service_id")
	private Services services;

	private String name;
	
	private float price;
	
	@ManyToMany(mappedBy = "plans")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private List<ServiceOption> options;
	
	@OneToMany(mappedBy = "servicePlan")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private List<DraftBooking> drafts;
	
	@OneToMany(mappedBy = "servicePlan")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private List<Booking> bookings;
}
