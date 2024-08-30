package com.damvih.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "players", indexes = @Index(name = "idx_name", columnList = "name"))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(unique = true, nullable = false, length = 16)
    private String name;

}
