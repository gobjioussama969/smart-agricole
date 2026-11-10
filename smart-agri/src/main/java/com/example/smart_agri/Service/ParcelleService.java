package com.example.smart_agri.Service;

import com.example.smart_agri.Entity.Parcelle;
import com.example.smart_agri.Repository.ParcelleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParcelleService {
    @Autowired
    private ParcelleRepository parcelleRepository;

    public Parcelle createParcelle(Parcelle parcelle) {
        return parcelleRepository.save(parcelle);
    }

    public List<Parcelle> getAllParcelle() {
        return parcelleRepository.findAll();
    }

    public Parcelle getParcelleById(Long id) {
        return parcelleRepository.findById(id).get();
    }

    public Parcelle updateParcelle(Parcelle parcelle) {
        return parcelleRepository.saveAndFlush(parcelle);
    }

    public void deleteParcelle(Long id) {
        parcelleRepository.deleteById(id);
    }
}

