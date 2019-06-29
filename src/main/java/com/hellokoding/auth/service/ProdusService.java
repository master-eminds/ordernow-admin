package com.hellokoding.auth.service;

import com.hellokoding.auth.model.Produs;

import java.util.List;

public interface ProdusService {

    void save(Produs produs);
    void saveOrUpdate(Produs produs);
    void delete(Long id);
    List<Produs> findAll();

    Produs findById(Long id);

}
