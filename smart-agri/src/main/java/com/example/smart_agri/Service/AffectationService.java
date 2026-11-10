package com.example.smart_agri.Service;

import com.example.smart_agri.Entity.Affectation;
import com.example.smart_agri.Repository.AffectationRepository;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AffectationService {
    @Autowired
    private AffectationRepository affectationRepository;

    public Affectation createAffectation(Affectation affectation) {
        return affectationRepository.save(affectation);
    }

    public List<Affectation> getAllAffectation() {
        return affectationRepository.findAll();
    }

    public Affectation getAffectationById(Long id) {
        return affectationRepository.findById(id).get();
    }

    public Affectation updateAffectation(Affectation affectation) {
        return affectationRepository.saveAndFlush(affectation);
    }

    public void deleteAffectation(Long id) {
        affectationRepository.deleteById(id);
    }
}
