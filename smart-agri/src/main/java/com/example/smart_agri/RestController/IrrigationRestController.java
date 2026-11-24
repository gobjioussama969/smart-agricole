package com.example.smart_agri.RestController;

import com.example.smart_agri.Entity.Irrigation;
import com.example.smart_agri.Service.IrrigationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("api/irrigation")
public class IrrigationRestController {
    @Autowired
    IrrigationService irrigationService;

    // ===== AjouterIrrigation =====
    @PostMapping("/save")
    public Irrigation saveIrrigation(@RequestBody Irrigation irrigation) {
        return irrigationService.createIrrigation(irrigation);
    }

    // Ou bien
    @PostMapping("/save2")
    public ResponseEntity<Irrigation> createIrrigation(@RequestBody Irrigation irrigation) {
        try {
            Irrigation cree = irrigationService.createIrrigation(irrigation);
            return new ResponseEntity<Irrigation>(cree, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<Irrigation>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // ===== ListIrrigation =====
    @GetMapping("/all")
    public List<Irrigation> getallIrrigation() {
        return irrigationService.getAllIrrigation();
    }

    // Ou bien
    @GetMapping("/all2")
    public ResponseEntity<List<Irrigation>> getAllIrrigations() {
        try {
            List<Irrigation> liste = irrigationService.getAllIrrigation();
            if (liste.isEmpty()) {
                return new ResponseEntity<List<Irrigation>>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<List<Irrigation>>(liste, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<List<Irrigation>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // ===== GetIrrigationById =====
    // Si l'id n'existe pas, le service lève NoSuchElementException -> 404 via GlobalExceptionHandler
    @GetMapping("/getone/{id}")
    public Irrigation getoneIrrigation(@PathVariable Long id) {
        return irrigationService.getIrrigationById(id);
    }

    // Ou bien
    @GetMapping("/irrigations/{id}")
    public ResponseEntity<Irrigation> getIrrigationById(@PathVariable("id") Long id) {
        Irrigation irrigation = trouver(id);
        if (irrigation != null) {
            return new ResponseEntity<Irrigation>(irrigation, HttpStatus.OK);
        } else {
            return new ResponseEntity<Irrigation>(HttpStatus.NOT_FOUND);
        }
    }

    // ===== UpdateIrrigation =====
    @PutMapping("/update/{id}")
    public Irrigation updateIrrigation(@PathVariable Long id, @RequestBody Irrigation irrigation) {
        Irrigation c1 = trouver(id);
        if (c1 != null) {
            irrigation.setId(id);
            return irrigationService.updateIrrigation(irrigation);
        } else {
            throw new RuntimeException("Failed!!! Irrigation introuvable : " + id);
        }
    }

    // ===== DeleteIrrigation =====
    @DeleteMapping("/irrigations/{id}")
    public ResponseEntity<HttpStatus> deleteIrrigation(@PathVariable("id") Long id) {
        if (trouver(id) == null) {
            return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);
        }
        try {
            irrigationService.deleteIrrigation(id);
            return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<HttpStatus>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Ou bien
    @DeleteMapping("/delete/{id}")
    public HashMap<String, String> deleteIrrigation2(@PathVariable Long id) {
        HashMap<String, String> message = new HashMap<>();
        if (trouver(id) == null) {
            message.put("etat", "irrigation not found");
            return message;
        }
        try {
            irrigationService.deleteIrrigation(id);
            message.put("etat", "irrigation deleted");
            return message;
        } catch (Exception e) {
            message.put("etat", "irrigation not deleted");
            return message;
        }
    }

    private Irrigation trouver(Long id) {
        try {
            return irrigationService.getIrrigationById(id);
        } catch (NoSuchElementException e) {
            return null;
        }
    }
}

