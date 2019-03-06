package com.hellokoding.auth.service;

import com.hellokoding.auth.model.Ospatar;
import com.hellokoding.auth.repository.OspatarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OspatarServiceImpl implements OspatarService {

    @Autowired
    OspatarRepository ospatarRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    public void save(Ospatar ospatar) {
        ospatar.setParola(bCryptPasswordEncoder.encode(ospatar.getParola()));
        ospatarRepository.save(ospatar);
    }

    @Override
    public Ospatar findById(Long id) {

        return ospatarRepository.findOne(id);
    }

    @Override
    public Ospatar findByEmail(String email) {
        return ospatarRepository.findByEmail(email);
    }

    @Override
    public List<Ospatar> findAll() {
        return ospatarRepository.findAll();
    }
}
