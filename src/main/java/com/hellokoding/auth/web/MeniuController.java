package com.hellokoding.auth.web;

import com.hellokoding.auth.model.Meniu;
import com.hellokoding.auth.repository.MeniuRepository;
import com.hellokoding.auth.service.MeniuService;
import com.hellokoding.auth.util.Global;
import com.hellokoding.auth.validator.MeniuValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MeniuController {
    @Autowired
    private MeniuService meniuService;
    @Autowired
    private MeniuRepository meniuRepository;

    @Autowired
    private MeniuValidator meniuValidator;

    @RequestMapping(value = "/mitza", method = RequestMethod.GET)
    @ResponseBody
    public String mitza() {
        String obj = "hello";
        return obj;
    }

    @RequestMapping(value = "/vizualizareMeniuri", method = RequestMethod.GET)
    public ModelAndView vizualizareMeniuri() {
        ModelAndView model = new ModelAndView("vizualizareMeniuri");
        if(Global.listaMeniuri==null||Global.listaMeniuri.size()==0) {
           Global.listaMeniuri = meniuService.findAll();
        }
        model.addObject("meniuri", Global.listaMeniuri);

        return model;
    }
    @RequestMapping(value = "/vizualizareMeniuri/{stare}", method = RequestMethod.GET)
    public ModelAndView vizualizareMeniuriByStare(@PathVariable("stare") String stare) {
        ModelAndView model = new ModelAndView("vizualizareMeniuri");
        if(Global.listaMeniuri==null||Global.listaMeniuri.size()==0) {
           Global.listaMeniuri = meniuService.findAll();
        }
        List<Meniu> listaByStare=new ArrayList<>();
        for(Meniu m: Global.listaMeniuri){
            if(m.getStare().equals(stare)){
                listaByStare.add(m);
            }
        }
        model.addObject("meniuri", listaByStare);

        return model;
    }
    @RequestMapping(value = "/administrareMeniu/{id}", method = RequestMethod.GET)
    public ModelAndView registration(@PathVariable("id") Long id) {
        ModelAndView model = new ModelAndView("administrareMeniu");

        if(id == 0 ){
            Meniu m= new Meniu();
            if(m.getStare()==null){
                m.setStare("0");
            }
            model.addObject("meniuForm", m);
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
    public String adaugareMeniu(@ModelAttribute("meniuForm") Meniu meniuForm, BindingResult bindingResult)  {

        meniuValidator.validate(meniuForm,bindingResult);
        if(bindingResult.hasErrors()){
            return "administrareMeniu";
        }
        if(meniuForm.getId()!=null){
            int sters=0;
            for(int i=0;i<Global.listaMeniuri.size() && sters==0;i++){
                Meniu meniu=Global.listaMeniuri.get(i);
                if(meniu.getId().equals(meniuForm.getId())){
                    Global.listaMeniuri.remove(i);
                    sters=1;
                }
            }
        }

        Meniu meniu=meniuService.save(meniuForm);
        Global.listaMeniuri.add(meniu);
        return "redirect:/vizualizareMeniuri";
    }


    @RequestMapping(value = "/stergeMeniu/{meniu_id}", method = RequestMethod.GET)
    public String stergeMeniu(@PathVariable("meniu_id") Long meniu_id) {

        int sters=0;
        for(int i=0;i<Global.listaMeniuri.size() && sters==0;i++){
            Meniu meniu=Global.listaMeniuri.get(i);
            if(meniu.getId().equals(meniu_id)){
                Global.listaMeniuri.remove(i);
                sters=1;
            }
        }
        meniuRepository.deleteMeniu(meniu_id);


        return "redirect:/vizualizareMeniuri";
    }
}
