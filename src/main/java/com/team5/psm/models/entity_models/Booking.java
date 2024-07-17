package com.team5.psm.models.entity_models;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
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
@Table(name = "booking")
public class Booking {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@ManyToOne
	@JoinColumn(name = "pet_id")
	private Pet pet;
	
	@ManyToOne
	@JoinColumn(name = "service_id")
	private Service service;
	
	@ManyToOne
	@JoinColumn(name = "status_id")
	private BookingStatus bookingStatus;
	
	private String content;
	
	private boolean isFeedbacked;
	
	@Column(name = "create_date")
	private LocalDate createDate;
	
	private float price;
	
	@OneToMany(mappedBy = "booking")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private List<Feedback> feedbacks;
}
