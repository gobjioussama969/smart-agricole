package com.example.smart_agri.Entity;

import com.example.smart_agri.Enum.StatutPlantation;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Plantation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate datePlantation;
    private Double superficie;
    private Integer quantitePlantee;
    private LocalDate dateRecoltePrevue;
    private String commentaire;

    @Enumerated(EnumType.STRING)
    private StatutPlantation statut;

    @ManyToOne
    @JoinColumn(name = "id_parcelle")
    private Parcelle parcelle;

    @ManyToOne
    @JoinColumn(name = "id_campagne")
    private Campagne campagne;

    @ManyToOne
    @JoinColumn(name = "id_variete")
    private Variete variete;

    // Une plantation possède plusieurs activités
    @OneToMany(mappedBy = "plantation", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Observation> observations;

    @OneToMany(mappedBy = "plantation", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Irrigation> irrigations;

    @OneToMany(mappedBy = "plantation", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Fertilisation> fertilisations;

    @OneToMany(mappedBy = "plantation", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Traitement> traitements;

    @OneToMany(mappedBy = "plantation", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Recolte> recoltes;
}
