package com.example.smart_agri.Service;

import com.example.smart_agri.Entity.Variete;
import com.example.smart_agri.Repository.VarieteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VarieteService {
    @Autowired
    private VarieteRepository varieteRepository;

    public Variete createVariete(Variete variete) {
        return varieteRepository.save(variete);
    }

    public List<Variete> getAllVariete() {
        return varieteRepository.findAll();
    }

    public Variete getVarieteById(Long id) {
        return varieteRepository.findById(id).get();
    }

    public Variete updateVariete(Variete variete) {
        return varieteRepository.saveAndFlush(variete);
    }

    public void deleteVariete(Long id) {
        varieteRepository.deleteById(id);
    }
}

