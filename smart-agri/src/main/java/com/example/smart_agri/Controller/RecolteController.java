package com.example.smart_agri.Controller;

import com.example.smart_agri.Entity.Plantation;
import com.example.smart_agri.Entity.Recolte;
import com.example.smart_agri.Entity.Utilisateur;
import com.example.smart_agri.Enum.UniteRecolte;
import com.example.smart_agri.Service.PlantationService;
import com.example.smart_agri.Service.RecolteService;
import com.example.smart_agri.Service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RecolteController {
    @Autowired
    RecolteService recolteService;
    @Autowired
    PlantationService plantationService;
    @Autowired
    UtilisateurService utilisateurService;

    @RequestMapping("/addRecolte")
    public String addRecolte(Model model) {
        Recolte recolte = new Recolte();
        recolte.setPlantation(new Plantation());
        recolte.setAuteur(new Utilisateur());
        model.addAttribute("RecolteForm", recolte);
        model.addAttribute("listePlantations", plantationService.getAllPlantation());
        model.addAttribute("listeUtilisateurs", utilisateurService.getAllUtilisateur());
        model.addAttribute("uniteRecoltes", UniteRecolte.values());
        return "new_recolte";
    }

    @RequestMapping(value = "/saveRecolte", method = RequestMethod.POST)
    public String saveRecolte(@ModelAttribute("RecolteForm") Recolte recolte) {
        recolteService.createRecolte(recolte);
        return "redirect:/allRecolte";
    }
}

