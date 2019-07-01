package com.hellokoding.auth.service;

import com.hellokoding.auth.model.Produs;

import java.util.List;
import java.util.Map;

public interface ProdusService {

    void save(Produs produs);
    void saveOrUpdate(Produs produs);
    void delete(Long id);
    List<Produs> findAll();
    Map<Long,Integer> numarProduseComandate();


    Produs findById(Long id);

}
