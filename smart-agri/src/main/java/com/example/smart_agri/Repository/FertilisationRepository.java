package com.example.smart_agri.Repository;

import com.example.smart_agri.Entity.Fertilisation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FertilisationRepository extends JpaRepository<Fertilisation, Long> {

    List<Fertilisation> findByPlantationId(Long plantationId);

    List<Fertilisation> findByAuteurId(Long auteurId);

    List<Fertilisation> findByDateFertilisationBetween(LocalDate debut, LocalDate fin);
}
