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

  /*  @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("adminForm", new Admin());
        model.addAttribute("add","true");


        return "registration";
    }*/
 /*   @RequestMapping(value = "/registration/{id_admin}", method = RequestMethod.GET)
    public String setariCont(Model model) {
        model.addAttribute("adminForm", Global.admin);
        model.addAttribute("add","false");

        return "registration";
    }*/
  @RequestMapping(value = "/registration/{id}", method = RequestMethod.GET)
  public ModelAndView administrareAdmin(@PathVariable("id") Long id) {
      ModelAndView model = new ModelAndView("registration");
      if(id == 0 ){
          model.addObject("adminForm", new Admin());
          model.addObject("add","true");
      }else{
          Admin o = adminService.findById(id);
          o.setPassword(null);
          model.addObject("adminForm",o);
          model.addObject("add","false");
      }
      return model;
  }

    @RequestMapping(value = "/salvareCont", method = RequestMethod.POST)
    public String adaugareOspatar(@ModelAttribute("adminForm") Admin adminForm, BindingResult bindingResult, Model model) {


        if(adminForm.getId()!=null&& adminForm.getId()!=0){

            Admin old = adminService.findById(adminForm.getId());
            //adminForm.setRol(old.getRol());

            try {
                adminService.update(adminForm);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else{
            adminValidator.validate(adminForm,bindingResult);
            if(bindingResult.hasErrors()){
                return "registration";
            }
            adminForm.setRol(rolService.findRolById(2L));
            try {
                adminService.save(adminForm);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return "redirect:/welcome";
    }
   /* @RequestMapping(value = "/registration/{id}", method = RequestMethod.GET)
    public ModelAndView veziOspatari(@PathVariable("id") Long id) {
        ModelAndView model = new ModelAndView("registration");
        if(id == 0 ){
            model.addObject("adminForm", new Admin());
            model.addObject("add","true");
        }else{
            model.addObject("adminForm", Global.admin);
            model.addObject("add","false");
        }
        return model;
    }
    @RequestMapping(value = "/salvareCont", method = RequestMethod.POST)
    public String registration(@ModelAttribute("adminForm") Admin adminForm, @ModelAttribute("add") String add , BindingResult bindingResult, Model model) {
        adminValidator.validate(adminForm, bindingResult);
        if(add.equals("false")){
                if (bindingResult.hasErrors()) {
                    return "registration";
                }
                adminService.update(adminForm);
        }

        else {
            adminForm.setRol(rolService.findRolById(2L));
            if (bindingResult.hasErrors()) {
                return "registration";
            }
            try {
                adminService.save(adminForm);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }



        //securityService.autologin(adminForm.getUsername(), adminForm.getPasswordConfirm());

        return "redirect:/welcome";
    }*/

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
        Global.admin = adminService.findByUsername(currentPrincipalName);
        Global.rol= Global.admin.getRol();
         /* boolean master=false;
      if(Global.rol.getId()==1){
        master=true;
            }*/

        model.addAttribute("master", Global.rol.getId());

        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        String dateString = format.format( new Date()   );
        if(Global.listaOspatari==null|| Global.listaOspatari.size()==0){
            Global.listaOspatari=ospatarService.findAllNestersi();
        }
        if(Global.listaMese==null|| Global.listaMese.size()==0){
            Global.listaMese=masaService.findAllNesterse();
        }
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
        model.addAttribute("user",Global.admin);
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
