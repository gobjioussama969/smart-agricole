package com.example.smart_agri.Controller;

import com.example.smart_agri.Entity.Exploitation;
import com.example.smart_agri.Entity.Parcelle;
import com.example.smart_agri.Service.ExploitationService;
import com.example.smart_agri.Service.ParcelleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ParcelleController {
    @Autowired
    ParcelleService parcelleService;
    @Autowired
    ExploitationService exploitationService;

    @RequestMapping("/addParcelle")
    public String addParcelle(Model model) {
        Parcelle parcelle = new Parcelle();
        parcelle.setExploitation(new Exploitation());
        model.addAttribute("ParcelleForm", parcelle);
        model.addAttribute("listeExploitations", exploitationService.getAllExploitation());
        return "new_parcelle";
    }

    @RequestMapping(value = "/saveParcelle", method = RequestMethod.POST)
    public String saveParcelle(@ModelAttribute("ParcelleForm") Parcelle parcelle) {
        parcelleService.createParcelle(parcelle);
        return "redirect:/allParcelle";
    }
}

