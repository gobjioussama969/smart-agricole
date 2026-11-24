package com.example.smart_agri.RestController;

import com.example.smart_agri.Entity.Culture;
import com.example.smart_agri.Service.CultureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("api/culture")
public class CultureRestController {
    @Autowired
    CultureService cultureService;

    // ===== AjouterCulture =====
    @PostMapping("/save")
    public Culture saveCulture(@RequestBody Culture culture) {
        return cultureService.createCulture(culture);
    }

    // Ou bien
    @PostMapping("/save2")
    public ResponseEntity<Culture> createCulture(@RequestBody Culture culture) {
        try {
            Culture cree = cultureService.createCulture(culture);
            return new ResponseEntity<Culture>(cree, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<Culture>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // ===== ListCulture =====
    @GetMapping("/all")
    public List<Culture> getallCulture() {
        return cultureService.getAllCulture();
    }

    // Ou bien
    @GetMapping("/all2")
    public ResponseEntity<List<Culture>> getAllCultures() {
        try {
            List<Culture> liste = cultureService.getAllCulture();
            if (liste.isEmpty()) {
                return new ResponseEntity<List<Culture>>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<List<Culture>>(liste, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<List<Culture>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // ===== GetCultureById =====
    // Si l'id n'existe pas, le service lève NoSuchElementException -> 404 via GlobalExceptionHandler
    @GetMapping("/getone/{id}")
    public Culture getoneCulture(@PathVariable Long id) {
        return cultureService.getCultureById(id);
    }

    // Ou bien
    @GetMapping("/cultures/{id}")
    public ResponseEntity<Culture> getCultureById(@PathVariable("id") Long id) {
        Culture culture = trouver(id);
        if (culture != null) {
            return new ResponseEntity<Culture>(culture, HttpStatus.OK);
        } else {
            return new ResponseEntity<Culture>(HttpStatus.NOT_FOUND);
        }
    }

    // ===== UpdateCulture =====
    @PutMapping("/update/{id}")
    public Culture updateCulture(@PathVariable Long id, @RequestBody Culture culture) {
        Culture c1 = trouver(id);
        if (c1 != null) {
            culture.setId(id);
            return cultureService.updateCulture(culture);
        } else {
            throw new RuntimeException("Failed!!! Culture introuvable : " + id);
        }
    }

    // ===== DeleteCulture =====
    @DeleteMapping("/cultures/{id}")
    public ResponseEntity<HttpStatus> deleteCulture(@PathVariable("id") Long id) {
        if (trouver(id) == null) {
            return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);
        }
        try {
            cultureService.deleteCulture(id);
            return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<HttpStatus>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Ou bien
    @DeleteMapping("/delete/{id}")
    public HashMap<String, String> deleteCulture2(@PathVariable Long id) {
        HashMap<String, String> message = new HashMap<>();
        if (trouver(id) == null) {
            message.put("etat", "culture not found");
            return message;
        }
        try {
            cultureService.deleteCulture(id);
            message.put("etat", "culture deleted");
            return message;
        } catch (Exception e) {
            message.put("etat", "culture not deleted");
            return message;
        }
    }


    private Culture trouver(Long id) {
        try {
            return cultureService.getCultureById(id);
        } catch (NoSuchElementException e) {
            return null;
        }
    }
}

