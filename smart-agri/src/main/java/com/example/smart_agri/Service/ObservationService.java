package com.example.smart_agri.Service;

import com.example.smart_agri.Entity.Observation;
import com.example.smart_agri.Repository.ObservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ObservationService {
    @Autowired
    private ObservationRepository observationRepository;

    public Observation createObservation(Observation observation) {
        return observationRepository.save(observation);
    }

    public List<Observation> getAllObservation() {
        return observationRepository.findAll();
    }

    public Observation getObservationById(Long id) {
        return observationRepository.findById(id).get();
    }

    public Observation updateObservation(Observation observation) {
        return observationRepository.saveAndFlush(observation);
    }

    public void deleteObservation(Long id) {
        observationRepository.deleteById(id);
    }
}
