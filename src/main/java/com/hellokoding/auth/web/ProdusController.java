package com.hellokoding.auth.web;

import com.hellokoding.auth.model.Categorie;
import com.hellokoding.auth.model.Meniu;
import com.hellokoding.auth.model.Produs;
import com.hellokoding.auth.model.Review;
import com.hellokoding.auth.repository.ProdusRepository;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Controller
public class ProdusController {
    @Autowired
    private ProdusService produsService;
    @Autowired
    private ProdusRepository produsRepository;
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




    @RequestMapping(value = "/administrareProdus/{produs_id}/{categ_id}/{meniu_id}", method = RequestMethod.GET)
    public ModelAndView registration(@PathVariable("produs_id") Long produs_id,@PathVariable("categ_id") Long categ_id, @PathVariable("meniu_id") Long meniu_id) {
        ModelAndView model = new ModelAndView("administrareProdus");
        Meniu meniu= meniuService.findById(meniu_id);

        if(produs_id == 0 ){
            Categorie c= categorieService.findById(categ_id);

            model.addObject("meniu",meniu);
            model.addObject("produsForm", new Produs(c));
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

        produsForm.setMeniu_id(meniu_id);

        if(bindingResult.hasErrors()){
            return "administrareProdus";
        }

        Produs produs=produsService.save(produsForm);

        Long categorie= produsForm.getCategorie().getId();
        //la modificare produs
        if(produsForm.getId()!=0){
            if (Global.mapProduseByCategorie.get(categorie)!=null&&Global.mapProduseByCategorie.get(categorie).size()!=0){
                List<Produs> listaVeche= Global.mapProduseByCategorie.get(categorie);

                int sters=0;
                for(int i=0;i<listaVeche.size() && sters==0;i++){
                    Produs produs1=listaVeche.get(i);
                    if(produs1.getId().equals(produsForm.getId())){
                        listaVeche.remove(i);
                        sters=1;
                    }
                }
                listaVeche.add(produs);
                Global.mapProduseByCategorie.replace(categorie,listaVeche );
            }
        }
        // la adaugare produs
        else {
            //daca exista deja macar un produs in categorie, stergem produsul vechi din lista si o adaugam pe cea nou
            if (Global.mapProduseByCategorie.get(categorie)!=null&&Global.mapProduseByCategorie.get(categorie).size()!=0){
                List<Produs> listaVeche= Global.mapProduseByCategorie.get(categorie);

                int sters=0;
                for(int i=0;i<listaVeche.size() && sters==0;i++){
                    Produs produs1=listaVeche.get(i);
                    if(produs1.getId().equals(produsForm.getId())){
                        listaVeche.remove(i);
                        sters=1;
                    }
                }
                listaVeche.add(produs);
                Global.mapProduseByCategorie.replace(categorie,listaVeche );
            }
            // daca nu exista nicio categorie in meniu, adaugam noi o lista cu categ noua
            else {
                List<Produs> listaVeche = new ArrayList<>();
                listaVeche.add(produs);
                Global.mapProduseByCategorie.put(categorie, listaVeche);
            }
        }
        return "redirect:/detaliiCategorie/"+produsForm.getCategorie().getId()+"/"+meniu_id;
    }

    @RequestMapping(value = "/detaliiCategorie/{categorie_id}/{meniu_id}", method = RequestMethod.GET)
    public ModelAndView vizualizareProduse(@PathVariable ("categorie_id") Long categorie_id, @PathVariable("meniu_id") Long meniu_id) {
        ModelAndView model = new ModelAndView("detaliiCategorie");


        if(Global.mapProduseByCategorie.get(categorie_id)==null||Global.mapProduseByCategorie.get(categorie_id).size()==0) {
            Global.mapProduseByCategorie.put(categorie_id, produsService.findAllByCategorie(categorie_id));
        }

        model.addObject("produse", Global.mapProduseByCategorie.get(categorie_id));
        model.addObject("meniu_id",meniu_id);
        model.addObject("categorie_id",categorie_id);

        return model;
    }
    @RequestMapping(value = "/detaliiCategorie/{categorie_id}/{meniu_id}/{vizibilitate}", method = RequestMethod.GET)
    public ModelAndView vizualizareProduseByVizibil(@PathVariable ("categorie_id") Long categorie_id, @PathVariable("meniu_id") Long meniu_id,@PathVariable("vizibilitate") String vizibilitate){
        ModelAndView model = new ModelAndView("detaliiCategorie");


        List<Produs> listaProduse= produsService.findAllByVizibilitate(categorie_id,vizibilitate);
        model.addObject("produse", listaProduse);
        model.addObject("meniu_id",meniu_id);

        return model;
    }




    @RequestMapping(value = "/statisticiReviewProduse", method = RequestMethod.GET)
    public ModelAndView statisticiReviewProduse() {
        ModelAndView model = new ModelAndView("statisticiReviewProduse");
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

    @RequestMapping(value = "/stergeProdus/{id}/{categ}/{meniu_id}", method = RequestMethod.GET)
    public String stergeProdus(@PathVariable("id") Long id, @PathVariable("categ") Long id_categ, @PathVariable("meniu_id") Long meniu_id) {

        produsRepository.deleteProdus(1,id);
        List<Produs> listaVeche= Global.mapProduseByCategorie.get(id_categ);
        int sters=0;
        for(int i=0;i<listaVeche.size() && sters==0;i++){
            Produs produs1=listaVeche.get(i);
            if(produs1.getId().equals(id)){
                listaVeche.remove(i);
                sters=1;
            }
        }

        Global.mapProduseByCategorie.replace(id_categ,listaVeche);
        return "redirect:/detaliiCategorie/"+id_categ+"/"+meniu_id;
    }

}
