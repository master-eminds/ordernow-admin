package com.hellokoding.auth.web;

import com.hellokoding.auth.model.Categorie;
import com.hellokoding.auth.model.Meniu;
import com.hellokoding.auth.service.CategorieService;
import com.hellokoding.auth.service.MeniuService;
import com.hellokoding.auth.service.ProdusService;
import com.hellokoding.auth.service.SecurityService;
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
          /*  if(Global.mapCategoriiByMeniu==null||Global.mapCategoriiByMeniu.size()==0){
                for(Meniu m: Global.listaMeniuri){
                    Global.mapCategoriiByMeniu.put(m.getId(),m.getCategorii());
                }
            }

         List<Categorie> listaCategorii=Global.mapCategoriiByMeniu.get(meniu_id);
        */
          List<Categorie> listaCategorii=meniuService.findById(meniu_id).getCategorii();

        model.addObject("categorii", listaCategorii);
        model.addObject("meniu_id",meniu_id);
        return model;
}
    @RequestMapping(value = "/administrareCategorie/{categorie_id}/{meniu_id}", method = RequestMethod.GET)
    public ModelAndView registration(@PathVariable("categorie_id") Long categorie_id,@PathVariable("meniu_id") Long meniu_id) {
        ModelAndView model = new ModelAndView("administrareCategorie");
        if(categorie_id == 0 ){
            Meniu meniu= meniuService.findById(meniu_id);
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
        Categorie old=null;
        if(categorieForm.getId()!=null){
            old = categorieService.findById(categorieForm.getId());
            //Global.listaCategorii.remove(old);
            //Global.mapCategoriiByMeniu.remove(meniu_id);
        }

        Categorie categorie=categorieService.save(categorieForm);
       /* Global.listaCategorii.add(categorie);

        List<Categorie> listaVeche= Global.mapCategoriiByMeniu.get(meniu_id);
        if(old!=null){
            for(Categorie p: listaVeche){
                if(p.getId().equals(old.getId())){
                    listaVeche.remove(old);
                }
            }
        }

        listaVeche.add(categorie);
        Global.mapCategoriiByMeniu.replace(meniu_id,listaVeche);
*/
        return "redirect:/vizualizareCategorii/"+meniu_id;
    }



    @RequestMapping(value = "/stergeCategorie/{categorie_id}", method = RequestMethod.GET)

    public String stergeMeniu(@PathVariable("categorie_id") Long categorie_id) {
        Categorie categorie=categorieService.findById(categorie_id);
        categorieService.delete(categorie_id);
       /* Global.listaCategorii.remove(categorie);

        Long meniu_id=categorie.getMeniu().getId();
        List<Categorie> listaVeche= Global.mapCategoriiByMeniu.get(meniu_id);
        listaVeche.remove(categorie);
        Global.mapCategoriiByMeniu.replace(meniu_id,listaVeche);
*/
        return "redirect:/vizualizareCategorii/"+categorie.getMeniu().getId();
    }
}
