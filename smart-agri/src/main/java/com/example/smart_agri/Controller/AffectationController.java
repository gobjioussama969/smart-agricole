package com.example.smart_agri.Controller;


import com.example.smart_agri.Entity.Affectation;
import com.example.smart_agri.Entity.Parcelle;
import com.example.smart_agri.Entity.Utilisateur;
import com.example.smart_agri.Service.AffectationService;
import com.example.smart_agri.Service.ParcelleService;
import com.example.smart_agri.Service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AffectationController {
    @Autowired
    AffectationService affectationService;
    @Autowired
    ParcelleService parcelleService;
    @Autowired
    UtilisateurService utilisateurService;

    @RequestMapping("/addAffectation")
    public String addAffectation(Model model) {
        Affectation affectation = new Affectation();
        affectation.setUtilisateur(new Utilisateur());
        affectation.setParcelle(new Parcelle());
        model.addAttribute("AffectationForm", affectation);
        model.addAttribute("listeParcelles", parcelleService.getAllParcelle());
        model.addAttribute("listeUtilisateurs", utilisateurService.getAllUtilisateur());
        return "new_affectation";
    }

    @RequestMapping(value = "/saveAffectation", method = RequestMethod.POST)
    public String saveAffectation(@ModelAttribute("AffectationForm") Affectation affectation) {
        affectationService.createAffectation(affectation);
        return "redirect:/allAffectation";
    }
}