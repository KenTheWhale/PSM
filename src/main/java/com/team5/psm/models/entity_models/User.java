package com.team5.psm.models.entity_models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "\"user\"")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @OneToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @Column
    private String name;

    @Column
    private String phone;

    @Column
    private LocalDate dob;

    @Column
    private String address;

    @OneToMany(mappedBy = "owner")
    private List<Pet> pets;

    @OneToOne(mappedBy = "manager")
    private Spa_Center center;
}
