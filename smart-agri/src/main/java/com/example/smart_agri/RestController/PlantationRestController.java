package com.example.smart_agri.RestController;

import com.example.smart_agri.Entity.Plantation;
import com.example.smart_agri.Service.PlantationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("api/plantation")
public class PlantationRestController {
    @Autowired
    PlantationService plantationService;

    // ===== AjouterPlantation =====
    @PostMapping("/save")
    public Plantation savePlantation(@RequestBody Plantation plantation) {
        return plantationService.createPlantation(plantation);
    }

    // Ou bien
    @PostMapping("/save2")
    public ResponseEntity<Plantation> createPlantation(@RequestBody Plantation plantation) {
        try {
            Plantation cree = plantationService.createPlantation(plantation);
            return new ResponseEntity<Plantation>(cree, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<Plantation>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // ===== ListPlantation =====
    @GetMapping("/all")
    public List<Plantation> getallPlantation() {
        return plantationService.getAllPlantation();
    }

    // Ou bien
    @GetMapping("/all2")
    public ResponseEntity<List<Plantation>> getAllPlantations() {
        try {
            List<Plantation> liste = plantationService.getAllPlantation();
            if (liste.isEmpty()) {
                return new ResponseEntity<List<Plantation>>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<List<Plantation>>(liste, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<List<Plantation>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // ===== GetPlantationById =====
    // Si l'id n'existe pas, le service lève NoSuchElementException -> 404 via GlobalExceptionHandler
    @GetMapping("/getone/{id}")
    public Plantation getonePlantation(@PathVariable Long id) {
        return plantationService.getPlantationById(id);
    }

    // Ou bien
    @GetMapping("/plantations/{id}")
    public ResponseEntity<Plantation> getPlantationById(@PathVariable("id") Long id) {
        Plantation plantation = trouver(id);
        if (plantation != null) {
            return new ResponseEntity<Plantation>(plantation, HttpStatus.OK);
        } else {
            return new ResponseEntity<Plantation>(HttpStatus.NOT_FOUND);
        }
    }

    // ===== UpdatePlantation =====
    @PutMapping("/update/{id}")
    public Plantation updatePlantation(@PathVariable Long id, @RequestBody Plantation plantation) {
        Plantation c1 = trouver(id);
        if (c1 != null) {
            plantation.setId(id);
            return plantationService.updatePlantation(plantation);
        } else {
            throw new RuntimeException("Failed!!! Plantation introuvable : " + id);
        }
    }

    // ===== DeletePlantation =====
    @DeleteMapping("/plantations/{id}")
    public ResponseEntity<HttpStatus> deletePlantation(@PathVariable("id") Long id) {
        if (trouver(id) == null) {
            return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);
        }
        try {
            plantationService.deletePlantation(id);
            return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<HttpStatus>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Ou bien
    @DeleteMapping("/delete/{id}")
    public HashMap<String, String> deletePlantation2(@PathVariable Long id) {
        HashMap<String, String> message = new HashMap<>();
        if (trouver(id) == null) {
            message.put("etat", "plantation not found");
            return message;
        }
        try {
            plantationService.deletePlantation(id);
            message.put("etat", "plantation deleted");
            return message;
        } catch (Exception e) {
            message.put("etat", "plantation not deleted");
            return message;
        }
    }
    private Plantation trouver(Long id) {
        try {
            return plantationService.getPlantationById(id);
        } catch (NoSuchElementException e) {
            return null;
        }
    }
}

