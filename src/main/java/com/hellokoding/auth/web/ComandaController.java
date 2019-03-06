package com.hellokoding.auth.web;

import com.hellokoding.auth.service.CategorieService;
import com.hellokoding.auth.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ComandaController {
    @Autowired
    private CategorieService categorieService;

    @Autowired
    private SecurityService securityService;

  /*  @RequestMapping(value = {"/", "/comanda_finalizata"}, method = RequestMethod.GET)
    public String welcome(Model model) {
        Categorie comandaFinalizata = new Categorie();
        comandaFinalizata.setId(12L);

        categorieService.save(comandaFinalizata);
        return "welcome";
    }*/
}
