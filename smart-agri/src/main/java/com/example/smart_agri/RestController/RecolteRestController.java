package com.example.smart_agri.RestController;

import com.example.smart_agri.Entity.Recolte;
import com.example.smart_agri.Service.RecolteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("api/recolte")
public class RecolteRestController {
    @Autowired
    RecolteService recolteService;

    // ===== AjouterRecolte =====
    @PostMapping("/save")
    public Recolte saveRecolte(@RequestBody Recolte recolte) {
        return recolteService.createRecolte(recolte);
    }

    // Ou bien
    @PostMapping("/save2")
    public ResponseEntity<Recolte> createRecolte(@RequestBody Recolte recolte) {
        try {
            Recolte cree = recolteService.createRecolte(recolte);
            return new ResponseEntity<Recolte>(cree, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<Recolte>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // ===== ListRecolte =====
    @GetMapping("/all")
    public List<Recolte> getallRecolte() {
        return recolteService.getAllRecolte();
    }

    // Ou bien
    @GetMapping("/all2")
    public ResponseEntity<List<Recolte>> getAllRecoltes() {
        try {
            List<Recolte> liste = recolteService.getAllRecolte();
            if (liste.isEmpty()) {
                return new ResponseEntity<List<Recolte>>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<List<Recolte>>(liste, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<List<Recolte>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // ===== GetRecolteById =====
    // Si l'id n'existe pas, le service lève NoSuchElementException -> 404 via GlobalExceptionHandler
    @GetMapping("/getone/{id}")
    public Recolte getoneRecolte(@PathVariable Long id) {
        return recolteService.getRecolteById(id);
    }

    // Ou bien
    @GetMapping("/recoltes/{id}")
    public ResponseEntity<Recolte> getRecolteById(@PathVariable("id") Long id) {
        Recolte recolte = trouver(id);
        if (recolte != null) {
            return new ResponseEntity<Recolte>(recolte, HttpStatus.OK);
        } else {
            return new ResponseEntity<Recolte>(HttpStatus.NOT_FOUND);
        }
    }

    // ===== UpdateRecolte =====
    @PutMapping("/update/{id}")
    public Recolte updateRecolte(@PathVariable Long id, @RequestBody Recolte recolte) {
        Recolte c1 = trouver(id);
        if (c1 != null) {
            recolte.setId(id);
            return recolteService.updateRecolte(recolte);
        } else {
            throw new RuntimeException("Failed!!! Recolte introuvable : " + id);
        }
    }

    // ===== DeleteRecolte =====
    @DeleteMapping("/recoltes/{id}")
    public ResponseEntity<HttpStatus> deleteRecolte(@PathVariable("id") Long id) {
        if (trouver(id) == null) {
            return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);
        }
        try {
            recolteService.deleteRecolte(id);
            return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<HttpStatus>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Ou bien
    @DeleteMapping("/delete/{id}")
    public HashMap<String, String> deleteRecolte2(@PathVariable Long id) {
        HashMap<String, String> message = new HashMap<>();
        if (trouver(id) == null) {
            message.put("etat", "recolte not found");
            return message;
        }
        try {
            recolteService.deleteRecolte(id);
            message.put("etat", "recolte deleted");
            return message;
        } catch (Exception e) {
            message.put("etat", "recolte not deleted");
            return message;
        }
    }

    // Le service fait findById(id).get() : on transforme l'exception en null
    // pour pouvoir écrire les tests "!= null" comme dans le TP5.
    private Recolte trouver(Long id) {
        try {
            return recolteService.getRecolteById(id);
        } catch (NoSuchElementException e) {
            return null;
        }
    }
}

