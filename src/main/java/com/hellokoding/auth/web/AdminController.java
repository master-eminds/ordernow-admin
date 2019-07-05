package com.hellokoding.auth.web;

import com.hellokoding.auth.model.*;
import com.hellokoding.auth.service.*;
import com.hellokoding.auth.util.DateNecesare;
import com.hellokoding.auth.util.Global;
import com.hellokoding.auth.validator.AdminValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class AdminController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private MesajService mesajService;

    @Autowired
    private RolService rolService;
    @Autowired
    private MasaService masaService;
    @Autowired
    private OspatarService ospatarService;
    @Autowired
    private SecurityService securityService;
    @Autowired
    private ComandaService comandaService;
    @Autowired
    private ProdusService produsService;
    @Autowired
    private ReviewService reviewService;

    private DateNecesare dateNecesare = new DateNecesare();

    @Autowired
    private AdminValidator adminValidator;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("adminForm", new Admin());

        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("adminForm") Admin adminForm, BindingResult bindingResult, Model model) {
        adminValidator.validate(adminForm, bindingResult);
        adminForm.setRol(rolService.findRolById(2L));
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        try {
            adminService.save(adminForm);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //securityService.autologin(adminForm.getUsername(), adminForm.getPasswordConfirm());

        return "redirect:/welcome";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {

        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }

    @RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
    public String welcome(Model model) throws ParseException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        Admin admin = adminService.findByUsername(currentPrincipalName);
        Rol rol= admin.getRol();
        boolean master=false;
        if(rol.getId()==1){
        master=true;
            }

        model.addAttribute("master", master);

        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        String dateString = format.format( new Date()   );
        if(Global.listaOspatari==null|| Global.listaOspatari.size()==0){
            Global.listaOspatari=ospatarService.findAll();
        }
        if(Global.listaMese==null|| Global.listaMese.size()==0){
            Global.listaMese=masaService.findAll();
        }
       // List<Ospatar> ospatari = ospatarService.findAll();
        List<Comanda> comenzi=new ArrayList<>();
        if(Global.listaComenziUltimeleLuni!=null&&Global.listaComenziUltimeleLuni.size()!=0){
            comenzi=Global.listaComenziUltimeleLuni;
        }
        else{
            comenzi=comandaService.findAll();
        }
        List<Mesaj> listaMesajeNecitite= mesajService.findAllByStare("necitit");
        List<Mesaj> listaPrimeleMesajeNecitite= new ArrayList<>();
        if(listaMesajeNecitite.size()>3){
            listaPrimeleMesajeNecitite.add(listaMesajeNecitite.get(0));
            listaPrimeleMesajeNecitite.add(listaMesajeNecitite.get(1));
            listaPrimeleMesajeNecitite.add(listaMesajeNecitite.get(2));
            model.addAttribute("listaMesajeNecitite",listaPrimeleMesajeNecitite);
        }
        else {
            model.addAttribute("listaMesajeNecitite",listaMesajeNecitite);

        }
        model.addAttribute("counterMesajeNecitite",listaMesajeNecitite.size());

        //alte date necesare
        model.addAttribute("listaMese", Global.listaMese);
        model.addAttribute("listaOspatari",Global.listaOspatari);
        model.addAttribute("user",admin);
        model.addAttribute("data",dateString);

        if(Global.listaComenziUltimeleLuni==null|| Global.listaComenziUltimeleLuni.size()==0){
            Global.listaComenziUltimeleLuni=DateNecesare.listaComenziUltimeleLuni(comenzi,4);
        }
        if(Global.listaComenziUltimaSaptamana==null|| Global.listaComenziUltimaSaptamana.size()==0){
            Global.listaComenziUltimaSaptamana=DateNecesare.listaComenziUltimaSaptamana(comenzi);
        }
        if(Global.valoareTotala==null|| Global.valoareTotala==0){
            Global.valoareTotala=DateNecesare.calculeazaValoareTotalaIncasata(comenzi);
        }
        model.addAttribute("counterThisWeek", Global.listaComenziUltimaSaptamana.size());
        model.addAttribute("membriOnline",DateNecesare.calculareNrOspatariOnline(Global.listaOspatari));
        model.addAttribute("comenziUltimeleLuni",Global.listaComenziUltimeleLuni.size());
        model.addAttribute("incasari",Global.valoareTotala);

        return "welcome";
    }

    private  HashMap<Long, Integer> listaMeses (List<Comanda> comenzi){
       HashMap<Long, Integer> comenziMese= new HashMap<>();

       //List<Masa> mese= masaService.findAll();
       for(Masa masa: Global.listaMese){
           if(!comenziMese.containsKey(masa.getId())){
               comenziMese.put(masa.getId(),0);
           }
       }
        for(Comanda comanda:comenzi){
            Long idMasa=comanda.getMasa().getId();
            if (comenziMese.containsKey(idMasa)){
                comenziMese.replace(idMasa,comenziMese.get(idMasa)+1);
            }

        }
        return comenziMese;
    }

    @RequestMapping(value = "/vizualizareComenzi/{nrMasa}", method = RequestMethod.GET)
    public ModelAndView getComenzi(@PathVariable Long nrMasa) throws ParseException {
        ModelAndView model = new ModelAndView("vizualizareComenzi");
        Masa masa= masaService.findById(nrMasa);
        Set<Comanda> comenzi= masa.getComenzi();
        List<Comanda> listaComenzi = new ArrayList<>(comenzi);
        listaComenzi.sort(Comanda::compareTo);
        for(Comanda comanda:comenzi ){
            model.addObject("valoareTotalaComanda",comanda.getValoare());
        }
        model.addObject("listaComenzi",listaComenzi);
        return model;
    }


}
