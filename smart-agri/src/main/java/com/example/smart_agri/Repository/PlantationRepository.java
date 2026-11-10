package com.example.smart_agri.Repository;

import com.example.smart_agri.Entity.Plantation;
import com.example.smart_agri.Enum.StatutPlantation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlantationRepository extends JpaRepository<Plantation, Long> {

    List<Plantation> findByParcelleId(Long parcelleId);

    List<Plantation> findByCampagneId(Long campagneId);

    List<Plantation> findByVarieteId(Long varieteId);

    List<Plantation> findByStatut(StatutPlantation statut);

    List<Plantation> findPlantationByParcelleIdAndCampagneId(Long parcelleId, Long campagneId);
}
