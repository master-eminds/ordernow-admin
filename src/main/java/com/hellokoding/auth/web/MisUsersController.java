package com.hellokoding.auth.web;

import com.hellokoding.auth.model.MisUsers;
import com.hellokoding.auth.service.MisUsersService;
import com.hellokoding.auth.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MisUsersController {
    @Autowired
    private MisUsersService misUsersService;

    @Autowired
    private SecurityService securityService;

    @RequestMapping(value = "/adaugaUser", method = RequestMethod.POST)
    public void adaugaUser(@PathVariable MisUsers user){
        misUsersService.save(user);
    }

    @RequestMapping(value = "/login/{email}/{parola}", method = RequestMethod.GET)
    public MisUsers adaugaUser(@PathVariable String email,@PathVariable String parola){
        return misUsersService.findByEmailPass(email,parola);
    }
}
