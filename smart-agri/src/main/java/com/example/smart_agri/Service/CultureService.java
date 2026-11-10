package com.example.smart_agri.Service;

import com.example.smart_agri.Entity.Culture;
import com.example.smart_agri.Repository.CultureRepository;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CultureService {
    @Autowired
    private CultureRepository cultureRepository;

    public Culture createCulture(Culture culture) {
        return cultureRepository.save(culture);
    }

    public List<Culture> getAllCulture() {
        return cultureRepository.findAll();
    }

    public Culture getCultureById(Long id) {
        return cultureRepository.findById(id).get();
    }

    public Culture updateCulture(Culture culture) {
        return cultureRepository.saveAndFlush(culture);
    }

    public void deleteCulture(Long id) {
        cultureRepository.deleteById(id);
    }
}
