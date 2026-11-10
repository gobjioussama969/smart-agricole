package com.example.smart_agri.Repository;

import com.example.smart_agri.Entity.Culture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CultureRepository extends JpaRepository<Culture, Long> {

    List<Culture> findByNom(String nom);

    List<Culture> findByType(String type);

    boolean existsByNom(String nom);
}