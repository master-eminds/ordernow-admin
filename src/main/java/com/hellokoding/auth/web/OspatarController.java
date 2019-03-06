package com.hellokoding.auth.web;

import com.hellokoding.auth.model.Meniu;
import com.hellokoding.auth.model.Ospatar;
import com.hellokoding.auth.service.OspatarService;
import com.hellokoding.auth.service.SecurityService;
import com.hellokoding.auth.validator.OspatarValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class OspatarController {
    @Autowired
    private SecurityService securityService;
    @Autowired
    private OspatarValidator ospatarValidator;
    @Autowired
    private OspatarService ospatarService;
    @RequestMapping(value = "/administrareOspatari", method = RequestMethod.GET)
    public ModelAndView veziOspatari() {
        ModelAndView model = new ModelAndView("administrareOspatari");
        List<Ospatar> listaOspatari=ospatarService.findAll();
        model.addObject("listaOspatari", listaOspatari);
        return model;
    }
    @RequestMapping(value = "/gestionareOspatar", method = RequestMethod.GET)
    public String adaugareMeniu(Model model) {
        model.addAttribute("ospatarForm", new Ospatar());
        return "gestionareOspatar";
    }
    @RequestMapping(value = "/gestionareOspatar", method = RequestMethod.POST)
    public String adaugareMeniu(@ModelAttribute("ospatarForm") Ospatar ospatarForm, BindingResult bindingResult, Model model) {
        //ospatarValidator.validate(ospatarForm, bindingResult);
        if (bindingResult.hasErrors()) {
            return "gestionareOspatar";
        }
        ospatarService.save(ospatarForm);
        return "redirect:/administrareOspatari";
    }
}
