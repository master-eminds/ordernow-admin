package com.hellokoding.auth.web;

import com.hellokoding.auth.model.Categorie;
import com.hellokoding.auth.model.Meniu;
import com.hellokoding.auth.service.CategorieService;
import com.hellokoding.auth.service.MeniuService;
import com.hellokoding.auth.service.ProdusService;
import com.hellokoding.auth.service.SecurityService;
import com.hellokoding.auth.util.Global;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CategorieController {
    @Autowired
    private ProdusService produsService;

    @Autowired
    private CategorieService categorieService;

    @Autowired
    private MeniuService meniuService;
    @Autowired
    private SecurityService securityService;


    @RequestMapping(value = "/vizualizareCategorii/{meniu_id}", method = RequestMethod.GET)
            public ModelAndView vizualizareCategorii(@PathVariable ("meniu_id") Long meniu_id) {
            ModelAndView model = new ModelAndView("vizualizareCategorii");
            int i=0;
            while(i<Global.listaMeniuri.size() && !Global.listaMeniuri.get(i).getId().equals(meniu_id)){
                i++;
            }
            List<Categorie> listaCategorii=new ArrayList<>();
            if(i<Global.listaMeniuri.size()){
                if(Global.listaMeniuri.get(i).getId().equals(meniu_id)){
                    listaCategorii.addAll(Global.listaMeniuri.get(i).getCategorii());
                }
            }

            model.addObject("categorii", listaCategorii);
            model.addObject("meniu_id",meniu_id);
        return model;
}
    @RequestMapping(value = "/administrareCategorie/{categorie_id}/{meniu_id}", method = RequestMethod.GET)
    public ModelAndView registration(@PathVariable("categorie_id") Long categorie_id,@PathVariable("meniu_id") Long meniu_id) {
        ModelAndView model = new ModelAndView("administrareCategorie");
        Meniu meniu= meniuService.findById(meniu_id);
        if(categorie_id == 0 ){
            model.addObject("categorieForm", new Categorie(meniu));
            model.addObject("add","true");
        }else{
            Categorie c = categorieService.findById(categorie_id);
            model.addObject("categorieForm",c);
            model.addObject("add","false");
            model.addObject("imageSrc",new String(c.getImagine()));

        }
        return model;
    }


    @RequestMapping(value = "/salvareCategorie/{meniu_id}", method = RequestMethod.POST)
    public String adaugareMeniu(@ModelAttribute("categorieForm") Categorie categorieForm, @PathVariable("meniu_id") Long meniu_id, BindingResult bindingResult) throws UnsupportedEncodingException, SQLException {

        byte[] file = categorieForm.getImagine();
        //System.out.println(new String(file));
        categorieForm.setMeniu(meniuService.findById(meniu_id));
        if(bindingResult.hasErrors()){
            return "administrareCategorie";
        }
        if(categorieForm.getId()!=null){
            Categorie old = categorieService.findById(categorieForm.getId());
            Global.listaCategorii.remove(old);
        }

        Categorie categorie=categorieService.save(categorieForm);
        Global.listaCategorii.add(categorie);
        return "redirect:/vizualizareCategorii/"+meniu_id;
    }



    @RequestMapping(value = "/stergeCategorie/{categorie_id}", method = RequestMethod.GET)

    public String stergeMeniu(@PathVariable("categorie_id") Long categorie_id) {
        Categorie categorie=categorieService.findById(categorie_id);
        categorieService.delete(categorie_id);
        Global.listaCategorii.remove(categorie);
        return "redirect:/vizualizareCategorii/"+categorie.getMeniu().getId();
    }
}
