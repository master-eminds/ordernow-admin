package com.hellokoding.auth.web;

import com.hellokoding.auth.model.Masa;
import com.hellokoding.auth.service.MasaService;
import com.hellokoding.auth.service.SecurityService;
import com.hellokoding.auth.util.Global;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MasaController {
    @Autowired
    private MasaService masaService;

    @Autowired
    private SecurityService securityService;

    @RequestMapping(value = "/adaugaMasa", method = RequestMethod.POST)
    public String adaugaMasa(@ModelAttribute("adaugaMasaForm") Masa masaForm, Model model) {

       Masa masa= masaService.save(masaForm);
        Global.listaMese.add(masa);
        return "redirect:/welcome";
    }

}
