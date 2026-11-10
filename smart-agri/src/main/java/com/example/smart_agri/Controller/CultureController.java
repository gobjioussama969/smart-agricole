package com.example.smart_agri.Controller;

import com.example.smart_agri.Entity.Culture;
import com.example.smart_agri.Service.CultureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CultureController {
    @Autowired
    CultureService cultureService;

    @RequestMapping("/addCulture")
    public String addCulture(Model model) {
        Culture culture = new Culture();
        model.addAttribute("CultureForm", culture);
        return "new_culture";
    }

    @RequestMapping(value = "/saveCulture", method = RequestMethod.POST)
    public String saveCulture(@ModelAttribute("CultureForm") Culture culture) {
        cultureService.createCulture(culture);
        return "redirect:/allCulture";
    }
}

