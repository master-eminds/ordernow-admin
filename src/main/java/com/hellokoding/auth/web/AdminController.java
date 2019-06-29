package com.hellokoding.auth.web;

import com.hellokoding.auth.model.*;
import com.hellokoding.auth.service.*;
import com.hellokoding.auth.util.DateNecesare;
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

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        try {
            adminService.save(adminForm);
        } catch (Exception e) {
            e.printStackTrace();
        }

        securityService.autologin(adminForm.getUsername(), adminForm.getPasswordConfirm());

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
        String rezultatReview= dateChartProduse(produsService.findAll());
        model.addAttribute("dateChartReview",rezultatReview);

        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        String dateString = format.format( new Date()   );
        List<Ospatar> ospatari = ospatarService.findAll();
        List<Comanda> comenzi = comandaService.findAll();
        HashMap<Long,Integer> meseList = listaMeses(comenzi);


        //alte date necesare
        DateNecesare dateNecesareList = dateNecesare(ospatari,comenzi);
        model.addAttribute("listaMese",meseList);
        model.addAttribute("listaOspatari",ospatari);
        model.addAttribute("user",admin);
        model.addAttribute("data",dateString);
        model.addAttribute("counterThisWeek",dateNecesareList.getCounterComenziThisWeek());
        model.addAttribute("listaThisWeek",dateNecesareList.getNrComenziThisWeek());

        //grafice
        model.addAttribute("membriOnline",dateNecesareList.getNrOspatariOnline());
        model.addAttribute("comenziVandute",dateNecesareList.getNrComenziVandute());
        model.addAttribute("incasari",dateNecesare.getTotalIncasari().toString());



        Map<String,Integer> comenziPeLuna=dateNecesareList.getNrComenziOnMonth();

        StringBuilder stringLuni=new StringBuilder();
        StringBuilder stringNumarcomenzi=new StringBuilder();
        String[] luni={"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
            for(String luna:luni){
                if(comenziPeLuna.containsKey(luna)){
                    stringLuni.append(luna).append(",");
                    stringNumarcomenzi.append(comenziPeLuna.get(luna)).append(",");
                }
            }

        String dateChart2= stringLuni.toString().substring(0,stringLuni.length()-1).concat(";").concat(stringNumarcomenzi.toString().substring(0,stringNumarcomenzi.length()-1));
        model.addAttribute("dateChart2",dateChart2);
        Map<String,Integer> comenziPeZile=dateNecesareList.getNrComenziThisWeek();

        StringBuilder stringZile=new StringBuilder();
        StringBuilder stringNumarcomenziZile=new StringBuilder();
        Calendar cal= Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR,-7);

        for(int i=0;i<7;i++){
            String zi= format.format(cal.getTime());
                if(comenziPeZile.containsKey(zi)){
                    stringZile.append(zi.substring(0,5)).append(",");
                    stringNumarcomenziZile.append(comenziPeZile.get(zi)).append(",");
                }
                cal.add(Calendar.DAY_OF_YEAR,+1);
            }

        String dateChart3= stringZile.toString().substring(0,stringZile.length()-1).concat(";").concat(stringNumarcomenziZile.toString().substring(0,stringNumarcomenziZile.length()-1));
        model.addAttribute("dateChart3",dateChart3);
        return "welcome";
    }



    private String dateChartProduse (List<Produs> produses){
        int counterLow = 0;
        int counterHigh = 0;
        List<Review> reviewsTotale = new ArrayList<>();

        for(Produs p: produses) {
            reviewsTotale = reviewService.findByIdProdus(p.getId());
            float sum = 0;
            if (reviewsTotale != null && reviewsTotale.size()!=0 && !reviewsTotale.isEmpty() ) {
                for (Review review : reviewsTotale) {
                    sum += review.getNota();
                }
                double medie = sum / reviewsTotale.size();
                if (medie < 3) counterLow++;
                else counterHigh++;
            }
        }

        return counterLow+";"+counterHigh;
    }
    private DateNecesare dateNecesare(List<Ospatar> listaOspatari,List<Comanda> comandas) throws ParseException {
        DateNecesare date = dateNecesare.dateNecesare(listaOspatari,comandas);
        return date;
    }
    private  HashMap<Long, Integer> listaMeses (List<Comanda> comenzi){
       HashMap<Long, Integer> comenziMese= new HashMap<>();

       List<Masa> mese= masaService.findAll();
       for(Masa masa: mese){
           if(!comenziMese.containsKey(masa.getId())){
               comenziMese.put(masa.getId(),0);
           }
       }
        for(Comanda comanda:comenzi){
            Long idMasa=comanda.getMasa().getId();
            if(comenziMese.containsKey(idMasa)){
                comenziMese.replace(idMasa,comenziMese.get(idMasa)+1);
            }

        }
        return comenziMese;
    }

    @RequestMapping(value = "/vizualizareComenzi/{nrMasa}", method = RequestMethod.GET)
    public ModelAndView getComenzi(@PathVariable Long nrMasa) throws ParseException {
        ModelAndView model = new ModelAndView("vizualizareComenzi");
        Masa masa= masaService.findById(nrMasa);
        for(Comanda comanda: masa.getComenzi()){
            float valoareTotala=0;
            for(ItemComanda item: comanda.getListaItemComanda()){
                valoareTotala+=item.getCantitate()*item.getProdus().getPret();
            }
            model.addObject("valoareTotalaComanda",valoareTotala);
        }
        model.addObject("listaComenzi",masa.getComenzi());
        return model;
    }


}
