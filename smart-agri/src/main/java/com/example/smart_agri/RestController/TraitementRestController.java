package com.example.smart_agri.RestController;
import com.example.smart_agri.Entity.Traitement;
import com.example.smart_agri.Service.TraitementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("api/traitement")
public class TraitementRestController {
    @Autowired
    TraitementService traitementService;

    // ===== AjouterTraitement =====
    @PostMapping("/save")
    public Traitement saveTraitement(@RequestBody Traitement traitement) {
        return traitementService.createTraitement(traitement);
    }

    // Ou bien
    @PostMapping("/save2")
    public ResponseEntity<Traitement> createTraitement(@RequestBody Traitement traitement) {
        try {
            Traitement cree = traitementService.createTraitement(traitement);
            return new ResponseEntity<Traitement>(cree, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<Traitement>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // ===== ListTraitement =====
    @GetMapping("/all")
    public List<Traitement> getallTraitement() {
        return traitementService.getAllTraitement();
    }

    // Ou bien
    @GetMapping("/all2")
    public ResponseEntity<List<Traitement>> getAllTraitements() {
        try {
            List<Traitement> liste = traitementService.getAllTraitement();
            if (liste.isEmpty()) {
                return new ResponseEntity<List<Traitement>>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<List<Traitement>>(liste, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<List<Traitement>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // ===== GetTraitementById =====
    // Si l'id n'existe pas, le service lève NoSuchElementException -> 404 via GlobalExceptionHandler
    @GetMapping("/getone/{id}")
    public Traitement getoneTraitement(@PathVariable Long id) {
        return traitementService.getTraitementById(id);
    }

    // Ou bien
    @GetMapping("/traitements/{id}")
    public ResponseEntity<Traitement> getTraitementById(@PathVariable("id") Long id) {
        Traitement traitement = trouver(id);
        if (traitement != null) {
            return new ResponseEntity<Traitement>(traitement, HttpStatus.OK);
        } else {
            return new ResponseEntity<Traitement>(HttpStatus.NOT_FOUND);
        }
    }

    // ===== UpdateTraitement =====
    @PutMapping("/update/{id}")
    public Traitement updateTraitement(@PathVariable Long id, @RequestBody Traitement traitement) {
        Traitement c1 = trouver(id);
        if (c1 != null) {
            traitement.setId(id);
            return traitementService.updateTraitement(traitement);
        } else {
            throw new RuntimeException("Failed!!! Traitement introuvable : " + id);
        }
    }

    // ===== DeleteTraitement =====
    @DeleteMapping("/traitements/{id}")
    public ResponseEntity<HttpStatus> deleteTraitement(@PathVariable("id") Long id) {
        if (trouver(id) == null) {
            return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);
        }
        try {
            traitementService.deleteTraitement(id);
            return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<HttpStatus>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Ou bien
    @DeleteMapping("/delete/{id}")
    public HashMap<String, String> deleteTraitement2(@PathVariable Long id) {
        HashMap<String, String> message = new HashMap<>();
        if (trouver(id) == null) {
            message.put("etat", "traitement not found");
            return message;
        }
        try {
            traitementService.deleteTraitement(id);
            message.put("etat", "traitement deleted");
            return message;
        } catch (Exception e) {
            message.put("etat", "traitement not deleted");
            return message;
        }
    }

    // Le service fait findById(id).get() : on transforme l'exception en null
    // pour pouvoir écrire les tests "!= null" comme dans le TP5.
    private Traitement trouver(Long id) {
        try {
            return traitementService.getTraitementById(id);
        } catch (NoSuchElementException e) {
            return null;
        }
    }
}

