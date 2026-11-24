package com.example.smart_agri.RestController;


import com.example.smart_agri.Entity.Affectation;
import com.example.smart_agri.Service.AffectationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("api/affectation")
public class AffectationRestController {
    @Autowired
    AffectationService affectationService;

    // ===== AjouterAffectation =====
    @PostMapping("/save")
    public Affectation saveAffectation(@RequestBody Affectation affectation) {
        return affectationService.createAffectation(affectation);
    }

    // Ou bien
    @PostMapping("/save2")
    public ResponseEntity<Affectation> createAffectation(@RequestBody Affectation affectation) {
        try {
            Affectation cree = affectationService.createAffectation(affectation);
            return new ResponseEntity<Affectation>(cree, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<Affectation>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // ===== ListAffectation =====
    @GetMapping("/all")
    public List<Affectation> getallAffectation() {
        return affectationService.getAllAffectation();
    }

    // Ou bien
    @GetMapping("/all2")
    public ResponseEntity<List<Affectation>> getAllAffectations() {
        try {
            List<Affectation> liste = affectationService.getAllAffectation();
            if (liste.isEmpty()) {
                return new ResponseEntity<List<Affectation>>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<List<Affectation>>(liste, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<List<Affectation>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // ===== GetAffectationById =====
    // Si l'id n'existe pas, le service lève NoSuchElementException -> 404 via GlobalExceptionHandler
    @GetMapping("/getone/{id}")
    public Affectation getoneAffectation(@PathVariable Long id) {
        return affectationService.getAffectationById(id);
    }

    // Ou bien
    @GetMapping("/affectations/{id}")
    public ResponseEntity<Affectation> getAffectationById(@PathVariable("id") Long id) {
        Affectation affectation = trouver(id);
        if (affectation != null) {
            return new ResponseEntity<Affectation>(affectation, HttpStatus.OK);
        } else {
            return new ResponseEntity<Affectation>(HttpStatus.NOT_FOUND);
        }
    }

    // ===== UpdateAffectation =====
    @PutMapping("/update/{id}")
    public Affectation updateAffectation(@PathVariable Long id, @RequestBody Affectation affectation) {
        Affectation c1 = trouver(id);
        if (c1 != null) {
            affectation.setId(id);
            return affectationService.updateAffectation(affectation);
        } else {
            throw new RuntimeException("Failed!!! Affectation introuvable : " + id);
        }
    }

    // ===== DeleteAffectation =====
    @DeleteMapping("/affectations/{id}")
    public ResponseEntity<HttpStatus> deleteAffectation(@PathVariable("id") Long id) {
        if (trouver(id) == null) {
            return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);
        }
        try {
            affectationService.deleteAffectation(id);
            return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<HttpStatus>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Ou bien
    @DeleteMapping("/delete/{id}")
    public HashMap<String, String> deleteAffectation2(@PathVariable Long id) {
        HashMap<String, String> message = new HashMap<>();
        if (trouver(id) == null) {
            message.put("etat", "affectation not found");
            return message;
        }
        try {
            affectationService.deleteAffectation(id);
            message.put("etat", "affectation deleted");
            return message;
        } catch (Exception e) {
            message.put("etat", "affectation not deleted");
            return message;
        }
    }

    private Affectation trouver(Long id) {
        try {
            return affectationService.getAffectationById(id);
        } catch (NoSuchElementException e) {
            return null;
        }
    }
}

