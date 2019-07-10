package com.hellokoding.auth.service;

import com.hellokoding.auth.model.Meniu;

import java.util.List;

public interface MeniuService {

    Meniu save(Meniu meniu);

    Meniu findById(Long id);
    void delete(Long id);
    List<Meniu> findAll();
    List<Meniu> findTot();

    List<Meniu> findAllByStare(String stare);


}
