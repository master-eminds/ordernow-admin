package com.hellokoding.auth.web;

import com.hellokoding.auth.model.Sugestie;
import com.hellokoding.auth.repository.SugestieRepository;
import com.hellokoding.auth.service.SugestieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class SugestieController {
    @Autowired
    private SugestieService sugestieService;
    @Autowired
    private SugestieRepository sugestieRepository;



    @RequestMapping(value = "/vizualizareSugestii", method = RequestMethod.GET)
    public ModelAndView vizualizareSugestii() {
        ModelAndView model = new ModelAndView("vizualizareSugestii");
        List<Sugestie> listaSugestiiNoi=sugestieService.findAllByStare("necitit");
        List<Sugestie> listaSugestiiVechi=sugestieService.findAllByStare("citit");
        listaSugestiiNoi.sort(Sugestie::compareTo);
        listaSugestiiVechi.sort(Sugestie::compareTo);
        model.addObject("counterSugestiiNoi",listaSugestiiNoi.size());
        model.addObject("listaSugestiiNoi", listaSugestiiNoi);
        model.addObject("listaSugestiiVechi", listaSugestiiVechi);
        model.addObject("numarSugestii",listaSugestiiNoi.size()+listaSugestiiVechi.size() );

        return model;
    }

    @RequestMapping(value = "/vizualizareSugestii/{categorie}", method = RequestMethod.GET)
    public ModelAndView vizualizareSugestiiByCategorie(@PathVariable("categorie") String categorie) {
        ModelAndView model = new ModelAndView("vizualizareSugestii");
        List<Sugestie> listaSugestiiNoi=sugestieService.findAllByStareAndCateg("necitit", categorie);
        List<Sugestie> listaSugestiiVechi=sugestieService.findAllByStareAndCateg("citit",categorie);
        listaSugestiiNoi.sort(Sugestie::compareTo);
        listaSugestiiVechi.sort(Sugestie::compareTo);
        model.addObject("counterSugestiiNoi",listaSugestiiNoi.size());
        model.addObject("listaSugestiiNoi", listaSugestiiNoi);
        model.addObject("listaSugestiiVechi", listaSugestiiVechi);
        model.addObject("numarSugestii",listaSugestiiNoi.size()+listaSugestiiVechi.size() );
        return model;
    }
    @RequestMapping(value = "/vizualizareSugestii/stare/{stare}", method = RequestMethod.GET)
    public ModelAndView vizualizareSugestiiByStare(@PathVariable("stare") String stare) {
        ModelAndView model = new ModelAndView("vizualizareSugestii");
        List<Sugestie> listaSugestii=sugestieService.findAllByStare(stare);
        listaSugestii.sort(Sugestie::compareTo);
        if(stare.equals("necitit")){
            model.addObject("listaSugestiiNoi", listaSugestii);
            model.addObject("counterSugestiiNoi",listaSugestii.size());
            model.addObject("numarSugestii", listaSugestii.size());

        }
        else  if(stare.equals("citit")){
            model.addObject("listaSugestiiVechi", listaSugestii);
            model.addObject("numarSugestii", listaSugestii.size());
        }
        return model;
    }

    @RequestMapping(value = "/marcheazaCaCitite/{iduri}", method = RequestMethod.GET)
    public String marcheazaCaCitite(@PathVariable("iduri") String idSelectate) {

        String[] iduri= idSelectate.split(",");
        for(String id: iduri){
            Long idS= Long.parseLong(id.trim());
            sugestieRepository.updateStareSugestii( "citit",idS);
        }
        return "redirect:/vizualizareSugestii";
    }
    @RequestMapping(value = "/marcheazaCaNecitite/{iduri}", method = RequestMethod.GET)
    public String marcheazaCaNecitite(@PathVariable("iduri") String idSelectate) {

        String[] iduri= idSelectate.split(",");
        for(String id: iduri){
            Long idS= Long.parseLong(id.trim());
            sugestieRepository.updateStareSugestii( "necitit",idS);
        }
        return "redirect:/vizualizareSugestii";
    }
}
