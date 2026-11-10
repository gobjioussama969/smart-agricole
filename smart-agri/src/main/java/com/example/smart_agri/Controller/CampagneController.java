package com.example.smart_agri.Controller;

import com.example.smart_agri.Entity.Campagne;
import com.example.smart_agri.Enum.StatutCampagne;
import com.example.smart_agri.Service.CampagneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CampagneController {
    @Autowired
    CampagneService campagneService;

    @RequestMapping("/addCampagne")
    public String addCampagne(Model model) {
        Campagne campagne = new Campagne();
        model.addAttribute("CampagneForm", campagne);
        model.addAttribute("statutCampagnes", StatutCampagne.values());
        return "new_campagne";
    }

    @RequestMapping(value = "/saveCampagne", method = RequestMethod.POST)
    public String saveCampagne(@ModelAttribute("CampagneForm") Campagne campagne) {
        campagneService.createCampagne(campagne);
        return "redirect:/allCampagne";
    }
}
