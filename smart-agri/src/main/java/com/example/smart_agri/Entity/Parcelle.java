package com.example.smart_agri.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Parcelle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private String nom;
    private Double superficie;
    private String typeSol;
    private String typeIrrigation;
    private String description;

    @ManyToOne
    @JoinColumn(name = "id_exploitation")
    private Exploitation exploitation;

    @OneToMany(mappedBy = "parcelle", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Plantation> plantations;

    @OneToMany(mappedBy = "parcelle", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Affectation> affectations;
    @ManyToMany(mappedBy = "parcelles")
    @JsonIgnore
    private List<Campagne> campagnes;
    @PreRemove
    private void retirerDesCampagnes() {
        if (campagnes != null) {
            for (Campagne campagne : campagnes) {
                campagne.getParcelles().remove(this);
            }
        }
    }}