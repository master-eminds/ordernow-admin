package com.hellokoding.auth.web;

import com.hellokoding.auth.model.Categorie;
import com.hellokoding.auth.model.Meniu;
import com.hellokoding.auth.model.Produs;
import com.hellokoding.auth.model.Review;
import com.hellokoding.auth.service.*;
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
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.List;
import java.util.Map;


@Controller
public class ProdusController {
    @Autowired
    private ProdusService produsService;
    @Autowired
    private MeniuService meniuService;
    @Autowired
    private CategorieService categorieService;
    @Autowired
    private SecurityService securityService;
    @Autowired
    private ReviewService reviewService;

    private Map<Long,Double> noteProdus;
    private List<Review> reviewsProdus;




    @RequestMapping(value = "/administrareProdus/{produs_id}/{meniu_id}", method = RequestMethod.GET)
    public ModelAndView registration(@PathVariable("produs_id") Long produs_id, @PathVariable("meniu_id") Long meniu_id) {
        ModelAndView model = new ModelAndView("administrareProdus");
        Meniu meniu= meniuService.findById(meniu_id);

        if(produs_id == 0 ){
            model.addObject("meniu",meniu);
            model.addObject("produsForm", new Produs());
            model.addObject("add","true");
        }else{
            Produs p = produsService.findById(produs_id);
            model.addObject("meniu",meniu);
            model.addObject("produsForm",p);
            model.addObject("add","false");
            model.addObject("imageSrc",new String(p.getImagine()));
        }
        return model;
    }


    @RequestMapping(value = "/salvareProdus/{meniu_id}", method = RequestMethod.POST)
    public String adaugareMeniu(@ModelAttribute("produsForm") Produs produsForm, @PathVariable("meniu_id") Long meniu_id, BindingResult bindingResult) throws UnsupportedEncodingException, SQLException {

        byte[] file = produsForm.getImagine();
        produsForm.setMeniu_id(meniu_id);

        if(bindingResult.hasErrors()){
            return "administrareProdus";
        }
        Produs old=null;
        if(produsForm.getId()!=null){
            old = produsService.findById(produsForm.getId());
        }
    /*    Produs produs= produsService.save(produsForm);
        //Global.listaProduse.add(produs);
        Long categorie_id=produs.getCategorie().getId();
        List<Produs> listaVeche= Global.mapProduseByCategorie.get(categorie_id);
        if(old!=null){
            for(Produs p: listaVeche){
                if(p.getId().equals(old.getId())){
                    listaVeche.remove(old);
                }
            }
        }
        listaVeche.add(produs);
        Global.mapProduseByCategorie.replace(produs.getCategorie().getId(),listaVeche);*/

        produsService.save(produsForm);
        return "redirect:/detaliiCategorie/"+produsForm.getCategorie().getId();
    }

    @RequestMapping(value = "/detaliiCategorie/{categorie_id}", method = RequestMethod.GET)
    public ModelAndView vizualizareCategorii(@PathVariable ("categorie_id") Long categorie_id) {
        ModelAndView model = new ModelAndView("detaliiCategorie");

       /*Long meniu_id=0L;
       if(Global.listaCategorii==null||Global.listaCategorii.size()==0){
           Global.listaCategorii=categorieService.findAll();
       }

        if(Global.mapProduseByCategorie==null||Global.mapProduseByCategorie.size()==0){
            for(Categorie c: Global.listaCategorii){
                Global.mapProduseByCategorie.put(c.getId(),c.getProduse());
            }
        }
        if(Global.mapProduseByCategorie.containsKey(categorie_id)){
            List<Produs> listaProduse=Global.mapProduseByCategorie.get(categorie_id);
            meniu_id=listaProduse.get(0).getMeniu_id();
            model.addObject("produse", listaProduse);
        }*/
       Categorie categorie= categorieService.findById(categorie_id);
       Long meniu_id=categorie.getMeniu().getId();
        List<Produs> listaProduse= categorie.getProduse();
        model.addObject("produse", listaProduse);
        model.addObject("meniu_id",meniu_id);

        return model;
    }
    @RequestMapping(value = "/detaliiCategorie/{categorie_id}/{vizibilitate}", method = RequestMethod.GET)
    public ModelAndView vizualizareCategorii(@PathVariable ("categorie_id") Long categorie_id, @PathVariable("vizibilitate") String vizibilitate){
        ModelAndView model = new ModelAndView("detaliiCategorie");

       /*Long meniu_id=0L;
       if(Global.listaCategorii==null||Global.listaCategorii.size()==0){
           Global.listaCategorii=categorieService.findAll();
       }

        if(Global.mapProduseByCategorie==null||Global.mapProduseByCategorie.size()==0){
            for(Categorie c: Global.listaCategorii){
                Global.mapProduseByCategorie.put(c.getId(),c.getProduse());
            }
        }
        if(Global.mapProduseByCategorie.containsKey(categorie_id)){
            List<Produs> listaProduse=Global.mapProduseByCategorie.get(categorie_id);
            meniu_id=listaProduse.get(0).getMeniu_id();
            model.addObject("produse", listaProduse);
        }*/
        Long meniu_id= categorieService.findById(categorie_id).getMeniu().getId();
        List<Produs> listaProduse= produsService.findAllByVizibilitate(categorie_id,vizibilitate);
        model.addObject("produse", listaProduse);
        model.addObject("meniu_id",meniu_id);

        return model;
    }




