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
@Table(name = "`service`")
public class Service {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@ManyToOne
	@JoinColumn(name = "status_id")
	private ServiceStatus serviceStatus;

	@ManyToOne
	@JoinColumn(name = "center_id")
	private SpaCenter spaCenter;
	
	private String name;
	
	private String description;
	
	private float price;
	
	@OneToMany(mappedBy = "service")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private List<Booking> bookings;
	
	@OneToMany(mappedBy = "service")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private List<DraftBooking> drafts;
	
	@OneToMany(mappedBy = "service")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private List<ServicePlan> servicePlans;
	
	@OneToMany(mappedBy = "service")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private List<ServiceOption> serviceOptions;
}
