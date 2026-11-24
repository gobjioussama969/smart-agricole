package com.example.smart_agri.RestController;
import com.example.smart_agri.Entity.Utilisateur;
import com.example.smart_agri.Service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("api/utilisateur")
public class UtilisateurRestController {
    @Autowired
    UtilisateurService utilisateurService;

    // ===== AjouterUtilisateur =====
    @PostMapping("/save")
    public Utilisateur saveUtilisateur(@RequestBody Utilisateur utilisateur) {
        return utilisateurService.createUtilisateur(utilisateur);
    }

    // Ou bien
    @PostMapping("/save2")
    public ResponseEntity<Utilisateur> createUtilisateur(@RequestBody Utilisateur utilisateur) {
        try {
            Utilisateur cree = utilisateurService.createUtilisateur(utilisateur);
            return new ResponseEntity<Utilisateur>(cree, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<Utilisateur>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // ===== ListUtilisateur =====
    @GetMapping("/all")
    public List<Utilisateur> getallUtilisateur() {
        return utilisateurService.getAllUtilisateur();
    }

    // Ou bien
    @GetMapping("/all2")
    public ResponseEntity<List<Utilisateur>> getAllUtilisateurs() {
        try {
            List<Utilisateur> liste = utilisateurService.getAllUtilisateur();
            if (liste.isEmpty()) {
                return new ResponseEntity<List<Utilisateur>>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<List<Utilisateur>>(liste, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<List<Utilisateur>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // ===== GetUtilisateurById =====
    // Si l'id n'existe pas, le service lève NoSuchElementException -> 404 via GlobalExceptionHandler
    @GetMapping("/getone/{id}")
    public Utilisateur getoneUtilisateur(@PathVariable Long id) {
        return utilisateurService.getUtilisateurById(id);
    }

    // Ou bien
    @GetMapping("/utilisateurs/{id}")
    public ResponseEntity<Utilisateur> getUtilisateurById(@PathVariable("id") Long id) {
        Utilisateur utilisateur = trouver(id);
        if (utilisateur != null) {
            return new ResponseEntity<Utilisateur>(utilisateur, HttpStatus.OK);
        } else {
            return new ResponseEntity<Utilisateur>(HttpStatus.NOT_FOUND);
        }
    }

    // ===== UpdateUtilisateur =====
    @PutMapping("/update/{id}")
    public Utilisateur updateUtilisateur(@PathVariable Long id, @RequestBody Utilisateur utilisateur) {
        Utilisateur c1 = trouver(id);
        if (c1 != null) {
            utilisateur.setId(id);
            return utilisateurService.updateUtilisateur(utilisateur);
        } else {
            throw new RuntimeException("Failed!!! Utilisateur introuvable : " + id);
        }
    }

    // ===== DeleteUtilisateur =====
    @DeleteMapping("/utilisateurs/{id}")
    public ResponseEntity<HttpStatus> deleteUtilisateur(@PathVariable("id") Long id) {
        if (trouver(id) == null) {
            return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);
        }
        try {
            utilisateurService.deleteUtilisateur(id);
            return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<HttpStatus>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Ou bien
    @DeleteMapping("/delete/{id}")
    public HashMap<String, String> deleteUtilisateur2(@PathVariable Long id) {
        HashMap<String, String> message = new HashMap<>();
        if (trouver(id) == null) {
            message.put("etat", "utilisateur not found");
            return message;
        }
        try {
            utilisateurService.deleteUtilisateur(id);
            message.put("etat", "utilisateur deleted");
            return message;
        } catch (Exception e) {
            message.put("etat", "utilisateur not deleted");
            return message;
        }
    }

    private Utilisateur trouver(Long id) {
        try {
            return utilisateurService.getUtilisateurById(id);
        } catch (NoSuchElementException e) {
            return null;
        }
    }
}

