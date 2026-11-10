package com.example.smart_agri.Controller;

import com.example.smart_agri.Entity.Observation;
import com.example.smart_agri.Entity.Plantation;
import com.example.smart_agri.Entity.Utilisateur;
import com.example.smart_agri.Service.ObservationService;
import com.example.smart_agri.Service.PlantationService;
import com.example.smart_agri.Service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ObservationController {
    @Autowired
    ObservationService observationService;
    @Autowired
    PlantationService plantationService;
    @Autowired
    UtilisateurService utilisateurService;

    @RequestMapping("/addObservation")
    public String addObservation(Model model) {
        Observation observation = new Observation();
        observation.setPlantation(new Plantation());
        observation.setAuteur(new Utilisateur());
        model.addAttribute("ObservationForm", observation);
        model.addAttribute("listePlantations", plantationService.getAllPlantation());
        model.addAttribute("listeUtilisateurs", utilisateurService.getAllUtilisateur());
        return "new_observation";
    }

    @RequestMapping(value = "/saveObservation", method = RequestMethod.POST)
    public String saveObservation(@ModelAttribute("ObservationForm") Observation observation) {
        observationService.createObservation(observation);
        return "redirect:/allObservation";
    }
}

