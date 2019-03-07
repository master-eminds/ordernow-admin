package com.hellokoding.auth.web;

import com.hellokoding.auth.model.Categorie;
import com.hellokoding.auth.model.Meniu;
import com.hellokoding.auth.model.Produs;
import com.hellokoding.auth.service.CategorieService;
import com.hellokoding.auth.service.MeniuService;
import com.hellokoding.auth.service.ProdusService;
import com.hellokoding.auth.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;

@Controller
public class CategorieController {
    @Autowired
    private ProdusService produsService;

    @Autowired
    private CategorieService categorieService;
    @Autowired
    private SecurityService securityService;



    @RequestMapping(value = "/gestionareCategorie", method = RequestMethod.GET)
    public String gestionareProdus(Model model) {
        List<Categorie> categorii= categorieService.findAll();

        model.addAttribute("categorieForm", new Categorie());
        model.addAttribute("listaCategorii", categorii);
        return "gestionareCategorie";
    }

    @RequestMapping(value = "/gestionareCategorie", method = RequestMethod.POST)
    public String registration(@ModelAttribute("categorieForm") Categorie categorieForm,  BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "gestionareCategorie";
        }
        categorieService.save(categorieForm);
        return "redirect:/meniuriRestaurant";
    }
}
