package com.hellokoding.auth.service;

import com.hellokoding.auth.model.Produs;
import com.hellokoding.auth.repository.ProdusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ProdusServiceImpl implements ProdusService {

    @Autowired
    ProdusRepository produsRepository;

    @Override
    public void save(Produs produs) {
        produsRepository.save(produs);
    }

    @Override
    public Produs findById(Long id) {
        return produsRepository.findOne(id);
    }

}
