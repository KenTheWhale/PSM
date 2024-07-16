package com.team5.psm.pojo;

import com.team5.psm.consts.ERoleEnum;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ERoleEnum name;
}
