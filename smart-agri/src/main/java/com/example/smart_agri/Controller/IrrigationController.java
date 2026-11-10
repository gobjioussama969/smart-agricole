package com.example.smart_agri.Controller;

import com.example.smart_agri.Entity.Irrigation;
import com.example.smart_agri.Entity.Plantation;
import com.example.smart_agri.Entity.Utilisateur;
import com.example.smart_agri.Enum.UniteVolume;
import com.example.smart_agri.Service.IrrigationService;
import com.example.smart_agri.Service.PlantationService;
import com.example.smart_agri.Service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IrrigationController {
    @Autowired
    IrrigationService irrigationService;
    @Autowired
    PlantationService plantationService;
    @Autowired
    UtilisateurService utilisateurService;

    @RequestMapping("/addIrrigation")
    public String addIrrigation(Model model) {
        Irrigation irrigation = new Irrigation();
        irrigation.setPlantation(new Plantation());
        irrigation.setAuteur(new Utilisateur());
        model.addAttribute("IrrigationForm", irrigation);
        model.addAttribute("listePlantations", plantationService.getAllPlantation());
        model.addAttribute("listeUtilisateurs", utilisateurService.getAllUtilisateur());
        model.addAttribute("uniteVolumes", UniteVolume.values());
        return "new_irrigation";
    }

    @RequestMapping(value = "/saveIrrigation", method = RequestMethod.POST)
    public String saveIrrigation(@ModelAttribute("IrrigationForm") Irrigation irrigation) {
        irrigationService.createIrrigation(irrigation);
        return "redirect:/allIrrigation";
    }
}
