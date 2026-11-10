package com.example.smart_agri.Service;

import com.example.smart_agri.Entity.Campagne;
import com.example.smart_agri.Entity.Parcelle;
import com.example.smart_agri.Enum.StatutCampagne;
import com.example.smart_agri.Repository.CampagneRepository;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CampagneService {
    @Autowired
    private CampagneRepository campagneRepository;

    public Campagne createCampagne(Campagne campagne) {
        return campagneRepository.save(campagne);
    }

    public List<Campagne> getAllCampagne() {
        return campagneRepository.findAll();
    }

    public Campagne getCampagneById(Long id) {
        return campagneRepository.findById(id).get();
    }

    public Campagne updateCampagne(Campagne campagne) {
        return campagneRepository.saveAndFlush(campagne);
    }

    public void deleteCampagne(Long id) {
        campagneRepository.deleteById(id);
    }
}