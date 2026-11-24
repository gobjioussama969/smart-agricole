package com.example.smart_agri.RestController;

import com.example.smart_agri.Entity.Campagne;
import com.example.smart_agri.Service.CampagneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("api/campagne")
public class CampagneRestController {
    @Autowired
    CampagneService campagneService;

    // ===== AjouterCampagne =====
    @PostMapping("/save")
    public Campagne saveCampagne(@RequestBody Campagne campagne) {
        return campagneService.createCampagne(campagne);
    }

    // Ou bien
    @PostMapping("/save2")
    public ResponseEntity<Campagne> createCampagne(@RequestBody Campagne campagne) {
        try {
            Campagne cree = campagneService.createCampagne(campagne);
            return new ResponseEntity<Campagne>(cree, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<Campagne>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // ===== ListCampagne =====
    @GetMapping("/all")
    public List<Campagne> getallCampagne() {
        return campagneService.getAllCampagne();
    }

    // Ou bien
    @GetMapping("/all2")
    public ResponseEntity<List<Campagne>> getAllCampagnes() {
        try {
            List<Campagne> liste = campagneService.getAllCampagne();
            if (liste.isEmpty()) {
                return new ResponseEntity<List<Campagne>>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<List<Campagne>>(liste, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<List<Campagne>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // ===== GetCampagneById =====
    // Si l'id n'existe pas, le service lève NoSuchElementException -> 404 via GlobalExceptionHandler
    @GetMapping("/getone/{id}")
    public Campagne getoneCampagne(@PathVariable Long id) {
        return campagneService.getCampagneById(id);
    }

    // Ou bien
    @GetMapping("/campagnes/{id}")
    public ResponseEntity<Campagne> getCampagneById(@PathVariable("id") Long id) {
        Campagne campagne = trouver(id);
        if (campagne != null) {
            return new ResponseEntity<Campagne>(campagne, HttpStatus.OK);
        } else {
            return new ResponseEntity<Campagne>(HttpStatus.NOT_FOUND);
        }
    }

    // ===== UpdateCampagne =====
    @PutMapping("/update/{id}")
    public Campagne updateCampagne(@PathVariable Long id, @RequestBody Campagne campagne) {
        Campagne c1 = trouver(id);
        if (c1 != null) {
            campagne.setId(id);
            return campagneService.updateCampagne(campagne);
        } else {
            throw new RuntimeException("Failed!!! Campagne introuvable : " + id);
        }
    }

    // ===== DeleteCampagne =====
    @DeleteMapping("/campagnes/{id}")
    public ResponseEntity<HttpStatus> deleteCampagne(@PathVariable("id") Long id) {
        if (trouver(id) == null) {
            return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);
        }
        try {
            campagneService.deleteCampagne(id);
            return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<HttpStatus>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Ou bien
    @DeleteMapping("/delete/{id}")
    public HashMap<String, String> deleteCampagne2(@PathVariable Long id) {
        HashMap<String, String> message = new HashMap<>();
        if (trouver(id) == null) {
            message.put("etat", "campagne not found");
            return message;
        }
        try {
            campagneService.deleteCampagne(id);
            message.put("etat", "campagne deleted");
            return message;
        } catch (Exception e) {
            message.put("etat", "campagne not deleted");
            return message;
        }
    }

    private Campagne trouver(Long id) {
        try {
            return campagneService.getCampagneById(id);
        } catch (NoSuchElementException e) {
            return null;
        }
    }
}
