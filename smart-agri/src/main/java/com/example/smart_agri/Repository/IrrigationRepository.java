package com.example.smart_agri.Repository;

import com.example.smart_agri.Entity.Irrigation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IrrigationRepository extends JpaRepository<Irrigation, Long> {

    List<Irrigation> findByPlantationId(Long plantationId);

    List<Irrigation> findByAuteurId(Long auteurId);

    List<Irrigation> findByDateIrrigationBetween(LocalDate debut, LocalDate fin);
}
