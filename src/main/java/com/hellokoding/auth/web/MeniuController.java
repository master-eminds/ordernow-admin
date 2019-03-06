package com.hellokoding.auth.web;

import com.hellokoding.auth.model.Admin;
import com.hellokoding.auth.model.Meniu;
import com.hellokoding.auth.model.Ospatar;
import com.hellokoding.auth.model.Produs;
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
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Set;

@Controller
public class MeniuController {
    @Autowired
    private MeniuService meniuService;
    @Autowired
    private ProdusService produsService;

    @Autowired
    private SecurityService securityService;
    @RequestMapping(value = "/meniuriRestaurant", method = RequestMethod.GET)
    public ModelAndView veziMeniu() {
        ModelAndView model = new ModelAndView("meniuriRestaurant");
        List<Meniu> listaMeniuri=meniuService.findAll();
        model.addObject("meniuri", listaMeniuri);
        model.addObject("meniuForm", new Meniu());
        return model;
    }
    @RequestMapping(value = "/meniuriRestaurant", method = RequestMethod.POST)
    public String registration(@ModelAttribute("meniuForm") Meniu meniuForm, BindingResult bindingResult, Model model) {

        meniuService.save(meniuForm);
        return "redirect:/meniuriRestaurant";
    }
    /*@RequestMapping(value = "/adaugaMeniu", method = RequestMethod.GET)
    public String adaugareMeniu(Model model) {
        model.addAttribute("meniuForm", new Meniu());
        return "adaugaMeniu";
    }
    @RequestMapping(value = "/adaugaMeniu", method = RequestMethod.POST)
    public String adaugareMeniu(@ModelAttribute("meniuForm") Meniu meniuForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "adaugaMeniu";
        }
        meniuService.save(meniuForm);
        return "redirect:/meniuriRestaurant";
    }*/
    @RequestMapping(value = "/editareMeniu", method = RequestMethod.GET)
    public ModelAndView editareMeniu(@RequestParam("id") Long id) {
        ModelAndView model = new ModelAndView("editareMeniu");
        Meniu meniu=meniuService.findById(id);
        Set<Produs> listaProduse=meniu.getProduse();
        model.addObject("listaProduse", listaProduse);
        model.addObject("meniu_id_param", id);
        return model;
    }

}