    @RequestMapping(value = "/statisticiReviewProduse", method = RequestMethod.GET)
    public ModelAndView statisticiReviewProduse() {
        ModelAndView model = new ModelAndView("statisticiReviewProduse");
        //List<Produs> produse= produsService.findAll();
        if(Global.listaProduse==null||Global.listaProduse.size()==0){
            Global.listaProduse=produsService.findAll();
        }
        if(Global.dateChartReviewProduse.isEmpty() ||Global.dateChartReviewProduse.trim().length()==0){
            Global.dateChartReviewProduse=dateChartProduse(Global.listaProduse);
        }
        //String date= dateChartProduse(Global.listaProduse);
        model.addObject("dateChartReview", Global.dateChartReviewProduse);
        model.addObject("listaProduse",Global.listaProduse);
        model.addObject("noteProdus", Global.noteProduse);

        return model;
    }
    private String dateChartProduse (List<Produs> produses){
        int counterLow = 0;
        int counterHigh = 0;
        //noteProdus= new HashMap<>();
        for(Produs p: produses) {
            if(Global.reviewProduse==null||Global.reviewProduse.size()==0||!Global.reviewProduse.containsKey(p.getId())){
               Global.reviewProduse.put(p.getId(),reviewService.findByIdProdus(p.getId()));
            }
            float sum = 0;
            if (Global.reviewProduse.get(p.getId()) != null && Global.reviewProduse.get(p.getId()).size()!=0 && !Global.reviewProduse.get(p.getId()).isEmpty() ) {
                for (Review review : Global.reviewProduse.get(p.getId())) {
                    sum += review.getNota();
                }
                double medie = sum / Global.reviewProduse.get(p.getId()).size();
                DecimalFormat df = new DecimalFormat("####0.00");
                if (medie <= 3) counterLow++;
                else counterHigh++;
                Global.noteProduse.put(p.getId(), Double.valueOf(df.format(medie)));
            }
        }

        return counterLow+";"+counterHigh;
    }

    @RequestMapping(value = "/vizualizareReviewProdus/{idProdus}", method = RequestMethod.GET)
    public ModelAndView vizualizareReviewProdus(@PathVariable Long idProdus) throws ParseException {
        ModelAndView model = new ModelAndView("vizualizareReviewProdus");
        //List<Review> reviews = reviewService.findByIdProdus(idProdus);
        if(Global.reviewProduse!=null&& Global.reviewProduse.containsKey(idProdus)){
            model.addObject("listaReviewuri", Global.reviewProduse.get(idProdus));

        }
        else{
            model.addObject("listaReviewuri", reviewService.findByIdProdus(idProdus));
        }
            model.addObject("medieNote", Global.noteProduse.get(idProdus));
            return model;
        }

    @RequestMapping(value = "/stergeProdus/{id}", method = RequestMethod.GET)
    public String stergeProdus(@PathVariable("id") Long id) {
        Produs produs=produsService.findById(id);
        produsService.delete(id);
        /*Global.listaProduse.remove(produs);
        Long categorie_id=produs.getCategorie().getId();
        List<Produs> listaVeche= Global.mapProduseByCategorie.get(categorie_id);
        listaVeche.remove(produs);
        Global.mapProduseByCategorie.replace(produs.getCategorie().getId(),listaVeche);
*/
        return "redirect:/detaliiCategorie/"+produs.getCategorie().getId();
    }

}
