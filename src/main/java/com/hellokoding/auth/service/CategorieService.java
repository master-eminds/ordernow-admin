package com.hellokoding.auth.service;

import com.hellokoding.auth.model.Categorie;

import java.util.List;

public interface CategorieService {

    void save(Categorie categorie);

    Categorie findById(Long id);
    List<Categorie> findAll();
}
