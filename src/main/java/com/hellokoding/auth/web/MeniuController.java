package com.hellokoding.auth.web;

import com.hellokoding.auth.model.Meniu;
import com.hellokoding.auth.service.MeniuService;
import com.hellokoding.auth.service.ProdusService;
import com.hellokoding.auth.service.SecurityService;
import com.hellokoding.auth.validator.MeniuValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.List;

@Controller
public class MeniuController {
    @Autowired
    private MeniuService meniuService;
    @Autowired
    private ProdusService produsService;
    @Autowired
    private MeniuValidator meniuValidator;

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
    @RequestMapping(value = "/vizualizareMeniuri", method = RequestMethod.GET)
    public ModelAndView vizualizareMeniuri() {
        ModelAndView model = new ModelAndView("vizualizareMeniuri");
        List<Meniu> listaMeniuri=meniuService.findAll();
        model.addObject("meniuri", listaMeniuri);

        return model;
    }

    @RequestMapping(value = "/administrareMeniu/{id}", method = RequestMethod.GET)
    public ModelAndView registration(@PathVariable("id") Long id) {
        ModelAndView model = new ModelAndView("administrareMeniu");

        if(id == 0 ){
            model.addObject("meniuForm", new Meniu());
            model.addObject("add","true");
        }else{
            Meniu m = meniuService.findById(id);
            model.addObject("meniuForm",m);
            model.addObject("add","false");
            model.addObject("imageSrc",new String(m.getImage()));

        }
        return model;
    }


    @RequestMapping(value = "/salvareMeniu", method = RequestMethod.POST)
    public String adaugareMeniu(@ModelAttribute("meniuForm") Meniu meniuForm, BindingResult bindingResult) throws UnsupportedEncodingException, SQLException {

        meniuValidator.validate(meniuForm,bindingResult);
        byte[] file = meniuForm.getImage();
        System.out.println(new String(file));

        if(bindingResult.hasErrors()){
            return "administrareMeniu";
        }
        if(meniuForm.getId()!=null){
            Meniu old = meniuService.findById(meniuForm.getId());
        }

        meniuService.save(meniuForm);
        return "redirect:/vizualizareMeniuri";
    }


    @RequestMapping(value = "/stergeMeniu/{meniu_id}", method = RequestMethod.PUT)
    public String stergeMeniu(@PathVariable("meniu_id") Long meniu_id, Model model, BindingResult bindingResult) throws UnsupportedEncodingException, SQLException {
        if(bindingResult.hasErrors()){
            return "vizualizareMeniuri";
        }
        meniuService.delete(meniu_id);
        return "redirect:/vizualizareMeniuri";
    }
}
