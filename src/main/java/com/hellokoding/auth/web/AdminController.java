package com.hellokoding.auth.web;

import com.hellokoding.auth.model.*;
import com.hellokoding.auth.service.*;
import com.hellokoding.auth.util.*;
import com.hellokoding.auth.validator.AdminValidator;
import com.hellokoding.auth.validator.ModificareAdminValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
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
    private CategorieService categorieService;
    @Autowired
    private ComandaService comandaService;
    @Autowired
    private SugestieService sugestieService;
    @Autowired
    private ProdusService produsService;
    @Autowired
    private MeniuService meniuService;
    @Autowired
    private ReviewService reviewService;

    private DateNecesare dateNecesare = new DateNecesare();

    @Autowired
    private AdminValidator adminValidator;
    @Autowired
    private ModificareAdminValidator modificareAdminValidator;


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
            modificareAdminValidator.validate(adminForm,bindingResult);
            if(bindingResult.hasErrors()){
                return "registration";
            }
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
        if(Global.listaMeniuri==null || Global.listaMeniuri.size()==0){
            incarcaListaMeniuri();
        }
        if(Global.listaProduse==null || Global.listaProduse.size()==0){
            incarcaListaProduse();
        }
        if(Global.listaComenzi==null || Global.listaComenzi.size()==0){
            incarcaListaComenzi();
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        Global.admin = adminService.findByUsername(currentPrincipalName);
        Global.rol= Global.admin.getRol();
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
        if(Global.listaComenzi!=null&&Global.listaComenzi.size()!=0){
            comenzi=Global.listaComenzi;
        }
        else{
            comenzi=comandaService.findAll();
        }
        List<Mesaj> listaPrimeleMesajeNecitite= mesajService.findFirstByStare("necitit",3);
        model.addAttribute("listaMesajeNecitite",listaPrimeleMesajeNecitite);

        List<Sugestie> listaPrimeleSugestiiNecitite= sugestieService.findFirstByStare("necitit",3);
        model.addAttribute("listaSugestiiNoi",listaPrimeleSugestiiNecitite);

        model.addAttribute("counterMesajeNecitite",mesajService.findCounterByStare("necitit"));
        model.addAttribute("counterSugestiiNecitite",sugestieService.findCounterByStare("necitit"));

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



    private void incarcaListaComenzi() {
        ThreadComenziTotale util= new ThreadComenziTotale();
        util.setSomeCondition(true);
        util.setComandaService(comandaService);
        util.run();
    }
    private void incarcaListaMese() {
        ThreadMese util= new ThreadMese();
        util.setSomeCondition(true);
        util.setMasaService(masaService);
        util.run();
    }
    private void incarcaListaProduse() {

        ThreadStatisticiProduse util= new ThreadStatisticiProduse();
        util.setSomeCondition(true);
        util.setProdusService(produsService);
        util.setReviewService(reviewService);
        util.run();
       }
    private void incarcaListaMeniuri() {
        ThreadMeniuriSiCategorii threadMeniuriSiCategorii= new ThreadMeniuriSiCategorii();
        threadMeniuriSiCategorii.setCategorieService(categorieService);
        threadMeniuriSiCategorii.setMeniuService(meniuService);
        threadMeniuriSiCategorii.setProdusService(produsService);
        threadMeniuriSiCategorii.setSomeCondition(true);
        threadMeniuriSiCategorii.run();
    }

    @Scheduled(fixedRate = 30000)
    public void incarcareProduse() {
        incarcaListaProduse();
    }
    @Scheduled(fixedRate = 60000)
    public void incarcareComenzi() {
        incarcaListaComenzi();
    }
    @Scheduled(fixedRate = 30000)
    public void recalculareValoareTotala() {
        ThreadValoareTotala util= new ThreadValoareTotala();
        util.setSomeCondition(true);
        util.run();
    }
    @Scheduled(fixedDelay = 10000, initialDelay = 10000)
    public void reincarcaListaMese() {
        incarcaListaMese();
    }

    @RequestMapping(value = "/vizualizareComenzi/{nrMasa}", method = RequestMethod.GET)
    public ModelAndView getComenzi(@PathVariable Long nrMasa)  {
        ModelAndView model = new ModelAndView("vizualizareComenzi");
        Masa masa= masaService.findById(nrMasa);
        List<Comanda> listaComenzi = masa.getComenzi();
        listaComenzi.sort(Comanda::compareTo);

        model.addObject("listaComenzi",listaComenzi);
        return model;
    }


}
