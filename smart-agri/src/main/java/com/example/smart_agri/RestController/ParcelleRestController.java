package com.example.smart_agri.RestController;

import com.example.smart_agri.Entity.Parcelle;
import com.example.smart_agri.Service.ParcelleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("api/parcelle")
public class ParcelleRestController {
    @Autowired
    ParcelleService parcelleService;

    // ===== AjouterParcelle =====
    @PostMapping("/save")
    public Parcelle saveParcelle(@RequestBody Parcelle parcelle) {
        return parcelleService.createParcelle(parcelle);
    }

    // Ou bien
    @PostMapping("/save2")
    public ResponseEntity<Parcelle> createParcelle(@RequestBody Parcelle parcelle) {
        try {
            Parcelle cree = parcelleService.createParcelle(parcelle);
            return new ResponseEntity<Parcelle>(cree, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<Parcelle>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // ===== ListParcelle =====
    @GetMapping("/all")
    public List<Parcelle> getallParcelle() {
        return parcelleService.getAllParcelle();
    }

    // Ou bien
    @GetMapping("/all2")
    public ResponseEntity<List<Parcelle>> getAllParcelles() {
        try {
            List<Parcelle> liste = parcelleService.getAllParcelle();
            if (liste.isEmpty()) {
                return new ResponseEntity<List<Parcelle>>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<List<Parcelle>>(liste, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<List<Parcelle>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // ===== GetParcelleById =====
    // Si l'id n'existe pas, le service lève NoSuchElementException -> 404 via GlobalExceptionHandler
    @GetMapping("/getone/{id}")
    public Parcelle getoneParcelle(@PathVariable Long id) {
        return parcelleService.getParcelleById(id);
    }

    // Ou bien
    @GetMapping("/parcelles/{id}")
    public ResponseEntity<Parcelle> getParcelleById(@PathVariable("id") Long id) {
        Parcelle parcelle = trouver(id);
        if (parcelle != null) {
            return new ResponseEntity<Parcelle>(parcelle, HttpStatus.OK);
        } else {
            return new ResponseEntity<Parcelle>(HttpStatus.NOT_FOUND);
        }
    }

    // ===== UpdateParcelle =====
    @PutMapping("/update/{id}")
    public Parcelle updateParcelle(@PathVariable Long id, @RequestBody Parcelle parcelle) {
        Parcelle c1 = trouver(id);
        if (c1 != null) {
            parcelle.setId(id);
            return parcelleService.updateParcelle(parcelle);
        } else {
            throw new RuntimeException("Failed!!! Parcelle introuvable : " + id);
        }
    }

    // ===== DeleteParcelle =====
    @DeleteMapping("/parcelles/{id}")
    public ResponseEntity<HttpStatus> deleteParcelle(@PathVariable("id") Long id) {
        if (trouver(id) == null) {
            return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);
        }
        try {
            parcelleService.deleteParcelle(id);
            return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<HttpStatus>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Ou bien
    @DeleteMapping("/delete/{id}")
    public HashMap<String, String> deleteParcelle2(@PathVariable Long id) {
        HashMap<String, String> message = new HashMap<>();
        if (trouver(id) == null) {
            message.put("etat", "parcelle not found");
            return message;
        }
        try {
            parcelleService.deleteParcelle(id);
            message.put("etat", "parcelle deleted");
            return message;
        } catch (Exception e) {
            message.put("etat", "parcelle not deleted");
            return message;
        }
    }

    // Le service fait findById(id).get() : on transforme l'exception en null
    // pour pouvoir écrire les tests "!= null" comme dans le TP5.
    private Parcelle trouver(Long id) {
        try {
            return parcelleService.getParcelleById(id);
        } catch (NoSuchElementException e) {
            return null;
        }
    }
}

