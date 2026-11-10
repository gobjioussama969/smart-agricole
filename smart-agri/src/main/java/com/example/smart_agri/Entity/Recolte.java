package com.example.smart_agri.Entity;

import com.example.smart_agri.Enum.UniteRecolte;
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
public class Recolte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate dateRecolte;
    private Double quantite;

    @Enumerated(EnumType.STRING)
    private UniteRecolte unite;

    private String commentaire;

    @ManyToOne
    @JoinColumn(name = "id_plantation")
    private Plantation plantation;

    @ManyToOne
    @JoinColumn(name = "id_auteur")
    private Utilisateur auteur;
}
