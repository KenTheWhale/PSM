package com.team5.psm.models.entity_models;

import java.util.List;

import jakarta.persistence.*;
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
@Table(name = "service_status")
public class ServiceStatus {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true)
	private String status;

	@OneToMany(mappedBy = "serviceStatus")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private List<Services> services;
}