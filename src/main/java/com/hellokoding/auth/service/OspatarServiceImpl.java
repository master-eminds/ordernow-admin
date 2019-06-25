package com.hellokoding.auth.service;

import com.hellokoding.auth.model.Ospatar;
import com.hellokoding.auth.repository.OspatarRepository;
import com.hellokoding.auth.util.Global;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OspatarServiceImpl implements OspatarService {

    @Autowired
    OspatarRepository ospatarRepository;

    @Override
    public void save(Ospatar ospatar) {

        try {
            ospatar.setParola(Global.criptare(ospatar.getParola(),Global.cheieCriptare));
        } catch (Exception e) {
            e.printStackTrace();
        }
        ospatar.setStatus("offline");
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
