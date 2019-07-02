package com.hellokoding.auth.service;

import com.hellokoding.auth.model.Produs;
import com.hellokoding.auth.util.CountProdus;

import java.util.List;

public interface ProdusService {

    void save(Produs produs);
    void saveOrUpdate(Produs produs);
    void delete(Long id);
    List<Produs> findAll();
    List<CountProdus> numarProduseComandate();


    Produs findById(Long id);

}
