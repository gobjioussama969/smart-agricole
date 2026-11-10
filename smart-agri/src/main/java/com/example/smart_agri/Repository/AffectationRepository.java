package com.example.smart_agri.Repository;

import com.example.smart_agri.Entity.Affectation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AffectationRepository extends JpaRepository<Affectation, Long> {

    List<Affectation> findByParcelleId(Long parcelleId);

    List<Affectation> findByUtilisateurId(Long utilisateurId);

    boolean existsByUtilisateurIdAndParcelleId(Long utilisateurId, Long parcelleId);
}
