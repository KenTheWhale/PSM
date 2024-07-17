package com.team5.psm.models.entity_models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @OneToOne
    @JoinColumn(name = "status_id")
    private Service_Status status;

    @ManyToOne
    @JoinColumn(name = "center_id")
    private Spa_Center center;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private double price;

    @ManyToMany
    @JoinTable(
      name = "booking_service",
      joinColumns = @JoinColumn(name = "booking_id"),
      inverseJoinColumns = @JoinColumn(name = "service_id")
    )
    private List<Service> service;
}
