package com.example.smart_agri.RestController;

import com.example.smart_agri.Entity.Observation;
import com.example.smart_agri.Service.ObservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("api/observation")
public class ObservationRestController {
    @Autowired
    ObservationService observationService;

    // ===== AjouterObservation =====
    @PostMapping("/save")
    public Observation saveObservation(@RequestBody Observation observation) {
        return observationService.createObservation(observation);
    }

    // Ou bien
    @PostMapping("/save2")
    public ResponseEntity<Observation> createObservation(@RequestBody Observation observation) {
        try {
            Observation cree = observationService.createObservation(observation);
            return new ResponseEntity<Observation>(cree, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<Observation>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // ===== ListObservation =====
    @GetMapping("/all")
    public List<Observation> getallObservation() {
        return observationService.getAllObservation();
    }

    // Ou bien
    @GetMapping("/all2")
    public ResponseEntity<List<Observation>> getAllObservations() {
        try {
            List<Observation> liste = observationService.getAllObservation();
            if (liste.isEmpty()) {
                return new ResponseEntity<List<Observation>>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<List<Observation>>(liste, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<List<Observation>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // ===== GetObservationById =====
    // Si l'id n'existe pas, le service lève NoSuchElementException -> 404 via GlobalExceptionHandler
    @GetMapping("/getone/{id}")
    public Observation getoneObservation(@PathVariable Long id) {
        return observationService.getObservationById(id);
    }

    // Ou bien
    @GetMapping("/observations/{id}")
    public ResponseEntity<Observation> getObservationById(@PathVariable("id") Long id) {
        Observation observation = trouver(id);
        if (observation != null) {
            return new ResponseEntity<Observation>(observation, HttpStatus.OK);
        } else {
            return new ResponseEntity<Observation>(HttpStatus.NOT_FOUND);
        }
    }

    // ===== UpdateObservation =====
    @PutMapping("/update/{id}")
    public Observation updateObservation(@PathVariable Long id, @RequestBody Observation observation) {
        Observation c1 = trouver(id);
        if (c1 != null) {
            observation.setId(id);
            return observationService.updateObservation(observation);
        } else {
            throw new RuntimeException("Failed!!! Observation introuvable : " + id);
        }
    }

    // ===== DeleteObservation =====
    @DeleteMapping("/observations/{id}")
    public ResponseEntity<HttpStatus> deleteObservation(@PathVariable("id") Long id) {
        if (trouver(id) == null) {
            return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);
        }
        try {
            observationService.deleteObservation(id);
            return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<HttpStatus>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Ou bien
    @DeleteMapping("/delete/{id}")
    public HashMap<String, String> deleteObservation2(@PathVariable Long id) {
        HashMap<String, String> message = new HashMap<>();
        if (trouver(id) == null) {
            message.put("etat", "observation not found");
            return message;
        }
        try {
            observationService.deleteObservation(id);
            message.put("etat", "observation deleted");
            return message;
        } catch (Exception e) {
            message.put("etat", "observation not deleted");
            return message;
        }
    }

    private Observation trouver(Long id) {
        try {
            return observationService.getObservationById(id);
        } catch (NoSuchElementException e) {
            return null;
        }
    }
}

