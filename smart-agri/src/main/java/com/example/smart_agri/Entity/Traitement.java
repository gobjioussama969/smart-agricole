package com.example.smart_agri.Entity;

import com.example.smart_agri.Enum.UniteQuantite;
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
public class Traitement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate dateTraitement;
    private String nomProduit;
    private String cible;
    private Double quantite;

    @Enumerated(EnumType.STRING)
    private UniteQuantite unite;

    private String methodeApplication;
    private String commentaire;

    @ManyToOne
    @JoinColumn(name = "id_plantation")
    private Plantation plantation;

    @ManyToOne
    @JoinColumn(name = "id_auteur")
    private Utilisateur auteur;
}
