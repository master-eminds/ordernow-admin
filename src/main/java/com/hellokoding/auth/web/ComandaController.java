package com.hellokoding.auth.web;

import com.hellokoding.auth.model.Comanda;
import com.hellokoding.auth.model.ItemComanda;
import com.hellokoding.auth.service.ComandaService;
import com.hellokoding.auth.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;

@Controller
public class ComandaController {
    @Autowired
    private ComandaService comandaService;

    @Autowired
    private SecurityService securityService;

  /*  @RequestMapping(value = {"/", "/comanda_finalizata"}, method = RequestMethod.GET)
    public String welcome(Model model) {
        Categorie comandaFinalizata = new Categorie();
        comandaFinalizata.setId(12L);

        categorieService.save(comandaFinalizata);
        return "welcome";
    }*/

    @RequestMapping(value = "/getProduseComanda/{idComanda}", method = RequestMethod.GET)
    public ModelAndView getComenzi(@PathVariable Long idComanda) throws ParseException {
        ModelAndView model = new ModelAndView("getProduseComanda");

        Comanda comanda= comandaService.findById(idComanda);
        float valoareTotala=0;
        for(ItemComanda item: comanda.getListaItemComanda()){
            valoareTotala+=item.getCantitate()*item.getProdus().getPret();
        }
        model.addObject("valoareTotala",valoareTotala);
        model.addObject("listaProduse",comanda.getListaItemComanda());
        return model;
    }
}
