package com.hellokoding.auth.service;

import com.hellokoding.auth.model.Categorie;

import java.util.List;

public interface CategorieService {

    Categorie save(Categorie categorie);
    void delete(Long id);
    void stergeCategorie(Long id);
    Categorie findById(Long id);
    List<Categorie> findAll();

    List<Categorie> findAllByMeniuId(Long meniu_id);

    List<Categorie> findAllByVizibilitate(Long meniuId,String vizibilitate);
    }
