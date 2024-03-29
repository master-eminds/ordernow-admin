package com.hellokoding.auth.web;

import com.hellokoding.auth.model.Ospatar;
import com.hellokoding.auth.service.OspatarService;
import com.hellokoding.auth.service.SecurityService;
import com.hellokoding.auth.validator.OspatarValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class OspatarController {
    @Autowired
    private SecurityService securityService;
    @Autowired
    private OspatarValidator ospatarValidator;
    @Autowired
    private OspatarService ospatarService;


    @RequestMapping(value = "/administrareOspatari/{id}", method = RequestMethod.GET)
    public ModelAndView veziOspatari(@PathVariable("id") Long id) {
        ModelAndView model = new ModelAndView("administrareOspatari");
        if(id == 0 ){
            model.addObject("ospatarForm", new Ospatar());
            model.addObject("add","true");
        }else{
            Ospatar o = ospatarService.findById(id);
            o.setParola(null);
            model.addObject("ospatarForm",o);
            model.addObject("add","false");
        }
        return model;
    }

    @RequestMapping(value = "/salvareOspatar", method = RequestMethod.POST)
    public String adaugareMeniu(@ModelAttribute("ospatarForm") Ospatar ospatarForm, BindingResult bindingResult, Model model) {

        ospatarValidator.validate(ospatarForm,bindingResult);
       // ospatarService.save(ospatarForm);
         if(bindingResult.hasErrors()){
            return "administrareOspatari";
        }
        if(ospatarForm.getId()!=null&& ospatarForm.getId()!=null){
            Ospatar old = ospatarService.findById(ospatarForm.getId());
            ospatarForm.setParola(old.getParola());
        }
        ospatarService.save(ospatarForm);
        return "redirect:/administrareOspatari/0";
    }
}
