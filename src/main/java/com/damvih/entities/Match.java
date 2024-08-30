package com.damvih.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "matches")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "player1", referencedColumnName = "id", nullable = false)
    private Player playerOne;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "player2", referencedColumnName = "id", nullable = false)
    private Player playerTwo;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "winner", referencedColumnName = "id", nullable = false)
    private Player winner;

}
