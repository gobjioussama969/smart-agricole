package com.example.smart_agri.Controller;

import com.example.smart_agri.Entity.Fertilisation;
import com.example.smart_agri.Entity.Plantation;
import com.example.smart_agri.Entity.Utilisateur;
import com.example.smart_agri.Enum.UniteQuantite;
import com.example.smart_agri.Service.FertilisationService;
import com.example.smart_agri.Service.PlantationService;
import com.example.smart_agri.Service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class FertilisationController {
    @Autowired
    FertilisationService fertilisationService;
    @Autowired
    PlantationService plantationService;
    @Autowired
    UtilisateurService utilisateurService;

    @RequestMapping("/addFertilisation")
    public String addFertilisation(Model model) {
        Fertilisation fertilisation = new Fertilisation();
        fertilisation.setPlantation(new Plantation());
        fertilisation.setAuteur(new Utilisateur());
        model.addAttribute("FertilisationForm", fertilisation);
        model.addAttribute("listePlantations", plantationService.getAllPlantation());
        model.addAttribute("listeUtilisateurs", utilisateurService.getAllUtilisateur());
        model.addAttribute("uniteQuantites", UniteQuantite.values());
        return "new_fertilisation";
    }

    @RequestMapping(value = "/saveFertilisation", method = RequestMethod.POST)
    public String saveFertilisation(@ModelAttribute("FertilisationForm") Fertilisation fertilisation) {
        fertilisationService.createFertilisation(fertilisation);
        return "redirect:/allFertilisation";
    }
}
