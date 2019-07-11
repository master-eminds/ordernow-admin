package com.hellokoding.auth.service;

import com.hellokoding.auth.model.Masa;
import com.hellokoding.auth.repository.MasaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class MasaServiceImpl implements MasaService {

    @Autowired
    MasaRepository masaRepository;
    @PersistenceContext
    EntityManager em;

    @Override
    public Masa save(Masa masa) {
        return masaRepository.saveAndFlush(masa);
    }

    @Override
    public Masa findById(Long id) {
        return masaRepository.findOne(id);
    }

    @Override
    public List<Masa> findAll() {
        return masaRepository.findAll();
    }

    @Override
    public List<Masa> findAllNesterse() {
        List mese= em.createNativeQuery(
                "select * from mese where sters=0", Masa.class)
                .getResultList();
        return mese;
    }




}
