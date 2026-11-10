package com.example.smart_agri.Service;

import com.example.smart_agri.Entity.Utilisateur;
import com.example.smart_agri.Repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtilisateurService {
    @Autowired
    private UtilisateurRepository utilisateurRepository;

    public Utilisateur createUtilisateur(Utilisateur utilisateur) {
        return utilisateurRepository.save(utilisateur);
    }

    public List<Utilisateur> getAllUtilisateur() {
        return utilisateurRepository.findAll();
    }

    public Utilisateur getUtilisateurById(Long id) {
        return utilisateurRepository.findById(id).get();
    }

    public Utilisateur updateUtilisateur(Utilisateur utilisateur) {
        return utilisateurRepository.saveAndFlush(utilisateur);
    }

    public void deleteUtilisateur(Long id) {
        utilisateurRepository.deleteById(id);
    }
}
