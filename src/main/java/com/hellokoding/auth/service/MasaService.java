package com.hellokoding.auth.service;

import com.hellokoding.auth.model.Masa;

import java.util.List;

public interface MasaService {

    void save(Masa masa);

    Masa findById(Long id);
    List<Masa> findAll();
}
