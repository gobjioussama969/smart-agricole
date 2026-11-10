package com.example.smart_agri.Repository;

import com.example.smart_agri.Entity.Parcelle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParcelleRepository extends JpaRepository<Parcelle, Long> {

    List<Parcelle> findByNom(String nom);

    List<Parcelle> findByExploitationId(Long exploitationId);

    List<Parcelle> findByTypeSolAndTypeIrrigation(String typeSol, String typeIrrigation);

    Parcelle findByCode(String code);
}
