package com.example.smart_agri.Controller;

import com.example.smart_agri.Entity.Utilisateur;
import com.example.smart_agri.Enum.Role;
import com.example.smart_agri.Service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UtilisateurController {
    @Autowired
    UtilisateurService utilisateurService;

    @RequestMapping("/addUtilisateur")
    public String addUtilisateur(Model model) {
        Utilisateur utilisateur = new Utilisateur();
        model.addAttribute("UtilisateurForm", utilisateur);
        model.addAttribute("roles", Role.values());
        return "new_utilisateur";
    }

    @RequestMapping(value = "/saveUtilisateur", method = RequestMethod.POST)
    public String saveUtilisateur(@ModelAttribute("UtilisateurForm") Utilisateur utilisateur) {
        utilisateurService.createUtilisateur(utilisateur);
        return "redirect:/allUtilisateur";
    }
}

