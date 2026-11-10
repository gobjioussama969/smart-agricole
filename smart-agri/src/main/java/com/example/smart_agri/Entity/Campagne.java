package com.example.smart_agri.Entity;

import com.example.smart_agri.Enum.StatutCampagne;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Campagne {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String description;
    private LocalDate dateDebut;
    private LocalDate dateFin;

    @Enumerated(EnumType.STRING)
    private StatutCampagne statut;

    @ManyToMany
    @JoinTable(name = "campagne_parcelle",
            joinColumns = @JoinColumn(name = "id_campagne"),
            inverseJoinColumns = @JoinColumn(name = "id_parcelle"))
    private List<Parcelle> parcelles = new ArrayList<>();

    @OneToMany(mappedBy = "campagne", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Plantation> plantations;}
