package com.hellokoding.auth.web;

import com.hellokoding.auth.model.Categorie;
import com.hellokoding.auth.model.Meniu;
import com.hellokoding.auth.repository.CategorieRepository;
import com.hellokoding.auth.service.CategorieService;
import com.hellokoding.auth.service.MeniuService;
import com.hellokoding.auth.service.ProdusService;
import com.hellokoding.auth.util.Global;
import com.hellokoding.auth.validator.CategorieValidator;
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
private CategorieRepository categorieRepository;
    @Autowired
    private MeniuService meniuService;
@Autowired
private CategorieValidator categorieValidator;

    @RequestMapping(value = "/vizualizareCategorii/{meniu_id}/{vizibilitate}", method = RequestMethod.GET)
            public ModelAndView vizualizareCategorii(@PathVariable ("meniu_id") Long meniu_id, @PathVariable("vizibilitate") String vizibilitate) {
            ModelAndView model = new ModelAndView("vizualizareCategorii");

          List<Categorie> listaCategorii=new ArrayList<>();
          for(Categorie categorie: Global.mapCategoriiByMeniu.get(meniu_id)){
              if (categorie.getVizibilitate().equals(vizibilitate)){
                  listaCategorii.add(categorie);
              }
          }
                  //categorieService.findAllByVizibilitate(meniu_id,vizibilitate);

        model.addObject("categorii", listaCategorii);
        model.addObject("meniu_id",meniu_id);
        return model;
}
    @RequestMapping(value = "/vizualizareCategorii/{meniu_id}", method = RequestMethod.GET)
    public ModelAndView vizualizareCategorii(@PathVariable("meniu_id") Long meniu_id) {
        ModelAndView model = new ModelAndView("vizualizareCategorii");
        if(Global.mapCategoriiByMeniu.get(meniu_id)==null||Global.mapCategoriiByMeniu.get(meniu_id).size()==0) {
           Global.mapCategoriiByMeniu.put(meniu_id, categorieService.findAllByMeniuId(meniu_id));
        }
        model.addObject("categorii", Global.mapCategoriiByMeniu.get(meniu_id));
        model.addObject("meniu_id",meniu_id);

        return model;
    }
    @RequestMapping(value = "/administrareCategorie/{categorie_id}/{meniu_id}", method = RequestMethod.GET)
    public ModelAndView registration(@PathVariable("categorie_id") Long categorie_id,@PathVariable("meniu_id") Long meniu_id) {
        ModelAndView model = new ModelAndView("administrareCategorie");
        if(categorie_id == 0 ){
            Meniu meniu= meniuService.findById(meniu_id);
            Categorie c= new Categorie(meniu);
            if(c.getVizibilitate()==null){
                c.setVizibilitate("0");
            }
            model.addObject("categorieForm", c);
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

        categorieValidator.validate(categorieForm,bindingResult);
        if(bindingResult.hasErrors()){
            return "administrareCategorie";
        }
        categorieForm.setMeniu(meniuService.findById(meniu_id));
        if(bindingResult.hasErrors()){
            return "administrareCategorie";
        }
        Categorie categorie=categorieService.save(categorieForm);
        //modificare categorie
        if(categorieForm.getId()!=0){
            if (Global.mapCategoriiByMeniu.get(meniu_id)!=null&&Global.mapCategoriiByMeniu.get(meniu_id).size()!=0){
                List<Categorie> listaVeche=  stergeCategorie(meniu_id,categorie);
                listaVeche.add(categorie);
                Global.mapCategoriiByMeniu.replace(meniu_id,listaVeche);
            }
        }
        //adaugare categorie
        else {
            //daca exista deja macar o categorie in meniu, actualizam lista veche adaugand categoria noua
            if (Global.mapCategoriiByMeniu.get(meniu_id)!=null&&Global.mapCategoriiByMeniu.get(meniu_id).size()!=0){
                List<Categorie> listaVeche= Global.mapCategoriiByMeniu.get(meniu_id);
                listaVeche.add(categorie);
                Global.mapCategoriiByMeniu.replace(meniu_id,listaVeche);
            }
            // daca nu exista nicio categorie in meniu, adaugam noi o lista cu categ noua
            else {
                List<Categorie> listaVeche = new ArrayList<>();
                listaVeche.add(categorie);
                Global.mapCategoriiByMeniu.put(meniu_id, listaVeche);
            }
        }

        return "redirect:/vizualizareCategorii/"+meniu_id;
    }

    private List<Categorie> stergeCategorie(Long meniu_id, Categorie categorie) {
        List<Categorie> listaVeche= Global.mapCategoriiByMeniu.get(meniu_id);
        int sters=0;
        for(int i=0;i<listaVeche.size() && sters==0;i++){
            Categorie categorie1=listaVeche.get(i);
            if(categorie1.getId().equals(categorie.getId())){
                listaVeche.remove(i);
                sters=1;
            }
        }
        return listaVeche;
    }


    @RequestMapping(value = "/stergeCategorie/{categorie_id}/{meniu_id}", method = RequestMethod.GET)

    public String stergeMeniu(@PathVariable("categorie_id") Long categorie_id,@PathVariable("meniu_id") Long meniu_id) {
        Categorie categorie=categorieService.findById(categorie_id);
        categorieRepository.deleteCategorie(categorie_id);

        List<Categorie> listaVeche=stergeCategorie(meniu_id,categorie);
       // List<Categorie> listaVeche= Global.mapCategoriiByMeniu.get(meniu_id);
        Global.mapCategoriiByMeniu.replace(meniu_id,listaVeche);

        return "redirect:/vizualizareCategorii/"+meniu_id;
    }
}
