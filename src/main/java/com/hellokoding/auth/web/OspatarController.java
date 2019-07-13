package com.hellokoding.auth.web;

import com.hellokoding.auth.model.Ospatar;
import com.hellokoding.auth.model.Review;
import com.hellokoding.auth.repository.OspatarRepository;
import com.hellokoding.auth.service.OspatarService;
import com.hellokoding.auth.service.ReviewService;
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
    private OspatarValidator ospatarValidator;
    @Autowired
    private OspatarRepository ospatarRepository;

    @Autowired
    private OspatarService ospatarService;
    @Autowired
    private ReviewService reviewService;

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

    @RequestMapping(value = "/stergeOspatar/{id}", method = RequestMethod.GET)

    public String stergeMeniu(@PathVariable("id") Long id) {
        ospatarRepository.deleteOspatar(id);
        int sters=0;
        for(int i=0;i< Global.listaOspatari.size() && sters==0;i++){
            Ospatar ospatar= Global.listaOspatari.get(i);
            if(ospatar.getId().equals(id)){
                sters=1;
                Global.listaOspatari.remove(i);
            }
        }
        return "redirect:/welcome";
    }
    @RequestMapping(value = "/statisticiReviewOspatari", method = RequestMethod.GET)
    public ModelAndView statisticiReviewOspatar() {
        ModelAndView model = new ModelAndView("statisticiReviewOspatari");
        List<Ospatar> ospatari= Global.listaOspatari;
                //ospatarService.findAll();
        ospatari.sort(Ospatar::compareTo);
        if(Global.dateChartOspatari==null||Global.dateChartOspatari.isEmpty()||Global.dateChartOspatari.trim().length()==0){
            if(Global.noteOspatari==null||Global.noteOspatari.size()==0){
                Global.noteOspatari=dateReviewOspatari(ospatari);
            }
            Global.dateChartOspatari=dateChartOspatari(ospatari);
        }
        model.addObject("dateChartReview", Global.dateChartOspatari);
        model.addObject("listaOspatari",ospatari);
        model.addObject("noteOspatar", Global.noteOspatari);

        return model;
    }
    public  Map<Long, Double> dateReviewOspatari(List<Ospatar> ospatari){

        Map<Long, Double>  noteOspatar= new HashMap<>();
        for(Ospatar o: ospatari) {
            if(Global.reviewOspatari==null||Global.reviewOspatari.size()==0||!Global.reviewOspatari.containsKey(o.getId())){
                Global.reviewOspatari.put(o.getId(),reviewService.findByIdOspatar(o.getId()));
            }
            float sum = 0;
            if (Global.reviewOspatari.get(o.getId()) != null && Global.reviewOspatari.get(o.getId()).size()!=0 && !Global.reviewOspatari.get(o.getId()).isEmpty() ) {
                for (Review review : Global.reviewOspatari.get(o.getId())) {
                    sum += review.getNota();
                }
                double medie = sum / Global.reviewOspatari.get(o.getId()).size();
                DecimalFormat df = new DecimalFormat("####0.00");

                noteOspatar.put(o.getId(), Double.valueOf(df.format(medie)));
            }
        }

        return noteOspatar;
    }

    private String dateChartOspatari(List<Ospatar> ospatari){

        StringBuilder stringId=new StringBuilder();
        StringBuilder stringNote=new StringBuilder();
        for(Ospatar o: ospatari){
            if(Global.noteOspatari.containsKey(o.getId())){
                stringId.append(o.getNume()).append("-");
                stringNote.append(Global.noteOspatari.get(o.getId())).append("-");
            }

        }

        return stringId.toString().substring(0,stringId.length()-1).concat(";").concat(stringNote.toString().substring(0,stringNote.length()-1));

    }

    @RequestMapping(value = "/vizualizareReviewOspatar/{idOspatar}", method = RequestMethod.GET)
    public ModelAndView vizualizareReviewOspatar(@PathVariable Long idOspatar) throws ParseException {
        ModelAndView model = new ModelAndView("vizualizareReviewOspatar");
        model.addObject("listaReviewuri", Global.reviewOspatari.get(idOspatar));
        model.addObject("medieNote", Global.noteOspatari.get(idOspatar));
        return model;
    }
}
