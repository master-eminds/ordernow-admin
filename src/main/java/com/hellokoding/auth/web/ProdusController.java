package com.hellokoding.auth.web;

import com.hellokoding.auth.model.Admin;
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
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.util.*;

@Controller
public class ProdusController {
    @Autowired
    private ProdusService produsService;
    @Autowired
    private MeniuService meniuService;
    @Autowired
    private CategorieService categorieService;
    @Autowired
    private SecurityService securityService;



    @RequestMapping(value = "/gestionareProdus", method = RequestMethod.GET)
    public String gestionareProdus(Model model,@RequestParam("meniu_id") Long meniu_id) {

        List<Categorie> categoriiProduse= categorieService.findAll();
        /*if(categoriiProduse.size()==0) {
        categorieService.save(new Categorie("Pizza","https://i.imgur.com/fGrqDIv.jpg"));
        categorieService.save(new Categorie("Paste","https://i.imgur.com/AHsYYrJ.jpg"));
        categorieService.save(new Categorie("Salate","https://i.imgur.com/BQTYi26.jpg"));
        categorieService.save(new Categorie("Ciorbe","https://i.imgur.com/alHT8b6.jpg"));
        categorieService.save(new Categorie("Burgeri","https://i.imgur.com/y3PEZub.jpg"));
        categorieService.save(new Categorie("Cafea","https://i.imgur.com/gqLWZiv.jpg"));
        categorieService.save(new Categorie("Cocktail-uri","https://i.imgur.com/PQm6hCW.jpg"));
        }*/
        //categoriiProduse= categorieService.findAll();
        HashMap<Long,String> categorii= new HashMap<>();

        for(int i=0;i< categoriiProduse.size();i++){
            categorii.put(categoriiProduse.get(i).getId(),categoriiProduse.get(i).getDenumire());
        }
        model.addAttribute("produsForm", new Produs());
        model.addAttribute("categoriiProduse", categorii);
        return "gestionareProdus";
    }

    @RequestMapping(value = "/gestionareProdus", method = RequestMethod.POST)
    public String registration(@ModelAttribute("produsForm") Produs produsForm, @ModelAttribute("categoriiProduse") Categorie categorie, @RequestParam("meniu_id") Long meniu_id, BindingResult bindingResult, Model model) {
        Meniu meniu= meniuService.findById(meniu_id);
        produsForm.setMeniu(meniu);
        if (bindingResult.hasErrors()) {
            return "gestionareProdus";
        }
        produsService.save(produsForm);
        return "redirect:/editareMeniu?id="+produsForm.getMeniu().getId();
    }
}
