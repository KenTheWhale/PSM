package com.team5.psm.pojo;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @Column
    private String avatar;

    @Column
    private String background;
}
