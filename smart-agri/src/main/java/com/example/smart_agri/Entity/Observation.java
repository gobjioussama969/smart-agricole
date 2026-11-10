package com.example.smart_agri.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Observation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate dateObservation;
    private String description;

    @ManyToOne
    @JoinColumn(name = "id_plantation")
    private Plantation plantation;

    @ManyToOne
    @JoinColumn(name = "id_auteur")
    private Utilisateur auteur;
}

