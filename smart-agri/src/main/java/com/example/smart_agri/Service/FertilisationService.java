package com.example.smart_agri.Service;

import com.example.smart_agri.Entity.Fertilisation;
import com.example.smart_agri.Repository.FertilisationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FertilisationService {
    @Autowired
    private FertilisationRepository fertilisationRepository;

    public Fertilisation createFertilisation(Fertilisation fertilisation) {
        return fertilisationRepository.save(fertilisation);
    }

    public List<Fertilisation> getAllFertilisation() {
        return fertilisationRepository.findAll();
    }

    public Fertilisation getFertilisationById(Long id) {
        return fertilisationRepository.findById(id).get();
    }

    public Fertilisation updateFertilisation(Fertilisation fertilisation) {
        return fertilisationRepository.saveAndFlush(fertilisation);
    }

    public void deleteFertilisation(Long id) {
        fertilisationRepository.deleteById(id);
    }
}
