package com.example.smart_agri.Controller;

import com.example.smart_agri.Entity.Culture;
import com.example.smart_agri.Entity.Variete;
import com.example.smart_agri.Service.CultureService;
import com.example.smart_agri.Service.VarieteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class VarieteController {
    @Autowired
    VarieteService varieteService;
    @Autowired
    CultureService cultureService;

    @RequestMapping("/addVariete")
    public String addVariete(Model model) {
        Variete variete = new Variete();
        variete.setCulture(new Culture());
        model.addAttribute("VarieteForm", variete);
        model.addAttribute("listeCultures", cultureService.getAllCulture());
        return "new_variete";
    }

    @RequestMapping(value = "/saveVariete", method = RequestMethod.POST)
    public String saveVariete(@ModelAttribute("VarieteForm") Variete variete) {
        varieteService.createVariete(variete);
        return "redirect:/allVariete";
    }
}

