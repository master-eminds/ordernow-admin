package com.hellokoding.auth.service;

import com.hellokoding.auth.model.Comanda;
import com.hellokoding.auth.repository.ComandaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ComandaServiceImpl implements ComandaService {

    @Autowired
    ComandaRepository comandaRepository;


    @Override
    public List<Comanda> findAll() {
        return comandaRepository.findAll();
    }

    @Override
    public Comanda findById(Long id) {
        return comandaRepository.findOne(id);
    }
}
