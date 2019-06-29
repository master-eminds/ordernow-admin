package com.hellokoding.auth.service;

import com.hellokoding.auth.model.Meniu;

import java.util.List;

public interface MeniuService {

    void save(Meniu meniu);

    Meniu findById(Long id);
    void delete(Long id);
    List<Meniu> findAll();

}
