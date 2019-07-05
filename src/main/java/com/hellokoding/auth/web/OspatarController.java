package com.hellokoding.auth.web;

import com.hellokoding.auth.model.Ospatar;
import com.hellokoding.auth.model.Review;
import com.hellokoding.auth.service.OspatarService;
import com.hellokoding.auth.service.ReviewService;
import com.hellokoding.auth.service.SecurityService;
import com.hellokoding.auth.util.Global;
import com.hellokoding.auth.validator.OspatarValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class OspatarController {
    @Autowired
    private SecurityService securityService;
    @Autowired
    private OspatarValidator ospatarValidator;
    @Autowired
    private OspatarService ospatarService;
    @Autowired
    private ReviewService reviewService;
    private Map<Long,Double> noteOspatar;
    private List<Review> reviewsOspatar;

    @RequestMapping(value = "/administrareOspatari/{id}", method = RequestMethod.GET)
    public ModelAndView veziOspatari(@PathVariable("id") Long id) {
        ModelAndView model = new ModelAndView("administrareOspatari");
        if(id == 0 ){
            model.addObject("ospatarForm", new Ospatar());
            model.addObject("add","true");
        }else{
            Ospatar o = ospatarService.findById(id);
            o.setParola(null);
            model.addObject("ospatarForm",o);
            model.addObject("add","false");
        }
        return model;
    }

    @RequestMapping(value = "/salvareOspatar", method = RequestMethod.POST)
    public String adaugareOspatar(@ModelAttribute("ospatarForm") Ospatar ospatarForm, BindingResult bindingResult, Model model) {

        ospatarValidator.validate(ospatarForm,bindingResult);
         if(bindingResult.hasErrors()){
            return "administrareOspatari";
        }
        if(ospatarForm.getId()!=null&& ospatarForm.getId()!=null){
            Ospatar old = ospatarService.findById(ospatarForm.getId());
            ospatarForm.setParola(old.getParola());
            Global.listaOspatari.remove(old);
        }
        Ospatar ospatar=ospatarService.save(ospatarForm);
        Global.listaOspatari.add(ospatar);
        return "redirect:/welcome";
    }


    @RequestMapping(value = "/statisticiReviewOspatari", method = RequestMethod.GET)
    public ModelAndView statisticiReviewOspatar() {
        ModelAndView model = new ModelAndView("statisticiReviewOspatari");
        List<Ospatar> ospatari= ospatarService.findAll();
        ospatari.sort(Ospatar::compareTo);
        String date= dateChartOspatari(ospatari);
        model.addObject("dateChartReview", date);
        model.addObject("listaOspatari",ospatari);
        model.addObject("noteOspatar", noteOspatar);

        return model;
    }
    public  Map<Long, Double> dateReviewOspatari(List<Ospatar> ospatari){

        Map<Long, Double>  noteOspatar= new HashMap<>();
        for(Ospatar o: ospatari) {
            List<Review> reviewsOspatar = reviewService.findByIdOspatar(o.getId());
            float sum = 0;
            if (reviewsOspatar != null && reviewsOspatar.size()!=0 && !reviewsOspatar.isEmpty() ) {
                for (Review review : reviewsOspatar) {
                    sum += review.getNota();
                }
                double medie = sum / reviewsOspatar.size();
                DecimalFormat df = new DecimalFormat("####0.00");

                noteOspatar.put(o.getId(), Double.valueOf(df.format(medie)));
            }
        }

        return noteOspatar;
    }

    private String dateChartOspatari(List<Ospatar> ospatari){
        noteOspatar= dateReviewOspatari(ospatari);

        StringBuilder stringId=new StringBuilder();
        StringBuilder stringNote=new StringBuilder();
        for(Ospatar o: ospatari){
            if(noteOspatar.containsKey(o.getId())){
                stringId.append(o.getNume()).append("-");
                stringNote.append(noteOspatar.get(o.getId())).append("-");
            }

        }

        return stringId.toString().substring(0,stringId.length()-1).concat(";").concat(stringNote.toString().substring(0,stringNote.length()-1));

    }

    @RequestMapping(value = "/vizualizareReviewOspatar/{idOspatar}", method = RequestMethod.GET)
    public ModelAndView vizualizareReviewOspatar(@PathVariable Long idOspatar) throws ParseException {
        ModelAndView model = new ModelAndView("vizualizareReviewOspatar");
        //List<Review> reviews = reviewService.findByIdProdus(idProdus);
        model.addObject("listaReviewuri", reviewService.findByIdOspatar(idOspatar));
        model.addObject("medieNote", noteOspatar.get(idOspatar));
        return model;
    }
}
