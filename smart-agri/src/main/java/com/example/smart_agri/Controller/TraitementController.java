package com.example.smart_agri.Controller;
import com.example.smart_agri.Entity.Plantation;
import com.example.smart_agri.Entity.Traitement;
import com.example.smart_agri.Entity.Utilisateur;
import com.example.smart_agri.Enum.UniteQuantite;
import com.example.smart_agri.Service.PlantationService;
import com.example.smart_agri.Service.TraitementService;
import com.example.smart_agri.Service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TraitementController {
    @Autowired
    TraitementService traitementService;
    @Autowired
    PlantationService plantationService;
    @Autowired
    UtilisateurService utilisateurService;

    @RequestMapping("/addTraitement")
    public String addTraitement(Model model) {
        Traitement traitement = new Traitement();
        traitement.setPlantation(new Plantation());
        traitement.setAuteur(new Utilisateur());
        model.addAttribute("TraitementForm", traitement);
        model.addAttribute("listePlantations", plantationService.getAllPlantation());
        model.addAttribute("listeUtilisateurs", utilisateurService.getAllUtilisateur());
        model.addAttribute("uniteQuantites", UniteQuantite.values());
        return "new_traitement";
    }

    @RequestMapping(value = "/saveTraitement", method = RequestMethod.POST)
    public String saveTraitement(@ModelAttribute("TraitementForm") Traitement traitement) {
        traitementService.createTraitement(traitement);
        return "redirect:/allTraitement";
    }
}

