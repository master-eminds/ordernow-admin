package com.hellokoding.auth.web;

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
import java.util.*;


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
        if(produsForm.getId()!=null){
            Produs old = produsService.findById(produsForm.getId());
            Global.listaProduse.remove(old);
        }
        Produs produs= produsService.save(produsForm);
        Global.listaProduse.add(produs);
        return "redirect:/detaliiCategorie/"+produsForm.getCategorie().getId();
    }

    @RequestMapping(value = "/detaliiCategorie/{categorie_id}", method = RequestMethod.GET)
    public ModelAndView vizualizareCategorii(@PathVariable ("categorie_id") Long categorie_id) {
        ModelAndView model = new ModelAndView("detaliiCategorie");
        int i=0;
        while(i<Global.listaCategorii.size() && !Global.listaCategorii.get(i).getId().equals(categorie_id)){
            i++;
        }
        Set<Produs> listaProduse=new LinkedHashSet<>();
        Long meniu_id=0L;
        if(i<Global.listaCategorii.size()){
            if(Global.listaCategorii.get(i).getId().equals(categorie_id)){
                listaProduse=Global.listaCategorii.get(i).getProduse();
                meniu_id=Global.listaCategorii.get(i).getMeniu().getId();
            }
        }
        //Set<Produs> listaProduse=categorieService.findById(categorie_id).getProduse();
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
            model.addObject("medieNote", noteProdus.get(idProdus));
            return model;
        }

    @RequestMapping(value = "/stergeProdus/{id}", method = RequestMethod.GET)
    public String stergeProdus(@PathVariable("id") Long id) {
        Produs produs=produsService.findById(id);
        produsService.delete(id);
        Global.listaProduse.remove(produs);
        return "redirect:/detaliiCategorie/"+produs.getCategorie().getId();
    }

}
