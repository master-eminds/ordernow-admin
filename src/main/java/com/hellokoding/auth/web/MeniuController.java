package com.hellokoding.auth.web;

import com.hellokoding.auth.model.Meniu;
import com.hellokoding.auth.model.Produs;
import com.hellokoding.auth.service.MeniuService;
import com.hellokoding.auth.service.ProdusService;
import com.hellokoding.auth.service.SecurityService;
import com.hellokoding.auth.validator.MeniuValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

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
    @RequestMapping(value = "/administrareMeniu/{id}", method = RequestMethod.GET)
    public ModelAndView registration(@PathVariable("id") Long id) {
        ModelAndView model = new ModelAndView("administrareMeniu");

        if(id == 0 ){
            model.addObject("meniuForm", new Meniu());
            model.addObject("add","true");
        }else{
            Meniu m = meniuService.findById(id);
            //String encodedImage=Base64.encode(m.getImage());
            model.addObject("meniuForm",m);
            model.addObject("add","false");
            model.addObject("imageSrc",new String(m.getImage()));

        }
        return model;
    }

    @RequestMapping(value = "/editareMeniu/{id}", method = RequestMethod.GET)
    public ModelAndView editareMeniu(@RequestParam("id") Long id) {
        ModelAndView model = new ModelAndView("editareMeniu");
        Meniu meniu=meniuService.findById(id);
        Set<Produs> listaProduse=meniu.getProduse();
        model.addObject("listaProduse", listaProduse);
        model.addObject("meniu_id_param", id);
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
        if(meniuForm.getId()!=null&& meniuForm.getId()!=null){
            Meniu old = meniuService.findById(meniuForm.getId());
        }

        meniuService.save(meniuForm);
        return "redirect:/administrareMeniu/0";
    }
}
