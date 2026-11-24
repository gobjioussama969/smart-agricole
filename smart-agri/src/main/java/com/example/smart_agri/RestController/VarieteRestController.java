package com.example.smart_agri.RestController;

import com.example.smart_agri.Entity.Variete;
import com.example.smart_agri.Service.VarieteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("api/variete")
public class VarieteRestController {
    @Autowired
    VarieteService varieteService;

    // ===== AjouterVariete =====
    @PostMapping("/save")
    public Variete saveVariete(@RequestBody Variete variete) {
        return varieteService.createVariete(variete);
    }

    // Ou bien
    @PostMapping("/save2")
    public ResponseEntity<Variete> createVariete(@RequestBody Variete variete) {
        try {
            Variete cree = varieteService.createVariete(variete);
            return new ResponseEntity<Variete>(cree, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<Variete>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // ===== ListVariete =====
    @GetMapping("/all")
    public List<Variete> getallVariete() {
        return varieteService.getAllVariete();
    }

    // Ou bien
    @GetMapping("/all2")
    public ResponseEntity<List<Variete>> getAllVarietes() {
        try {
            List<Variete> liste = varieteService.getAllVariete();
            if (liste.isEmpty()) {
                return new ResponseEntity<List<Variete>>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<List<Variete>>(liste, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<List<Variete>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // ===== GetVarieteById =====
    // Si l'id n'existe pas, le service lève NoSuchElementException -> 404 via GlobalExceptionHandler
    @GetMapping("/getone/{id}")
    public Variete getoneVariete(@PathVariable Long id) {
        return varieteService.getVarieteById(id);
    }

    // Ou bien
    @GetMapping("/varietes/{id}")
    public ResponseEntity<Variete> getVarieteById(@PathVariable("id") Long id) {
        Variete variete = trouver(id);
        if (variete != null) {
            return new ResponseEntity<Variete>(variete, HttpStatus.OK);
        } else {
            return new ResponseEntity<Variete>(HttpStatus.NOT_FOUND);
        }
    }

    // ===== UpdateVariete =====
    @PutMapping("/update/{id}")
    public Variete updateVariete(@PathVariable Long id, @RequestBody Variete variete) {
        Variete c1 = trouver(id);
        if (c1 != null) {
            variete.setId(id);
            return varieteService.updateVariete(variete);
        } else {
            throw new RuntimeException("Failed!!! Variete introuvable : " + id);
        }
    }

    // ===== DeleteVariete =====
    @DeleteMapping("/varietes/{id}")
    public ResponseEntity<HttpStatus> deleteVariete(@PathVariable("id") Long id) {
        if (trouver(id) == null) {
            return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);
        }
        try {
            varieteService.deleteVariete(id);
            return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<HttpStatus>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Ou bien
    @DeleteMapping("/delete/{id}")
    public HashMap<String, String> deleteVariete2(@PathVariable Long id) {
        HashMap<String, String> message = new HashMap<>();
        if (trouver(id) == null) {
            message.put("etat", "variete not found");
            return message;
        }
        try {
            varieteService.deleteVariete(id);
            message.put("etat", "variete deleted");
            return message;
        } catch (Exception e) {
            message.put("etat", "variete not deleted");
            return message;
        }
    }

    private Variete trouver(Long id) {
        try {
            return varieteService.getVarieteById(id);
        } catch (NoSuchElementException e) {
            return null;
        }
    }
}

