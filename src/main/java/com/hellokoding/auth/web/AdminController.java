package com.hellokoding.auth.web;

import com.hellokoding.auth.model.Admin;
import com.hellokoding.auth.model.ListaMese;
import com.hellokoding.auth.model.Masa;
import com.hellokoding.auth.model.Ospatar;
import com.hellokoding.auth.service.AdminService;
import com.hellokoding.auth.service.MasaService;
import com.hellokoding.auth.service.OspatarService;
import com.hellokoding.auth.service.SecurityService;
import com.hellokoding.auth.validator.AdminValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

        adminService.save(adminForm);

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
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        String dateString = format.format( new Date()   );
        Date   date       = format.parse (dateString );
        List<Masa> listaMese = masaService.findAll();
        List<ListaMese> meseList = listaMeses(listaMese);
        List<Ospatar> ospatarList = ospatarService.findAll();
        model.addAttribute("listaMese",meseList);
        model.addAttribute("listaOspatari",ospatarList);
        model.addAttribute("user",admin);
        model.addAttribute("data",date);
        return "welcome";
    }

    private List<ListaMese> listaMeses (List<Masa> list){
        List<ListaMese> listaMeses = new ArrayList<>();
        for(Masa m:list){
            ListaMese l = new ListaMese();
            l.setIdMasa(m.getId());
            l.setNumarComenzi(m.getComanda().size());
            listaMeses.add(l);
        }
        return listaMeses;
    }
}
