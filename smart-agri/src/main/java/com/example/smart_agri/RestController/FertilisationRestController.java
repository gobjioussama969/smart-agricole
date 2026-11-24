package com.example.smart_agri.RestController;
import com.example.smart_agri.Entity.Fertilisation;
import com.example.smart_agri.Service.FertilisationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("api/fertilisation")
public class FertilisationRestController {
    @Autowired
    FertilisationService fertilisationService;

    // ===== AjouterFertilisation =====
    @PostMapping("/save")
    public Fertilisation saveFertilisation(@RequestBody Fertilisation fertilisation) {
        return fertilisationService.createFertilisation(fertilisation);
    }

    // Ou bien
    @PostMapping("/save2")
    public ResponseEntity<Fertilisation> createFertilisation(@RequestBody Fertilisation fertilisation) {
        try {
            Fertilisation cree = fertilisationService.createFertilisation(fertilisation);
            return new ResponseEntity<Fertilisation>(cree, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<Fertilisation>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // ===== ListFertilisation =====
    @GetMapping("/all")
    public List<Fertilisation> getallFertilisation() {
        return fertilisationService.getAllFertilisation();
    }

    // Ou bien
    @GetMapping("/all2")
    public ResponseEntity<List<Fertilisation>> getAllFertilisations() {
        try {
            List<Fertilisation> liste = fertilisationService.getAllFertilisation();
            if (liste.isEmpty()) {
                return new ResponseEntity<List<Fertilisation>>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<List<Fertilisation>>(liste, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<List<Fertilisation>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // ===== GetFertilisationById =====
    // Si l'id n'existe pas, le service lève NoSuchElementException -> 404 via GlobalExceptionHandler
    @GetMapping("/getone/{id}")
    public Fertilisation getoneFertilisation(@PathVariable Long id) {
        return fertilisationService.getFertilisationById(id);
    }

    // Ou bien
    @GetMapping("/fertilisations/{id}")
    public ResponseEntity<Fertilisation> getFertilisationById(@PathVariable("id") Long id) {
        Fertilisation fertilisation = trouver(id);
        if (fertilisation != null) {
            return new ResponseEntity<Fertilisation>(fertilisation, HttpStatus.OK);
        } else {
            return new ResponseEntity<Fertilisation>(HttpStatus.NOT_FOUND);
        }
    }

    // ===== UpdateFertilisation =====
    @PutMapping("/update/{id}")
    public Fertilisation updateFertilisation(@PathVariable Long id, @RequestBody Fertilisation fertilisation) {
        Fertilisation c1 = trouver(id);
        if (c1 != null) {
            fertilisation.setId(id);
            return fertilisationService.updateFertilisation(fertilisation);
        } else {
            throw new RuntimeException("Failed!!! Fertilisation introuvable : " + id);
        }
    }

    // ===== DeleteFertilisation =====
    @DeleteMapping("/fertilisations/{id}")
    public ResponseEntity<HttpStatus> deleteFertilisation(@PathVariable("id") Long id) {
        if (trouver(id) == null) {
            return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);
        }
        try {
            fertilisationService.deleteFertilisation(id);
            return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<HttpStatus>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Ou bien
    @DeleteMapping("/delete/{id}")
    public HashMap<String, String> deleteFertilisation2(@PathVariable Long id) {
        HashMap<String, String> message = new HashMap<>();
        if (trouver(id) == null) {
            message.put("etat", "fertilisation not found");
            return message;
        }
        try {
            fertilisationService.deleteFertilisation(id);
            message.put("etat", "fertilisation deleted");
            return message;
        } catch (Exception e) {
            message.put("etat", "fertilisation not deleted");
            return message;
        }
    }

    private Fertilisation trouver(Long id) {
        try {
            return fertilisationService.getFertilisationById(id);
        } catch (NoSuchElementException e) {
            return null;
        }
    }
}

