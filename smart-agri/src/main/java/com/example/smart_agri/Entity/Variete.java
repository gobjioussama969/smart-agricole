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
public class Variete {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String description;

    @ManyToOne
    @JoinColumn(name = "id_culture")
    private Culture culture;

    @OneToMany(mappedBy = "variete", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Plantation> plantations;
}
