package com.example.smart_agri.Service;

import com.example.smart_agri.Entity.Recolte;
import com.example.smart_agri.Repository.RecolteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecolteService {
    @Autowired
    private RecolteRepository recolteRepository;

    public Recolte createRecolte(Recolte recolte) {
        return recolteRepository.save(recolte);
    }

    public List<Recolte> getAllRecolte() {
        return recolteRepository.findAll();
    }

    public Recolte getRecolteById(Long id) {
        return recolteRepository.findById(id).get();
    }

    public Recolte updateRecolte(Recolte recolte) {
        return recolteRepository.saveAndFlush(recolte);
    }

    public void deleteRecolte(Long id) {
        recolteRepository.deleteById(id);
    }
}
