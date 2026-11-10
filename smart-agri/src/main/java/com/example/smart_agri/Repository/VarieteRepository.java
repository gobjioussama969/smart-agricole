package com.example.smart_agri.Repository;

import com.example.smart_agri.Entity.Variete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VarieteRepository extends JpaRepository<Variete, Long> {

    List<Variete> findByNom(String nom);

    List<Variete> findByCultureId(Long cultureId);

    List<Variete> findVarieteByNomAndCultureId(String nom, Long cultureId);
}
