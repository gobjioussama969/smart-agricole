package com.example.smart_agri.Repository;

import com.example.smart_agri.Entity.Utilisateur;
import com.example.smart_agri.Enum.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {

    List<Utilisateur> findByNom(String nom);

    List<Utilisateur> findByRole(Role role);

    List<Utilisateur> findByActif(Boolean actif);

    Utilisateur findByEmail(String email);

    boolean existsByEmail(String email);
}
