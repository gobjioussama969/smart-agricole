package com.example.smart_agri.Service;

import com.example.smart_agri.Entity.Traitement;
import com.example.smart_agri.Repository.TraitementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TraitementService {
    @Autowired
    private TraitementRepository traitementRepository;

    public Traitement createTraitement(Traitement traitement) {
        return traitementRepository.save(traitement);
    }

    public List<Traitement> getAllTraitement() {
        return traitementRepository.findAll();
    }

    public Traitement getTraitementById(Long id) {
        return traitementRepository.findById(id).get();
    }

    public Traitement updateTraitement(Traitement traitement) {
        return traitementRepository.saveAndFlush(traitement);
    }

    public void deleteTraitement(Long id) {
        traitementRepository.deleteById(id);
    }
}

