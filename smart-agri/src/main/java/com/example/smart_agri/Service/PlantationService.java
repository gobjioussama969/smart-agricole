package com.example.smart_agri.Service;

import com.example.smart_agri.Entity.Plantation;
import com.example.smart_agri.Repository.PlantationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlantationService {
    @Autowired
    private PlantationRepository plantationRepository;

    public Plantation createPlantation(Plantation plantation) {
        return plantationRepository.save(plantation);
    }

    public List<Plantation> getAllPlantation() {
        return plantationRepository.findAll();
    }

    public Plantation getPlantationById(Long id) {
        return plantationRepository.findById(id).get();
    }

    public Plantation updatePlantation(Plantation plantation) {
        return plantationRepository.saveAndFlush(plantation);
    }

    public void deletePlantation(Long id) {
        plantationRepository.deleteById(id);
    }
}

