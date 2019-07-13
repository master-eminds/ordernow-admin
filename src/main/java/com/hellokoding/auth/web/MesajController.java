package com.hellokoding.auth.web;

import com.hellokoding.auth.model.Mesaj;
import com.hellokoding.auth.repository.MesajRepository;
import com.hellokoding.auth.service.MesajService;
import com.hellokoding.auth.validator.MesajValidator;
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
public class MesajController {
    @Autowired
    private MesajService mesajService;
    @Autowired
    private MesajRepository mesajRepository;

    @Autowired
    private MesajValidator mesajValidator;
    @RequestMapping(value = "/vizualizareMesaje", method = RequestMethod.GET)
    public ModelAndView vizualizareMesaje() {
        ModelAndView model = new ModelAndView("vizualizareMesaje");
        List<Mesaj> listaMesajeNecitite=mesajService.findAllByStare("necitit");
        List<Mesaj> listaMesajeCitite=mesajService.findAllByStare("citit");

        listaMesajeCitite.sort(Mesaj::compareTo);
        listaMesajeNecitite.sort(Mesaj::compareTo);
        model.addObject("listaMesajeNecitite", listaMesajeNecitite);
        model.addObject("listaMesajeCitite", listaMesajeCitite);
        return model;
    }
    @RequestMapping(value = "/raspunsMesaj/{idMesaj}", method = RequestMethod.GET)
    public ModelAndView incarcaConversatie(@PathVariable("idMesaj") Long idMesaj) throws UnsupportedEncodingException, SQLException {
        ModelAndView model = new ModelAndView("raspunsMesaj");

        Mesaj mesaj= mesajService.findById(idMesaj);
        mesajRepository.updateStareMesaj("citit",idMesaj);
        boolean dejaRaspuns= false;
        if(mesaj!=null && mesaj.getContinutRaspuns()!=null&&!mesaj.getContinutRaspuns().isEmpty()){
            dejaRaspuns=true;
        }
        model.addObject("dejaRaspuns",dejaRaspuns);
        model.addObject("mesaj",mesaj);
        model.addObject("raspunsForm", mesaj);
        return model;
    }
    @RequestMapping(value = "/trimiteRaspuns/{idMesaj}", method = RequestMethod.POST)
    public String adaugareMeniu(@ModelAttribute("raspunsForm") Mesaj raspunsForm, @PathVariable("idMesaj") Long idMesaj, BindingResult bindingResult) throws UnsupportedEncodingException, SQLException {
        mesajValidator.validate(raspunsForm,bindingResult);

        if(bindingResult.hasErrors()){
            return "redirect:/raspunsMesaj/"+idMesaj;
        }
        mesajRepository.updateMesaj( raspunsForm.getContinutRaspuns(),idMesaj);
        return "redirect:/vizualizareMesaje";
    }

}
