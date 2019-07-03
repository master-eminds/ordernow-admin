package com.hellokoding.auth.service;

import com.hellokoding.auth.model.Rol;
import com.hellokoding.auth.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RolServiceImpl implements RolService {

    @Autowired
    RolRepository rolRepository;



    @Override
    public Rol findRolById(Long id) {
        return rolRepository.findOne(id);
    }
}
