package com.hellokoding.auth.web;

import com.hellokoding.auth.model.Categorie;
import com.hellokoding.auth.model.Meniu;
import com.hellokoding.auth.model.Produs;
import com.hellokoding.auth.model.Review;
import com.hellokoding.auth.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


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
        HashMap<Long,String> categorii= new HashMap<>();
        for(Categorie categorie: meniu.getCategorii()){
            categorii.put(categorie.getId(),categorie.getDenumire());
        }
        if(produs_id == 0 ){
            model.addObject("produsForm", new Produs());
            model.addObject("add","true");
            model.addObject("categoriiProduse",categorii );
        }else{
            Produs p = produsService.findById(produs_id);
            model.addObject("produsForm",p);
            model.addObject("add","false");
            model.addObject("imageSrc",new String(p.getImagine()));
            model.addObject("categoriiProduse",categorii );
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
        }
        produsService.save(produsForm);
        return "redirect:/detaliiCategorie/"+produsForm.getCategorie().getId();
    }

    @RequestMapping(value = "/detaliiCategorie/{categorie_id}", method = RequestMethod.GET)
    public ModelAndView vizualizareCategorii(@PathVariable ("categorie_id") Long categorie_id) {
        ModelAndView model = new ModelAndView("detaliiCategorie");
        Set<Produs> listaProduse=categorieService.findById(categorie_id).getProduse();
        model.addObject("produse", listaProduse);
        model.addObject("meniu_id", categorieService.findById(categorie_id).getMeniu().getId());

        return model;
    }




    @RequestMapping(value = "/statisticiReviewProduse", method = RequestMethod.GET)
    public ModelAndView statisticiReviewProduse() {
        ModelAndView model = new ModelAndView("statisticiReviewProduse");
        List<Produs> produse= produsService.findAll();
        String date= dateChartProduse(produse);
        model.addObject("dateChartReview", date);
        model.addObject("listaProduse",produse);
        model.addObject("noteProdus", noteProdus);

        return model;
    }
    private String dateChartProduse (List<Produs> produses){
        int counterLow = 0;
        int counterHigh = 0;
        noteProdus= new HashMap<>();
        for(Produs p: produses) {
            reviewsProdus = reviewService.findByIdProdus(p.getId());
            float sum = 0;
            if (reviewsProdus != null && reviewsProdus.size()!=0 && !reviewsProdus.isEmpty() ) {
                for (Review review : reviewsProdus) {
                    sum += review.getNota();
                }
                double medie = sum / reviewsProdus.size();
                DecimalFormat df = new DecimalFormat("####0.00");
                if (medie <= 3) counterLow++;
                else counterHigh++;
                noteProdus.put(p.getId(), Double.valueOf(df.format(medie)));
            }
        }

        return counterLow+";"+counterHigh;
    }

    @RequestMapping(value = "/vizualizareReviewProdus/{idProdus}", method = RequestMethod.GET)
    public ModelAndView vizualizareReviewProdus(@PathVariable Long idProdus) throws ParseException {
        ModelAndView model = new ModelAndView("vizualizareReviewProdus");
        //List<Review> reviews = reviewService.findByIdProdus(idProdus);
            model.addObject("listaReviewuri", reviewService.findByIdProdus(idProdus));
            model.addObject("medieNote", noteProdus.get(idProdus));
            return model;
        }

    @RequestMapping(value = "/stergeProdus/{id}", method = RequestMethod.GET)
    public String stergeProdus(@PathVariable("id") Long id) {
        Produs produs=produsService.findById(id);
        produsService.delete(id);

        return "redirect:/detaliiCategorie/"+produs.getCategorie().getId();
    }

}
