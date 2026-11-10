package com.example.smart_agri.Service;

import com.example.smart_agri.Entity.Irrigation;
import com.example.smart_agri.Repository.IrrigationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IrrigationService {
    @Autowired
    private IrrigationRepository irrigationRepository;

    public Irrigation createIrrigation(Irrigation irrigation) {
        return irrigationRepository.save(irrigation);
    }

    public List<Irrigation> getAllIrrigation() {
        return irrigationRepository.findAll();
    }

    public Irrigation getIrrigationById(Long id) {
        return irrigationRepository.findById(id).get();
    }

    public Irrigation updateIrrigation(Irrigation irrigation) {
        return irrigationRepository.saveAndFlush(irrigation);
    }

    public void deleteIrrigation(Long id) {
        irrigationRepository.deleteById(id);
    }
}
