package com.hellokoding.auth.service;

import com.hellokoding.auth.model.Admin;
import com.hellokoding.auth.repository.AdminRepository;
import com.hellokoding.auth.repository.RoleRepository;
import com.hellokoding.auth.util.Global;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    public void save(Admin admin) {
        String password= admin.getPassword();
        admin.setPassword(bCryptPasswordEncoder.encode(password));
        try {
            admin.setParolaAndroid(Global.criptare(password,Global.cheieCriptare));
        } catch (Exception e) {
            e.printStackTrace();
        }
        //admin.setRoles(new HashSet<>(roleRepository.findAll()));
        adminRepository.save(admin);
    }

    @Override
    public void update(Admin admin) {

            if(admin.getId()!=null){
                adminRepository.delete(admin.getId());
            }
        String password= admin.getPassword();
        admin.setPassword(bCryptPasswordEncoder.encode(password));
        try {
            admin.setParolaAndroid(Global.criptare(password,Global.cheieCriptare));
        } catch (Exception e) {
            e.printStackTrace();
        }
        adminRepository.save(admin);
    }

    @Override
    public Admin findByUsername(String username) {
        return adminRepository.findByUsername(username);
    }

    @Override
    public Admin findById(Long id) {
        return adminRepository.findOne(id);
    }


}
