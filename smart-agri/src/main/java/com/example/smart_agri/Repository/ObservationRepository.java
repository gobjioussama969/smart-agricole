package com.example.smart_agri.Repository;

import com.example.smart_agri.Entity.Observation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ObservationRepository extends JpaRepository<Observation, Long> {

    List<Observation> findByPlantationId(Long plantationId);

    List<Observation> findByAuteurId(Long auteurId);

    List<Observation> findByDateObservationBetween(LocalDate debut, LocalDate fin);
}
