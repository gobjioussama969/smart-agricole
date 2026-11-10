package com.example.smart_agri.Entity;


import com.example.smart_agri.Enum.Role;
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
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String prenom;
    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;

    private Boolean actif = true;
    @OneToMany(mappedBy = "utilisateur", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Affectation> affectations;
}

