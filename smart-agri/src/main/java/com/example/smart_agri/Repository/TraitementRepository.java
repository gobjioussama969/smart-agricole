package com.example.smart_agri.Repository;

import com.example.smart_agri.Entity.Traitement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TraitementRepository extends JpaRepository<Traitement, Long> {

    List<Traitement> findByPlantationId(Long plantationId);

    List<Traitement> findByAuteurId(Long auteurId);

    List<Traitement> findByDateTraitementBetween(LocalDate debut, LocalDate fin);
}
