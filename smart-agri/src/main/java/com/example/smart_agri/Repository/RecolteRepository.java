package com.example.smart_agri.Repository;

import com.example.smart_agri.Entity.Recolte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RecolteRepository extends JpaRepository<Recolte, Long> {

    List<Recolte> findByPlantationId(Long plantationId);

    List<Recolte> findByAuteurId(Long auteurId);

    List<Recolte> findByDateRecolteBetween(LocalDate debut, LocalDate fin);
}
