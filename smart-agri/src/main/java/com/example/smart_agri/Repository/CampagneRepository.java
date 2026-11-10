package com.example.smart_agri.Repository;

import com.example.smart_agri.Entity.Campagne;
import com.example.smart_agri.Enum.StatutCampagne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CampagneRepository extends JpaRepository<Campagne, Long> {

    List<Campagne> findByNom(String nom);

    List<Campagne> findByStatut(StatutCampagne statut);

    List<Campagne> findCampagneByNomAndStatut(String nom, StatutCampagne statut);

    boolean existsByNom(String nom);
}
