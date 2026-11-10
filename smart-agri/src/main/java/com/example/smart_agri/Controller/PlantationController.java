package com.example.smart_agri.Controller;

import com.example.smart_agri.Entity.Campagne;
import com.example.smart_agri.Entity.Parcelle;
import com.example.smart_agri.Entity.Plantation;
import com.example.smart_agri.Entity.Variete;
import com.example.smart_agri.Enum.StatutPlantation;
import com.example.smart_agri.Service.CampagneService;
import com.example.smart_agri.Service.ParcelleService;
import com.example.smart_agri.Service.PlantationService;
import com.example.smart_agri.Service.VarieteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PlantationController {
    @Autowired
    PlantationService plantationService;
    @Autowired
    CampagneService campagneService;
    @Autowired
    ParcelleService parcelleService;
    @Autowired
    VarieteService varieteService;

    @RequestMapping("/addPlantation")
    public String addPlantation(Model model) {
        Plantation plantation = new Plantation();
        plantation.setParcelle(new Parcelle());
        plantation.setCampagne(new Campagne());
        plantation.setVariete(new Variete());
        model.addAttribute("PlantationForm", plantation);
        model.addAttribute("listeCampagnes", campagneService.getAllCampagne());
        model.addAttribute("listeParcelles", parcelleService.getAllParcelle());
        model.addAttribute("listeVarietes", varieteService.getAllVariete());
        model.addAttribute("statutPlantations", StatutPlantation.values());
        return "new_plantation";
    }

    @RequestMapping(value = "/savePlantation", method = RequestMethod.POST)
    public String savePlantation(@ModelAttribute("PlantationForm") Plantation plantation) {
        plantationService.createPlantation(plantation);
        return "redirect:/allPlantation";
    }
}

